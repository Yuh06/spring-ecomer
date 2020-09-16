package vn.t3h.aop;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import vn.t3h.data.UploadsFiles;
import vn.t3h.exceptions.InvilidParamsException;

public class BaseUploads {

	public static String uploadSignFile(MultipartFile multipartFile, UploadsFiles uploadsFiles) throws Exception {
		String nameFile = multipartFile.getOriginalFilename();
		try {
			String extionsionFile = AopCommon.pathNameFile(nameFile);
			List<String> filePass = uploadsFiles.filePass();
			if(!filePass.contains(extionsionFile)) {
				throw new InvilidParamsException("File không được phép upload !");
			}
	        String fileMd5 = AopCommon.setFileName(nameFile + AmazonUtil.dateToInt())  + "." +  AopCommon.pathNameFile(nameFile);
	        
	        Integer subFolder = AmazonUtil.getFolderUpload();
	        String fd = System.getProperty("user.dir") + uploadsFiles.folderUpload() + subFolder + "/";
	        File f = new File(fd);
	  		if(!f.exists()) f.mkdirs();
	  		
	        String filePath = fd + fileMd5;
	        InputStream fileStream = multipartFile.getInputStream();
	        File targetFile = new File(filePath);
	        FileUtils.copyInputStreamToFile(fileStream, targetFile);
	        
	        return subFolder + "/" + fileMd5;
		} catch (IOException | NoSuchAlgorithmException e) {
			throw e;
		}
	}
}
