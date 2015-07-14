package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReportDAO extends Object implements Serializable{
	final private static String dbname = "MidiCloud";
	final private static String user = "dbpuser";
	final private static String password = "hogehoge";
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public boolean add(Report report){
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

			connection.close();
			return true;

		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(int reportedUserID){
		String sql = "delete from report where midiID = ?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, reportedUserID);
			pstmt.executeUpdate();

            connection.close();
            return true;

		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<Report> returnReports(){
		int reportID;
		int userID;
		int reportedUserID;
		int reportedmidiID;
		ArrayList<Report> repList = new ArrayList<Report>();
		Report rep = new Report();
		String sql = "select * from report";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);

			while(resultSet.next()){
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

	public int searchNoUserID()throws SQLException{
		Connection connection;
		String sql = "select max(reportID) from report";

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			int id=0;
			ResultSet resultSet;
			resultSet = pstmt.executeQuery();
			if(resultSet.wasNull()) {
				id = 1;
			} else {
				resultSet.next();
				id = resultSet.getInt(1) + 1;
			}

			resultSet.close();
			connection.close();

			return id;

		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
}