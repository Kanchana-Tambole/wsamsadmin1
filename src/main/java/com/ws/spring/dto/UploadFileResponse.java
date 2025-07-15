package com.ws.spring.dto;

public class UploadFileResponse {
	
	private long responseCode;
	private String status;
	private String message;
	
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    public UploadFileResponse(int responseCode, String status, String message, String string3, String fileName,
			String fileDownloadUri, String fileType, long size) {
    	 this.responseCode = responseCode;
    	 this.status = status;
    	 this.message = message;
    	 this.fileName = fileName;
         this.fileDownloadUri = fileDownloadUri;
         this.fileType = fileType;
         this.size = size;
         
	}
    
    
    
    
    
    

	

	
	public long getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(long responseCode) {
		this.responseCode = responseCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
