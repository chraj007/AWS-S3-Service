package org.spring.aws.s3readwrite.pojo;

/**
 * @author Rajesh Chejerla
 */

public class S3ReadPojo {
	private String bucketName;
	private String accessKey;
	private String accessSecret;
	private String filePath;
	private String destinationFilePath;
	
	public S3ReadPojo(String bucketName, String accessKey, String accessSecret, String filePath,
			String destinationFilePath) {
		super();
		this.bucketName = bucketName;
		this.accessKey = accessKey;
		this.accessSecret = accessSecret;
		this.filePath = filePath;
		this.destinationFilePath = destinationFilePath;
	}
	
	public String getBucketName() {
		return bucketName;
	}
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getAccessSecret() {
		return accessSecret;
	}
	public void setAccessSecret(String accessSecret) {
		this.accessSecret = accessSecret;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getDestinationFilePath() {
		return destinationFilePath;
	}
	public void setDestinationFilePath(String destinationFilePath) {
		this.destinationFilePath = destinationFilePath;
	}

}
