package bootwildfly.service;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import bootwildfly.model.ReportSummary;

public interface ReportService {

    ReportSummary getFileUploadSummary(LocalDate enquiryDate);

	void delete(Long fileId);

	ReportSummary uploadFileUploadSummary(MultipartFile uploadfile, String description);
}
