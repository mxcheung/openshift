package fileuploadconsole.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "reports" })
public class ReportSummary {

    private List< FileEntity> reports;

	public List<FileEntity> getReports() {
		return reports;
	}

	public void setReports(List<FileEntity> reports) {
		this.reports = reports;
	}

    

}