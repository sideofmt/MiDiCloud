package Report;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Comment.Comment;

public class ReportDAO extends Object implements Serializable{
	final private static String dbname = "comment";
	final private static String user = "wspuser";
	final private static String password = "hogehoge";
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public void add(Report report){
		String sql = "insert into comment values (?, ?, ?, ?)";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, report.getReportID());
			pstmt.setInt(2, report.getUserID());
			pstmt.setInt(3, report.getReportedUserID());
			pstmt.setInt(4, report.getReportedmidiID());

			connection.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public void delete(int reportedUserID){
		String sql = "delete from comment where midiID = ?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, reportedUserID);

            connection.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public ArrayList<Report> returnReport(int midiID){
		int commentID;
		String comment;
		int userID;
		Comment com = new Comment();
		ArrayList<Comment> comList = new ArrayList<Comment>();
		String sql = "select * from comment";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);

			while(resultSet.next()){
				commentID = resultSet.getInt("commentID");
				comment = resultSet.getString("comment");
				userID = resultSet.getInt("userID");

				com.setCommentID(commentID);
				com.setComment(comment);
				com.setUserID(userID);
				com.setMidiID(midiID);

				comList.add(com);
			}

            resultSet.close();
            statement.close();
            connection.close();

    		return comList;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}