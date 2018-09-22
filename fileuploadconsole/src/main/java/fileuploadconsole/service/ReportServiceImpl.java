package fileuploadconsole.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fileuploadconsole.model.ReportSummary;
import fileuploadconsole.model.ReportTable;

@Service
public class ReportServiceImpl implements ReportService {

	@Override
	public ReportSummary getFileUploadSummary(LocalDate enquiryDate) {
		ReportSummary reportSummary = new ReportSummary();
		List<ReportTable> reports = new ArrayList<>();
		reports.add(getReport("Sample_PDF.pdf", "application pdf", "Sample PDF file"));
		reports.add(getReport("Sample_image.png", "image/png", "Picture file"));
		reports.add(getReport("Sample_zip.zip", "application/x-zip-compressed", "Zip file"));
		reports.add(getReport("Sample_text.txt", "text", "Text file"));
		reportSummary.setReports(reports);
		return reportSummary;
	}

	private ReportTable getReport(String fileName, String fileType, String description) {
		ReportTable report = new ReportTable();
		report.setFileName(fileName);
		report.setFileType(fileType);
		report.setDescription(description);
		return report;
	}

}