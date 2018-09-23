package fileuploadconsole.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fileuploadconsole.model.ReportSummary;
import fileuploadconsole.service.ReportService;

@RestController
@RequestMapping("report")
public class ReportRestController {


	@Autowired
	private ReportService reportService;


	@RequestMapping(params = {"enquiryDate"}, method = RequestMethod.GET, path = "/file-upload")
	public ReportSummary getFileUploadSummary(@RequestParam("enquiryDate") @DateTimeFormat(iso = ISO.DATE) LocalDate enquiryDate) {
		return reportService.getFileUploadSummary(enquiryDate);
	}
	
	
    @RequestMapping(method = RequestMethod.DELETE, path = "/{fileId}")
    public void deleteFileUpload(@PathVariable Long fileId) {
    	reportService.delete(fileId);
    }


}