package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ReportDAO extends Object implements Serializable{
	final private static String dbname = "MiDiCloud";
//	final private static String user = "wspuser";
	final private static String user = "dbpuser";
	final private static String password = "hogehoge";
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public void add(Report report){
		String sql = "insert into report values (?, ?, ?, ?)";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, report.getReportID());
			pstmt.setInt(2, report.getUserID());
			pstmt.setInt(3, report.getReportedUserID());
			pstmt.setInt(4, report.getReportedmidiID());
			pstmt.executeUpdate();

			pstmt.close();
			connection.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public void delete(int reportedUserID){
		String sql = "delete from report where reportID = ?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, reportedUserID);
			pstmt.executeUpdate();

			pstmt.close();
            connection.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public ArrayList<Report> returnReports(){
		int reportID;
		int userID;
		int reportedUserID;
		int reportedmidiID;
		ArrayList<Report> repList = new ArrayList<Report>();
		String sql = "select * from report order by reportID asc";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);

			while(resultSet.next()){
				Report rep = new Report();
				reportID = resultSet.getInt("reportID");
				userID = resultSet.getInt("userID");
				reportedUserID = resultSet.getInt("reportedUserID");
				reportedmidiID = resultSet.getInt("reportedmidiID");
				rep.setReportID(reportID);
				rep.setUserID(userID);
				rep.setReportedUserID(reportedUserID);
				rep.setReportedmidiID(reportedmidiID);

				repList.add(rep);
			}

            resultSet.close();
            statement.close();
            connection.close();

    		return repList;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}