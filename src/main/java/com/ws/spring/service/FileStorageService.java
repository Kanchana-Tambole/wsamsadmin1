package com.ws.spring.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.SQLException;
 
import javax.servlet.http.HttpServletRequest;
 
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
 
import com.ws.spring.exception.FileStorageException;
import com.ws.spring.exception.MyFileNotFoundException;
import com.ws.spring.properties.FileStorageProperties;
 
@Service
public class FileStorageService {
 
    private final Path fileStorageLocation;
 
    Logger logger = LoggerFactory.getLogger(this.getClass().getName());
 
    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
 
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
                    ex);
        }
    }
 
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
 
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
 
            // SimpleDateFormat newFormat = new SimpleDateFormat(("yyyyMMdd_hhmmss"));
            // String currentDateStr = newFormat.format(new Date());
            // String[] splitFileName = fileName.split("\\.");
            // String newFileName = splitFileName[0] + "_" + currentDateStr + "." +
            // splitFileName[1];
 
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
 
            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
 
    public String moveFile(String parentFolder,String fileName, String directoryName) {
        // Normalize file name
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
 
            // Move file to the target location (Replacing existing file with the same name)
            Path sourceLocation = this.fileStorageLocation.resolve(fileName);
            Files.createDirectories(sourceLocation.getParent().resolve(parentFolder).resolve(directoryName));
 
            Path targetLocation = this.fileStorageLocation.resolve(parentFolder).resolve(directoryName).resolve(fileName);
            Files.move(sourceLocation, targetLocation, StandardCopyOption.ATOMIC_MOVE);

 
            logger.info("MOve file from : {} to {}", sourceLocation, targetLocation);
            return targetLocation.toAbsolutePath().toString();
        } catch (NoSuchFileException e) {
            // Returning null while Topic edit file wont present in the server
            return null;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
 
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
 
    public Resource loadFileAsResource(String action,String topicName, String fileName) {
        try {
            String file = action + File.separator + topicName + File.separator + fileName;
            Path filePath = this.fileStorageLocation.resolve(file);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
 
    public Path loadFileAsPath(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            if (Files.exists(filePath)) {
                return filePath;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (Exception ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
 
    public Resource getTemplatePath(String fileName, HttpServletRequest request) {
        try {
            String dataDirectory = request.getServletContext().getRealPath("/app_templates/");
            Path filePath = Paths.get(dataDirectory, fileName);
            return new UrlResource(filePath.toUri());
        } catch (Exception e) {
            throw new MyFileNotFoundException("File not found " + fileName);
        }
    }
 
    public byte[] convertFileContentToBlob(String fileName) throws IOException {
        byte[] fileContent = null;
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            fileContent = FileUtils.readFileToByteArray(filePath.toFile());
        } catch (IOException e) {
            throw new IOException("Unable to convert file to byte array. " + e.getMessage());
        }
        return fileContent;
    }
 
    public String convertBlobContentToString(Blob content) throws IOException {
        String fileContent = null;
        try {
            fileContent = new String(content.getBytes(1l, (int) content.length()));
        } catch (SQLException e) {
            throw new IOException("Unable to convert file to byte array. " + e.getMessage());
        }
        return fileContent;
    }
}