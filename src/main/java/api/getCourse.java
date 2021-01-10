package main.java.api;

import java.util.List;

public class getCourse {

	public String lektId;
	public String value;
	public String decimalId;
	public String plats;
	public String id;
	public String startdate;
	public String starttime;
	public String enddate;
	public String endtime;
	public List<String> columns;

	
	public String getLektId() {
		return lektId;
	}

	public void setLektId(String lektId) {
		this.lektId = lektId;
	}

	public String getPlats() {
		return plats;
	}

	public void setPlats(String plats) {
		this.plats = plats;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDecimalId() {
		return decimalId;
	}

	public void setDecimalId(String decimalId) {
		this.decimalId = decimalId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public void setColumnsPos(int pos, String info) {
		this.columns.set(pos, info);
	}

	@Override
	public String toString() {
		return "Value: " + this.getValue()+ " decimalId: " + this.getDecimalId()+ " ID: " + this.getId() + ", Date: " + this.getStartdate() + ", Time: " + this.getStarttime()
				+ ", Columns " + this.getColumns() + ", Plats " + this.getPlats();
	}
}