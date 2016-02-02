package org.spring.aws.s3readwrite;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.aws.s3readwrite.pojo.S3ReadPojo;
import org.spring.aws.s3readwrite.pojo.S3WritePojo;
import org.spring.aws.s3readwrite.service.base.AWSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AwsS3ServiceApplication.class)
public class AwsS3ServiceApplicationTests {

	@Autowired
	private AWSService awsService;
	@Test
	public void testReadFromS3() {
		S3ReadPojo s3ReadPojo = new S3ReadPojo("<your-bucket-name>",
				"<your-s3-access-key>", 
				"<your-s3-access-secret>", 
				"<s3-file-path-to-read>", 
				"<destination-file-path>");
		try {
			// Please uncomment below line to test
			//awsService.readFromS3(s3ReadPojo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void writeReadFromS3() {
		List<String> filesToSave = new ArrayList<String>(); // add list of files to save
		filesToSave.add("sample1.txt");
		filesToSave.add("sample2.txt");
		S3WritePojo s3WritePojo = new S3WritePojo("<your-bucket-name>", 
				"<s3-directory-path>",
				"<your-s3-access-key>", 
				"<your-s3-access-secret>",
				filesToSave,
				true,
				false);
		try {
			// Please uncomment below line to test
			//awsService.writeToS3(s3WritePojo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void listFileNamesFromS3(){
		S3ReadPojo s3ReadPojo = new S3ReadPojo("<your-bucket-name>",
				"<your-s3-access-key>", 
				"<your-s3-access-secret>", 
				"<s3-file-path-to-read>", 
				"<destination-file-path>");
		try {
			// Please uncomment below line to test
			//awsService.listKeys(s3ReadPojo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
