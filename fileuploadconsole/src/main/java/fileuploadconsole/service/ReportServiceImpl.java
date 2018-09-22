package fileuploadconsole.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fileuploadconsole.model.ReportSummary;
import fileuploadconsole.model.FileEntity;

@Service
public class ReportServiceImpl implements ReportService {

	@Override
	public ReportSummary getFileUploadSummary(LocalDate enquiryDate) {
		ReportSummary reportSummary = new ReportSummary();
		List<FileEntity> reports = new ArrayList<>();
		reports.add(getReport("Sample_PDF.pdf", "application pdf", "Sample PDF file"));
		reports.add(getReport("Sample_image.png", "image/png", "Picture file"));
		reports.add(getReport("Sample_zip.zip", "application/x-zip-compressed", "Zip file"));
		reports.add(getReport("Sample_text.txt", "text", "Text file"));
		reportSummary.setReports(reports);
		return reportSummary;
	}

	private FileEntity getReport(String fileName, String fileType, String description) {
		FileEntity report = new FileEntity();
		report.setFileName(fileName);
		report.setFileType(fileType);
		report.setDescription(description);
		return report;
	}

}