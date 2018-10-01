package bootwildfly.service;

import java.time.LocalDate;

public class FileUploadFilterCriteria {

	
    private String applicationCd;
    private String type;
    private String subtype;
	private LocalDate valueDate;
	private LocalDate valueDateFrom;
	private LocalDate valueDateTo;
	
	public String getApplicationCd() {
		return applicationCd;
	}
	public void setApplicationCd(String applicationCd) {
		this.applicationCd = applicationCd;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSubtype() {
		return subtype;
	}
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	public LocalDate getValueDate() {
		return valueDate;
	}
	public void setValueDate(LocalDate valueDate) {
		this.valueDate = valueDate;
	}
	public LocalDate getValueDateFrom() {
		return valueDateFrom;
	}
	public void setValueDateFrom(LocalDate valueDateFrom) {
		this.valueDateFrom = valueDateFrom;
	}
	public LocalDate getValueDateTo() {
		return valueDateTo;
	}
	public void setValueDateTo(LocalDate valueDateTo) {
		this.valueDateTo = valueDateTo;
	}
	
	


}
