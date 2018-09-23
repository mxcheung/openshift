package fileuploadconsole.service;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import fileuploadconsole.model.ReportSummary;

public interface ReportService {

    ReportSummary getFileUploadSummary(LocalDate enquiryDate);

	void delete(Long fileId);

	ReportSummary uploadFileUploadSummary(MultipartFile uploadfile);
}
