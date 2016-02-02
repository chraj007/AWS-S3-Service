package org.spring.aws.s3readwrite.service.base;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.spring.aws.s3readwrite.pojo.S3ReadPojo;
import org.spring.aws.s3readwrite.pojo.S3WritePojo;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;

/**
 * The Interface AWSService.
 *
 * @author Rajesh Chejerla
 */

public interface AWSService {
	
	/**
	 * Read from s3.
	 *
	 * @param s3ReadPojo the s3 read pojo
	 * @throws Exception the exception
	 */
	void readFromS3(S3ReadPojo s3ReadPojo)  throws Exception;
	
	/**
	 * Read from s3.
	 *
	 * @param s3WritePojo the s3 write pojo
	 * @throws AmazonServiceException the amazon service exception
	 * @throws AmazonClientException the amazon client exception
	 * @throws InterruptedException the interrupted exception
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void writeToS3(S3WritePojo s3WritePojo)  throws AmazonServiceException, AmazonClientException, InterruptedException, FileNotFoundException, IOException;
	
	/**
	 * Write to s3.
	 *
	 * @param s3ReadPojo the s3 read pojo
	 * @return the list
	 * @throws AmazonServiceException the amazon service exception
	 * @throws AmazonClientException the amazon client exception
	 * @throws InterruptedException the interrupted exception
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	
	List<String> listKeys(S3ReadPojo s3ReadPojo) throws AmazonServiceException, AmazonClientException, InterruptedException, FileNotFoundException, IOException ;
}
