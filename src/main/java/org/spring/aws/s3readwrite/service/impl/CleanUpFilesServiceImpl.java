package org.spring.aws.s3readwrite.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.spring.aws.s3readwrite.service.base.CleanUpFilesService;
import org.springframework.stereotype.Service;


/**
 * @author Rajesh Chejerla
 */
@Service
public class CleanUpFilesServiceImpl implements CleanUpFilesService{

	@Override
	public void removeFiles(List<String> files) {
		if(files != null && files.size() > 0){
			for (String file : files) {
				new File(file).delete();
			}
		}
	}

	@Override
	public void removeDirectory(String directoryPath) throws IOException {
		FileUtils.deleteDirectory(new File(directoryPath));
	}

}
