package bootwildfly;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("report")
public class ReportRestController {


	@Autowired
	private ReportService reportService;


	@RequestMapping(params = {"enquiryDate"}, method = RequestMethod.GET, path = "/file-upload")
	public ReportSummary getAccountCashBalanceSummary(@RequestParam("enquiryDate") @DateTimeFormat(iso = ISO.DATE) LocalDate enquiryDate) {
		return reportService.getFileUploadSummary(enquiryDate);
	}

}