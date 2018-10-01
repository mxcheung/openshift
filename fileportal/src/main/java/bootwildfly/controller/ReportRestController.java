package bootwildfly.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bootwildfly.model.FileEntity;
import bootwildfly.model.ReportSummary;
import bootwildfly.service.DepositFilterCriteria;
import bootwildfly.service.ReportService;
import bootwildfly.storage.StorageService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import java.util.Optional;

@RestController
@RequestMapping("report")
public class ReportRestController {

	// Linux: /home/{user}/test
	// Windows: C:/Users/{user}/test
	private static String UPLOAD_DIR = System.getProperty("user.home") + "/test";

	@Autowired
	StorageService storageService;

	@Autowired
	private ReportService reportService;

	@RequestMapping(params = { "enquiryDate" }, method = RequestMethod.GET, path = "/file-upload")
	public ReportSummary getFileUploadSummary(
		    @RequestParam("enquiryDate") @DateTimeFormat(iso = ISO.DATE) Optional<LocalDate> enquiryDate,
			@RequestParam("applicationCd") Optional<String> applicationCd) {

		DepositFilterCriteria depositFilterCriteria = new DepositFilterCriteria();
		depositFilterCriteria.setValueDate(enquiryDate.orElse(null));
		depositFilterCriteria.setApplicationCd(applicationCd.orElse(null));
		return reportService.getFileUploadSummary(depositFilterCriteria);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{fileId}")
	public void deleteFileUpload(@PathVariable Long fileId) {
		reportService.delete(fileId);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/file-download/{fileId}")
	public ResponseEntity<Resource> getFile(@PathVariable Long fileId) {
		FileEntity fileEntity = reportService.get(fileId);
		String filename = fileEntity.getFileName();
		Resource file = storageService.loadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/file-upload")
	public ReportSummary uploadFileUploadSummary(@RequestParam("applicationCd") String applicationCd,
			@RequestParam("type") String type, @RequestParam("subtype") String subtype,
			@RequestParam("description") String description, @RequestParam("uploadfile") MultipartFile file) {
		storageService.store(file);

		UploadForm uploadForm = new UploadForm();
		uploadForm.setApplicationCd(applicationCd);
		uploadForm.setType(type);
		uploadForm.setSubtype(subtype);
		uploadForm.setDescription(description);
		uploadForm.setFile(file);

		return reportService.uploadFileUploadSummary(uploadForm);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/rest/uploadMultiFiles")
	public ResponseEntity<?> uploadFileMulti(@ModelAttribute UploadForm form, final BindingResult bindingResult)
			throws Exception {

		System.out.println("Description:" + form.getDescription());

		String result = null;
		try {

			result = this.saveUploadedFiles(form.getFiles());

		}
		// Here Catch IOException only.
		// Other Exceptions catch by RestGlobalExceptionHandler class.
		catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("Uploaded to: " + result, HttpStatus.OK);
	}

	// Save Files
	private String saveUploadedFiles(MultipartFile[] files) throws IOException {

		// Make sure directory exists!
		File uploadDir = new File(UPLOAD_DIR);
		uploadDir.mkdirs();

		StringBuilder sb = new StringBuilder();

		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				continue;
			}
			String uploadFilePath = UPLOAD_DIR + "/" + file.getOriginalFilename();

			byte[] bytes = file.getBytes();
			Path path = Paths.get(uploadFilePath);
			Files.write(path, bytes);

			sb.append(uploadFilePath).append(", ");
		}
		return sb.toString();
	}
}