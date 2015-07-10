package model;

import java.util.ArrayList;

public class ReportManager {
	public void add(int reportID, int userID, int reportedUserID, int reportedmidiID){
		Report report = new Report();
		ReportDAO repDAO = new ReportDAO();

		report.setReportID(reportID);
		report.setUserID(userID);
		report.setReportedUserID(reportedUserID);
		report.setReportedmidiID(reportedmidiID);

		repDAO.add(report);
	}

	public void remove(int reportedUserID){
		ReportDAO repDAO = new ReportDAO();
		repDAO.delete(reportedUserID);
	}

	public ArrayList<Report> getReportList(){
		ReportDAO repDAO = new ReportDAO();
		ArrayList<Report> repList = new ArrayList<Report>();

		repList = repDAO.returnReports();

		return repList;
	}
}
