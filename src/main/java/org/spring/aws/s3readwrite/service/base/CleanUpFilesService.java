package org.spring.aws.s3readwrite.service.base;

import java.io.IOException;
import java.util.List;

/**
 * The Interface CleanUpFilesService.
 *
 * @author Rajesh Chejerla
 */

public interface CleanUpFilesService {
	
	/**
	 * Removes the files.
	 *
	 * @param files the files
	 */
	void removeFiles(List<String> files);
	
	/**
	 * Removes the directory.
	 *
	 * @param directoryPath the directory path
	 */
	void removeDirectory(String directoryPath) throws IOException;
}
