package bootwildfly.service;

import java.time.LocalDate;
import java.util.List;

import bootwildfly.controller.UploadForm;
import bootwildfly.model.FileEntity;
import bootwildfly.model.ReportSummary;

public interface ReportService {

    ReportSummary getFileUploadSummary(DepositFilterCriteria depositFilterCriteria);

	void delete(Long fileId);

	FileEntity get(Long fileId);

	ReportSummary uploadFileUploadSummary(UploadForm uploadForm);

	List<FileEntity> getRecordsByCriteria(DepositFilterCriteria criteria);
}
