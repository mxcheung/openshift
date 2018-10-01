package bootwildfly.service;

import java.time.LocalDate;

public class DepositFilterCriteria {

	
    private String applicationCd;
	private LocalDate valueDate;
	private LocalDate valueDateFrom;
	private LocalDate valueDateTo;
	
	
	public String getApplicationCd() {
		return applicationCd;
	}
	public void setApplicationCd(String applicationCd) {
		this.applicationCd = applicationCd;
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
