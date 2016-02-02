package org.spring.aws.s3readwrite.service.base;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The Interface ZIpService.
 *
 * @author Rajesh Chejerla
 */

public interface ZIpService {
	
	/**
	 * Compress file.
	 *
	 * @param inputFile the input file
	 * @param outPutFile the out put file
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void compressFile(String inputFile, String outPutFile) throws FileNotFoundException, IOException;
	
	/**
	 * De compress file.
	 *
	 * @param inputFile the input file
	 * @param outPutFile the out put file
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void deCompressFile(String inputFile, String outPutFile) throws FileNotFoundException, IOException;
}
