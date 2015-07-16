package model;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDAO {
	final private static String dbname = "midicloud";
	final private static String username = "dbpuser";
	final private static String password = "hogehoge";
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;

	public boolean addUser(User user)throws SQLException{
		Connection connection;
		String sql = "insert into userdata values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement prepStmt;

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, user.getUserID());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getMailAddress());
			pstmt.setString(5, user.getProfile());
			pstmt.setBytes(6, user.getIcon());
			pstmt.setArray(7, (Array) user.getMIDI_IDs());
			pstmt.setArray(8, (Array) user.getCommentIDs());
			pstmt.setBoolean(9, user.isManager());

			pstmt.executeUpdate();

			connection.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public User returnUser(int userID)throws SQLException{
		Connection connection;
		User user = new User();
		String sql = "select * from userdata where userID=?";

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, userID);

			ResultSet resultSet = pstmt.executeQuery();
			resultSet.next();

			String userName = resultSet.getString("username");
			String password = resultSet.getString("password");
			String mailAddress = resultSet.getString("mailAddress");
			String profile = resultSet.getString("profile");
			byte[] icon = resultSet.getBytes("icon");
			ArrayList<Integer> MIDI_IDs = null;
			ArrayList<Integer> comment_IDs = null;
			try{
				MIDI_IDs = (ArrayList<Integer>) resultSet.getArray("MIDI_IDs");
				comment_IDs = (ArrayList<Integer>) resultSet.getArray("commentIDs");
			}catch(NullPointerException e){

			}
			Boolean isManagaer = resultSet.getBoolean("isManager");

			user.setUserID(userID);
			user.setUsername(userName);
			user.setPassword(password);
			user.setMailAddress(mailAddress);
			user.setProfile(profile);
			user.setIcon(icon);
			user.setMIDI_IDs(MIDI_IDs);
			user.setCommentIDs(comment_IDs);
			user.setManager(isManagaer);

			resultSet.close();
			connection.close();

			return user;

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}


	public ArrayList<User> returnUser(ArrayList<Integer> userIDs)throws SQLException{
		Connection connection;
		User user = new User();
		String sql = "select * from userdata where userID=?";
		ArrayList<User> USERs = new ArrayList<User>();

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, username, password);

			for(Integer userID : userIDs){

				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, userID);
				ResultSet resultSet = pstmt.executeQuery();

				String userName = resultSet.getString("username");
				String password = resultSet.getString("password");
				String mailAddress = resultSet.getString("mailAddress");
				String profile = resultSet.getString("profile");
				byte[] icon = resultSet.getBytes("icon");
				ArrayList<Integer> MIDI_IDs = null;
				ArrayList<Integer> comment_IDs = null;
				try{
					MIDI_IDs = (ArrayList<Integer>) resultSet.getArray("MIDI_IDs");
					comment_IDs = (ArrayList<Integer>) resultSet.getArray("commentIDs");
				}catch(NullPointerException e){

				}
				Boolean isManagaer = resultSet.getBoolean("isManager");

				user.setUserID(userID);
				user.setUsername(userName);
				user.setPassword(password);
				user.setMailAddress(mailAddress);
				user.setProfile(profile);
				user.setIcon(icon);
				user.setMIDI_IDs(MIDI_IDs);
				user.setCommentIDs(comment_IDs);
				user.setManager(isManagaer);

				USERs.add(user);

				resultSet.close();
			}

			connection.close();
			return USERs;

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}


	public void updateUser(User user)throws SQLException{
		Connection connection;
		String sql = "update userdata set username=?,password=?,mailAddress=?,profile=?,icon=?,MIDI_IDs=?,commentIDs=?,isManager=? where userID=?";

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);


			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getMailAddress());
			pstmt.setString(4, user.getProfile());
			pstmt.setBytes(5, user.getIcon());
			pstmt.setArray(6, (Array) user.getMIDI_IDs());
			pstmt.setArray(7, (Array) user.getCommentIDs());
			pstmt.setBoolean(8, user.isManager());
			pstmt.setInt(9, user.getUserID());

			pstmt.executeUpdate();

			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void deleteUser(int userID)throws SQLException{
		Connection connection;
		String sql = "delete from userdata where userID=?";

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, userID);
			pstmt.executeUpdate();

			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public User searchUser(String mailAddress)throws SQLException{
		Connection connection;
		User user = new User();
		String sql = "select * from userdata where mailAddress=?";

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, mailAddress);

			ResultSet resultSet = pstmt.executeQuery();
			resultSet.next();

			int userid = resultSet.getInt("userID");
			String userName = resultSet.getString("username");
			String password = resultSet.getString("password");
			String profile = resultSet.getString("profile");
			byte[] icon = resultSet.getBytes("icon");
			ArrayList<Integer> MIDI_IDs = null;
			ArrayList<Integer> comment_IDs = null;
			try{
				MIDI_IDs = (ArrayList<Integer>) resultSet.getArray("MIDI_IDs");
				comment_IDs = (ArrayList<Integer>) resultSet.getArray("commentIDs");
			}catch(NullPointerException e){

			}
			Boolean isManagaer = resultSet.getBoolean("isManager");
			user.setUserID(userid);
			user.setUsername(userName);
			user.setPassword(password);
			user.setMailAddress(mailAddress);
			user.setProfile(profile);
			user.setIcon(icon);
			user.setMIDI_IDs(MIDI_IDs);
			user.setCommentIDs(comment_IDs);
			user.setManager(isManagaer);

			resultSet.close();
			connection.close();

			return user;
		}catch(Exception e){
			e.printStackTrace();

			return null;
		}
	}
	public static List<String> convert(String[] array){
		return new ArrayList<String>(Arrays.asList(array));
	}


	public int searchNoUserID()throws SQLException{
		Connection connection;
		String sql = "select max(userID) from userdata";

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, username, password);
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

	public List<User> getUserList() {
		ArrayList<User> users = new ArrayList<User>();
		Connection connection;
		String sql = "SELECT * FROM userdata";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			ResultSet result = pstmt.executeQuery();

			while(result.next()) {
				User user = new User();
				user.setUserID(result.getInt(1));
				user.setUsername(result.getString(2));
				user.setPassword(result.getString(3));
				user.setMailAddress(result.getString(4));
				user.setProfile(result.getString(5));
				user.setIcon(result.getBytes(6));
				ArrayList<Integer> MIDI_IDs = null;
				ArrayList<Integer> comment_IDs = null;
				try{
					MIDI_IDs = (ArrayList<Integer>) result.getArray("MIDI_IDs");
					comment_IDs = (ArrayList<Integer>) result.getArray("commentIDs");
				}catch(NullPointerException e){

				}
				user.setManager(result.getBoolean(9));
				users.add(user);
			}
			result.close();
			connection.close();
			return users;

		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
