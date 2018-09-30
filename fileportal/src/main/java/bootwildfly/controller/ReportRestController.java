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

import bootwildfly.model.ReportSummary;
import bootwildfly.service.ReportService;

@RestController
@RequestMapping("report")
public class ReportRestController {

	// Linux: /home/{user}/test
	// Windows: C:/Users/{user}/test
	private static String UPLOAD_DIR = System.getProperty("user.home") + "/test";

	@Autowired
	private ReportService reportService;

	@RequestMapping(params = { "enquiryDate" }, method = RequestMethod.GET, path = "/file-upload")
	public ReportSummary getFileUploadSummary(
			@RequestParam("enquiryDate") @DateTimeFormat(iso = ISO.DATE) LocalDate enquiryDate) {
		return reportService.getFileUploadSummary(enquiryDate);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{fileId}")
	public void deleteFileUpload(@PathVariable Long fileId) {
		reportService.delete(fileId);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/file-upload")
	public ReportSummary uploadFileUploadSummary( @RequestParam("description") String description,
			@RequestParam("uploadfile") MultipartFile file) {
		return reportService.uploadFileUploadSummary(file,description);
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