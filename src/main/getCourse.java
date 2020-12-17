package main;

import java.util.List;

public class getCourse{
	
	public class Info{
	    public int reservationlimit;
	    public int reservationcount;
		public int getReservationlimit() {
			return reservationlimit;
		}
		public void setReservationlimit(int reservationlimit) {
			this.reservationlimit = reservationlimit;
		}
		public int getReservationcount() {
			return reservationcount;
		}
		public void setReservationcount(int reservationcount) {
			this.reservationcount = reservationcount;
		}
	}
	
	public class Reservation{
	    public String id;
	    public String startdate;
	    public String starttime;
	    public String enddate;
	    public String endtime;
	    public List<String> columns;
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
	}
	
	public class Root{
	    public List<String> columnheaders;
	    public Info info;
	    public List<Reservation> reservations;
		public List<String> getColumnheaders() {
			return columnheaders;
		}
		public void setColumnheaders(List<String> columnheaders) {
			this.columnheaders = columnheaders;
		}
		public Info getInfo() {
			return info;
		}
		public void setInfo(Info info) {
			this.info = info;
		}
		public List<Reservation> getReservations() {
			return reservations;
		}
		public void setReservations(List<Reservation> reservations) {
			this.reservations = reservations;
		}
	}
}