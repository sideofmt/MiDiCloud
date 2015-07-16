package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class CommentDAO extends Object{
	final private static String dbname = "midicloud";
	final private static String user = "dbpuser";
	final private static String password = "hogehoge";
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public boolean add(Comment comment){
		String sql = "insert into comment values (?, ?, ?, ?)";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, comment.getCommentID());
			pstmt.setString(2, comment.getComment());
			pstmt.setInt(3, comment.getUserID());
			pstmt.setInt(4,  comment.getMidiID());

			pstmt.executeUpdate();
			connection.close();
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<Comment> returnComment(int midiID){
		int commentID;
		String comment;
		int userID;
		String sql = "select * from comment where midiID = ?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
//			statement = connection.createStatement();
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, midiID);

			resultSet = pstmt.executeQuery();
			ArrayList<Comment> comList = new ArrayList<Comment>();

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

            resultSet.close();
//            statement.close();
            connection.close();

    		return comList;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public void delete(int midiID){
		String sql = "delete from comment where midiID = ?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, midiID);

			pstmt.executeUpdate();
            connection.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public void deleteByID(int commentID){
		String sql = "delete from comment where commentID = ?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, commentID);

			pstmt.executeUpdate();
            connection.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public void deleteU(int userID){
		String sql = "delete from comment where userID = ?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, userID);

			pstmt.executeUpdate();
            connection.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public int searchNoID()throws SQLException{
		Connection connection;
		String sql = "select max(commentID) from comment";

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
