package bootwildfly;

import java.time.LocalDate;

public interface ReportService {

    ReportSummary getFileUploadSummary(LocalDate enquiryDate);
}
