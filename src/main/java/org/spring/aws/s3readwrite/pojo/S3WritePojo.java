package org.spring.aws.s3readwrite.pojo;

import java.util.List;

/**
 * @author Rajesh Chejerla
 */

public class S3WritePojo {
	private String bucketName;
	private String directoryPath;
	private String accessKey;
	private String accessSecret;
	private List<String> filesToSave;
	private boolean withGZip;
	private boolean directory;
	
	public S3WritePojo(String bucketName, String directoryPath,
			String accessKey, String accessSecret, List<String> filesToSave,
			boolean withGZip, boolean directory) {
		super();
		this.bucketName = bucketName;
		this.directoryPath = directoryPath;
		this.accessKey = accessKey;
		this.accessSecret = accessSecret;
		this.filesToSave = filesToSave;
		this.withGZip = withGZip;
		this.directory = directory;
	}
	
	public String getBucketName() {
		return bucketName;
	}
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	public String getDirectoryPath() {
		return directoryPath;
	}
	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
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
	public List<String> getFilesToSave() {
		return filesToSave;
	}
	public void setFilesToSave(List<String> filesToSave) {
		this.filesToSave = filesToSave;
	}
	public boolean isWithGZip() {
		return withGZip;
	}
	public void setWithGZip(boolean withGZip) {
		this.withGZip = withGZip;
	}
	public boolean isDirectory() {
		return directory;
	}
	public void setDirectory(boolean directory) {
		this.directory = directory;
	}
	
}
