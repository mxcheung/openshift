package bootwildfly.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import bootwildfly.controller.UploadForm;
import bootwildfly.model.FileEntity;
import bootwildfly.model.ReportSummary;
import bootwildfly.repo.FileRepository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


@Service
public class ReportServiceImpl implements ReportService {


	private final FileRepository fileRepository;

    @Autowired
    private EntityManager entityManager;

	@Autowired
	public ReportServiceImpl(FileRepository fileRepository) {
		super();
		List<FileEntity> reports = getReports();
		this.fileRepository = fileRepository;
		fileRepository.save(reports);
	}

	@Override
	public ReportSummary getFileUploadSummary(DepositFilterCriteria depositFilterCriteria) {
		ReportSummary reportSummary = new ReportSummary();
		// List<FileEntity> reports = getReports();
		// List<FileEntity> reports = fileRepository.findAll();
//		List<FileEntity> reports = fileRepository.findByValueDate(enquiryDate);
		
		List<FileEntity> files = getRecordsByCriteria(depositFilterCriteria);

		reportSummary.setReports(files);
		return reportSummary;
	}
	
	
	
	  @Override
	    public List<FileEntity> getRecordsByCriteria(DepositFilterCriteria criteria) {
	        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	        CriteriaQuery<FileEntity> depositQuery = criteriaBuilder.createQuery(FileEntity.class);
	        Root<FileEntity> deposit = depositQuery.from(FileEntity.class);
	        List<Predicate> predicates = new ArrayList<Predicate>();
	        List<FileEntity> depositList = new ArrayList<FileEntity>();
	        LocalDate valueDate = criteria.getValueDate();
	        if (valueDate != null) {
	        	predicates.add(criteriaBuilder.equal(deposit.get("valueDate"), valueDate));
	        }
	        LocalDate valueDateFrom = criteria.getValueDateFrom();
	        if (valueDateFrom != null) {
	        	predicates.add(criteriaBuilder.greaterThanOrEqualTo(deposit.get("valueDate"), valueDateFrom));
	        }
	        LocalDate valueDateTo = criteria.getValueDateTo();
	        if (valueDateTo != null) {
	            predicates.add(criteriaBuilder.lessThanOrEqualTo(deposit.get("valueDate"), valueDateTo));
	        }
	        
	        String applicationCd = criteria.getApplicationCd();
	        if (applicationCd != null) {
	        	predicates.add(criteriaBuilder.equal(deposit.get("applicationCd"), applicationCd));
	        }
	        
	        
	        if (predicates.isEmpty()) {
	            return depositList;
	        }
	        
	        depositQuery.select(deposit).where(predicates.toArray(new Predicate[] {}));
//	        depositQuery.orderBy(criteriaBuilder.desc(deposit.get("createdOn")));
	        TypedQuery<FileEntity> depositTypedQuery = entityManager.createQuery(depositQuery);
	        depositList = depositTypedQuery.getResultList();
	        return depositList;
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

		reports.add(getReport(today, "General", "DefaultType", "DefaultSubType", "Sample_PDF.pdf", "application pdf",
				"Sample PDF file"));
		reports.add(getReport(yesterday, "Accounting", "DefaultType", "DefaultSubType", "Sample_image.png", "image/png",
				"Picture file"));
		reports.add(getReport(today, "Banking", "DefaultType", "DefaultSubType", "Sample_zip.zip",
				"application/x-zip-compressed", "Zip file"));
		reports.add(getReport(today, "Accounting", "DefaultType", "DefaultSubType", "Sample_text.txt", "text", "Text file"));
		reports.add(getReport(today, "Reconciliation", "DefaultType", "DefaultSubType", "Sample_csv.csv", "csv", "csv file"));
		return reports;
	}

	private FileEntity getReport(LocalDate valueDate, String applicationCd, String type, String subtype,
			String fileName, String fileType, String description) {
		FileEntity report = new FileEntity();
		report.setApplicationCd(applicationCd);
		report.setType(type);
		report.setSubtype(subtype);
		report.setValueDate(valueDate);
		report.setFileName(fileName);
		report.setFileType(fileType);
		report.setDescription(description);
		report.setMaker("user1");
		return report;
	}

	@Override
	public ReportSummary uploadFileUploadSummary(UploadForm uploadForm) {
		LocalDate today = LocalDate.now();
		FileEntity fileEntity = getReport(today, uploadForm.getApplicationCd(), uploadForm.getType(),
				uploadForm.getSubtype(), uploadForm.getFile().getOriginalFilename(),
				uploadForm.getFile().getContentType(), uploadForm.getDescription());
		fileEntity.setCreatedOn(LocalDateTime.now());
		fileEntity.setMaker("user1");
		fileRepository.save(fileEntity);
		return null;
	}

	@Override
	public FileEntity get(Long fileId) {
		return fileRepository.findOne(fileId);
	}

}