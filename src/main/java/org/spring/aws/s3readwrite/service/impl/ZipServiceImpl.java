package org.spring.aws.s3readwrite.service.impl;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.spring.aws.s3readwrite.service.base.ZIpService;
import org.springframework.stereotype.Service;

/**
 * @author Rajesh Chejerla
 */
@Service
public class ZipServiceImpl implements ZIpService{

	public void compressFile(String inputFile, String outPutFile) throws FileNotFoundException, IOException{
		InputStream in = new FileInputStream(inputFile);
		OutputStream out = new GZIPOutputStream(
		            new BufferedOutputStream(new FileOutputStream(new File(outPutFile))));

		byte[] bytes = new byte[32*1024];
		int len;
		while((len = in.read(bytes)) > 0)
		   out.write(bytes, 0, len);

		in.close();
		out.flush();
		out.close();
	}

	@Override
	public void deCompressFile(String inputFile, String outPutFile) throws FileNotFoundException, IOException {
		byte[] buffer = new byte[32*1024];	
		GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(inputFile));
		FileOutputStream out = new FileOutputStream(outPutFile);
        int len;
        while ((len = gzis.read(buffer)) > 0) {
        	out.write(buffer, 0, len);
        }
        gzis.close();
    	out.close();
	}
}
