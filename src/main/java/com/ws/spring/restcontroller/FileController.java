package com.ws.spring.restcontroller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ws.spring.config.MyResourceHttpRequestHandler;
import com.ws.spring.dto.UploadFileResponse;
import com.ws.spring.service.FileStorageService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/file")
@Api(value = "File Managenemt", tags = "File Managenent")
// 100 MB = 104857600 , 200MB = 209715200 ‬
// @MultipartConfig(fileSizeThreshold = 0, maxFileSize = 104857600‬, maxRequestSize = 104857600‬)
public class FileController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private FileStorageService fileStorageService;
    
    @Autowired
	private MyResourceHttpRequestHandler handler;

    @PostMapping("/uploadFile")
    public ResponseEntity<UploadFileResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = fileStorageService.storeFile(file);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
                    .path(fileName).toUriString();

         //   return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
            
            return ResponseEntity.ok().body(new UploadFileResponse(HttpStatus.CREATED.value(), "SUCCESS",
           		 " File Successfully Uploaded", "",fileName, fileDownloadUri, file.getContentType(), file.getSize()));
            
        } catch (Exception ex) {
            logger.error("Exception Occure : {} ", ex.getMessage(), ex);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exception Occured", ex);
        }
    }

    @PostMapping("/uploadMultipleFiles")
    public List<ResponseEntity<UploadFileResponse>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files).stream().map(file -> uploadFile(file)).collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            // Fallback to the default content type if type could not be determined
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exception Occured", ex);
        }

    }

    @GetMapping("/downloadFile/")
    public ResponseEntity<Resource> downloadFileFromPath(@RequestParam String filePath, HttpServletRequest request) {
        String contentType = null;
        try {
            // Load file as Resource
            File path = new File(filePath);
            Resource resource = new UrlResource(path.toURI());

            // Try to determine file's content type
            contentType = request.getServletContext().getMimeType(path.getAbsolutePath());
            // Fallback to the default content type if type could not be determined
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (IOException ex) {
            logger.error("Exception Occure : {} ", ex.getMessage(), ex);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exception Occured", ex);
        }

    }

//    @GetMapping("/downloadPdfFile/{topicName}/{fileName:.+}")
    public ResponseEntity<Resource> downloadPdfFile(@PathVariable String action , @PathVariable String topicName, @PathVariable String fileName,
            HttpServletRequest request) {
        // Load file as Resource

        String newTopicDirectory = topicName.replace(" ", "_");
        Resource resource = fileStorageService.loadFileAsResource(action,newTopicDirectory, fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.error("Exception Occure : {} ", ex.getMessage(), ex);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exception Occured", ex);
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/downloadTemplateFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadTemplateFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.getTemplatePath(fileName, request);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.error("Exception Occure : {} ", ex.getMessage(), ex);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exception Occured", ex);
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
    
 // supports byte-range requests
 	@GetMapping("/videos/byterange/{action}/{id}/{fileName:.+}")
 	public void byterange(@PathVariable String action, @PathVariable String id, @PathVariable String fileName, HttpServletRequest request,
 			HttpServletResponse response) throws ServletException, IOException {

 		String fileDirectory = String.valueOf(id);
 		Resource resource = fileStorageService.loadFileAsResource(action,fileDirectory, fileName);

 		request.setAttribute(MyResourceHttpRequestHandler.ATTR_FILE, resource);
 		handler.handleRequest(request, response);
 	}
}