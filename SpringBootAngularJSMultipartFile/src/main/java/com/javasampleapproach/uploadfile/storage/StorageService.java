package com.javasampleapproach.uploadfile.storage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class StorageService {
	
	private static final String VAR_TMP_UPLOAD_DIR1 = "/var/tmp/upload-dir2";
//	private static final String VAR_TMP_UPLOAD_DIR1 = "c://temp//upload-dir1//";

	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	private final Path rootLocation = Paths.get(VAR_TMP_UPLOAD_DIR1);

	public void store(MultipartFile file){
		try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
        	throw new RuntimeException("FAIL!");
        }
	}

    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else{
            	throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
        	throw new RuntimeException("FAIL!");
        }
    }
    
    public void deleteAll() {
    	log.info("deleteAll start");
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    	log.info("deleteAll end");
    }

    public void init() {
        try {
        	log.info("createDirectory start");
            Files.createDirectory(rootLocation);
        	log.info("createDirectory end");
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }
}