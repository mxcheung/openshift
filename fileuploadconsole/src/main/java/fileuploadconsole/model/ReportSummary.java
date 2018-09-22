package fileuploadconsole.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "reports" })
public class ReportSummary {

    private List< ReportTable> reports;

	public List<ReportTable> getReports() {
		return reports;
	}

	public void setReports(List<ReportTable> reports) {
		this.reports = reports;
	}

    

}