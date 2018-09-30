package bootwildfly.service;

import java.time.LocalDate;

import bootwildfly.controller.UploadForm;
import bootwildfly.model.FileEntity;
import bootwildfly.model.ReportSummary;

public interface ReportService {

    ReportSummary getFileUploadSummary(LocalDate enquiryDate);

	void delete(Long fileId);

	FileEntity get(Long fileId);

	ReportSummary uploadFileUploadSummary(UploadForm uploadForm);
}
