package model;

public class Report {
	private int reportID;
	private int userID;
	private int reportedUserID;
	private int reportedmidiID;

	public int getReportID() {
		return reportID;
	}
	public void setReportID(int reportID) {
		this.reportID = reportID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getReportedUserID() {
		return reportedUserID;
	}
	public void setReportedUserID(int reportedUserID) {
		this.reportedUserID = reportedUserID;
	}
	public int getReportedmidiID() {
		return reportedmidiID;
	}
	public void setReportedmidiID(int reportedmidiID) {
		this.reportedmidiID = reportedmidiID;
	}
}
