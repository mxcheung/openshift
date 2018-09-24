package fileuploadconsole.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fileuploadconsole.model.FileEntity;
import fileuploadconsole.model.ReportSummary;
import fileuploadconsole.repo.FileRepository;

@Service
public class ReportServiceImpl implements ReportService {

	private final FileRepository fileRepository;
	
	@Autowired
	public ReportServiceImpl(FileRepository fileRepository) {
		super();
		List<FileEntity> reports = getReports();
		this.fileRepository = fileRepository;
		fileRepository.save(reports);
	}

	@Override
	public ReportSummary getFileUploadSummary(LocalDate enquiryDate) {
		ReportSummary reportSummary = new ReportSummary();
	//	List<FileEntity> reports = getReports();
//		List<FileEntity> reports = fileRepository.findAll();
		List<FileEntity> reports = fileRepository.findByValueDate(enquiryDate);
		
		reportSummary.setReports(reports);
		return reportSummary;
	}

	@Override
	public void delete(Long id) {
		fileRepository.deleteById(id);
	}

	private List<FileEntity> getReports() {
		List<FileEntity> reports = new ArrayList<>();
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);
		LocalDate twoDaysAgo = today.minusDays(2);
		
		reports.add(getReport(today,"Sample_PDF.pdf", "application pdf", "Sample PDF file"));
		reports.add(getReport(yesterday,"Sample_image.png", "image/png", "Picture file"));
		reports.add(getReport(today,"Sample_zip.zip", "application/x-zip-compressed", "Zip file"));
		reports.add(getReport(today,"Sample_text.txt", "text", "Text file"));
		reports.add(getReport(today,"Sample_csv.csv", "csv", "csv file"));
		return reports;
	}

	private FileEntity getReport(LocalDate valueDate, String fileName, String fileType, String description) {
		FileEntity report = new FileEntity();
		report.setValueDate(valueDate);
		report.setFileName(fileName);
		report.setFileType(fileType);
		report.setDescription(description);
		return report;
	}

	@Override
	public ReportSummary uploadFileUploadSummary(MultipartFile uploadfile) {
		// TODO Auto-generated method stub
		return null;
	}


}