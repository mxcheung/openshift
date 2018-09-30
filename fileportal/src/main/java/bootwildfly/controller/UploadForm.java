package bootwildfly.controller;

import org.springframework.web.multipart.MultipartFile;

public class UploadForm {

	private String applicationCd;
	private String type;
	private String subtype;
	private String description;
	private MultipartFile file;
	private MultipartFile[] files;
	public String getApplicationCd() {
		return applicationCd;
	}
	public void setApplicationCd(String applicationCd) {
		this.applicationCd = applicationCd;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSubtype() {
		return subtype;
	}
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	
	
	

}