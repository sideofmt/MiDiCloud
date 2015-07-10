package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class CommentDAO extends Object implements Serializable{
	final private static String dbname = "MiDiCloud";
	//final private static String user = "wspuser";
	final private static String user = "dbpuser";
	final private static String password = "hogehoge";
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public void add(Comment comment){
		String sql = "insert into comment values(?, ?, ?, ?)";
		PreparedStatement pstmt;

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, comment.getCommentID());
			pstmt.setString(2, comment.getComment());
			pstmt.setInt(3, comment.getUserID());
			pstmt.setInt(4,  comment.getMidiID());
			pstmt.executeUpdate();

			pstmt.close();
			connection.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public ArrayList<Comment> returnComment(int midiID){
		int commentID;
		String comment;
		int userID;
		ArrayList<Comment> comList = new ArrayList<Comment>();
		String sql = "select * from comment where midiID = ? order by commentid asc";
		PreparedStatement pstmt;

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, midiID);
			resultSet = pstmt.executeQuery();

			while(resultSet.next()){
				Comment com = new Comment();
				commentID = resultSet.getInt("commentID");
				comment = resultSet.getString("comment");
				userID = resultSet.getInt("userID");

				com.setCommentID(commentID);
				com.setComment(comment);
				com.setUserID(userID);
				com.setMidiID(midiID);

				comList.add(com);
			}

			pstmt.close();
            resultSet.close();
            statement.close();
            connection.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return comList;
	}

	public void delete(int midiID){
		String sql = "delete from comment where midiID = ?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, midiID);
			pstmt.executeUpdate();

			pstmt.close();
            connection.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
