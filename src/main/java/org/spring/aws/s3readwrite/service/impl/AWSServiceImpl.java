package org.spring.aws.s3readwrite.service.impl;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.spring.aws.s3readwrite.pojo.S3ReadPojo;
import org.spring.aws.s3readwrite.pojo.S3WritePojo;
import org.spring.aws.s3readwrite.service.base.AWSService;
import org.spring.aws.s3readwrite.service.base.CleanUpFilesService;
import org.spring.aws.s3readwrite.service.base.ZIpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.MultipleFileUpload;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;

/**
 * @author Rajesh Chejerla
 */
@Service
public class AWSServiceImpl implements AWSService{

	@Autowired
	ZIpService zipService;
	@Autowired
	CleanUpFilesService cleanUpFilesService;
	
	@Override
	public void readFromS3(S3ReadPojo s3ReadPojo)  throws Exception{
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(s3ReadPojo.getAccessKey(), s3ReadPojo.getAccessSecret());
		AmazonS3 s3Client = new AmazonS3Client(awsCreds); 
		S3Object object = s3Client.getObject(new GetObjectRequest(s3ReadPojo.getBucketName(), s3ReadPojo.getFilePath()));
		InputStream inputStream = null;
		int retry = 0;
		while(retry++ <= 5){
			try {
				inputStream = object.getObjectContent();
				Files.copy(inputStream, new File(s3ReadPojo.getDestinationFilePath()).toPath(), StandardCopyOption.REPLACE_EXISTING);
				break;
			} catch (Exception e) {
				Thread.sleep(5000);
				if(retry == 5){
					throw new RuntimeException(e);
				}
			}finally{
				inputStream.close();
			}
		}
	}

	@Override
	public void writeToS3(S3WritePojo s3WritePojo) throws AmazonServiceException, AmazonClientException, InterruptedException, FileNotFoundException, IOException {
		if(s3WritePojo != null){
			if(s3WritePojo.isDirectory()){
				uploadDirectory(s3WritePojo);
			}else{
				uploadMultiPartFile(s3WritePojo);
			}
		}
	}
	
	private void uploadDirectory(S3WritePojo s3WritePojo) throws AmazonServiceException, AmazonClientException, InterruptedException {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(s3WritePojo.getAccessKey(), s3WritePojo.getAccessSecret());
        TransferManager tx = new TransferManager(awsCreds);
        for(String directory : s3WritePojo.getFilesToSave()){
        	MultipleFileUpload upload = tx.uploadDirectory(s3WritePojo.getBucketName(), s3WritePojo.getDirectoryPath(), new File(directory), true);
        	upload.waitForCompletion();
        }
       
    }
    
	private void uploadMultiPartFile(S3WritePojo s3WritePojo) throws AmazonServiceException, AmazonClientException, InterruptedException, FileNotFoundException, IOException{
    	BasicAWSCredentials awsCreds = new BasicAWSCredentials(s3WritePojo.getAccessKey(), s3WritePojo.getAccessSecret());
    	TransferManager tm = new TransferManager(awsCreds);    
    	String gipFilePath;
    	List<String> cleanUpFiles = new ArrayList<String>(0);
    	for(String filePath : s3WritePojo.getFilesToSave()){
    		if(s3WritePojo.isWithGZip()){
    			gipFilePath = filePath.substring(filePath.lastIndexOf(System.getProperty("file.separator"))+1)+".gz";
    			zipService.compressFile(filePath, gipFilePath);
    			filePath = gipFilePath;
    			cleanUpFiles.add(gipFilePath);
    		}
    		int retry = 0;
    		while(retry++ <= 5){
    			try {
    				Upload upload = tm.upload(s3WritePojo.getBucketName(), s3WritePojo.getDirectoryPath()+System.getProperty("file.separator")+filePath, new File(filePath));
                	upload.waitForCompletion();
				} catch (Exception e) {
					Thread.sleep(5000);
					if(retry == 5){
						throw new RuntimeException(e);
					}
				}
    		}
    	}
		cleanUpFilesService.removeFiles(cleanUpFiles);
    }

	@Override
	public List<String> listKeys(S3ReadPojo s3ReadPojo) {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(s3ReadPojo.getAccessKey(), s3ReadPojo.getAccessSecret());
		List<String> keys = new ArrayList<String>();
		AmazonS3 s3Client = new AmazonS3Client(awsCreds); 
		ListObjectsRequest listObjectsRequest = new ListObjectsRequest().
		  withBucketName(s3ReadPojo.getBucketName()).
		  withPrefix(s3ReadPojo.getFilePath()).
		  withDelimiter("/");
		ObjectListing objectList = s3Client.listObjects(listObjectsRequest);
		for (S3ObjectSummary summary : objectList.getObjectSummaries()) {
			keys.add(summary.getKey());
		}
	
		return keys;
	}
}
