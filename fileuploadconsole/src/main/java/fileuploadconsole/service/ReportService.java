package fileuploadconsole.service;

import java.time.LocalDate;

import fileuploadconsole.model.ReportSummary;

public interface ReportService {

    ReportSummary getFileUploadSummary(LocalDate enquiryDate);
}
