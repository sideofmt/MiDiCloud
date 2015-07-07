package model;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
	final private static String dbname = "MidiCloud";
	final private static String username = "dbpuser";
	final private static String password = "hogehoge";
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;

	public boolean addUser(User user)throws SQLException{
		Connection connection;
		String sql = "insert into user values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, user.getUserID());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getMailAddress());
			pstmt.setString(5, user.getProfile());
			pstmt.setByte(6, user.getIcon());
			pstmt.setObject(7, user.getMIDI_IDs());
			pstmt.setObject(8, user.getCommentIDs());
			pstmt.setBoolean(9, user.isManager());

			ResultSet resultSet = pstmt.executeQuery();

			resultSet.close();
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
		String sql = "select * from user where userID=?";

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, userID);

			ResultSet resultSet = pstmt.executeQuery();

			String userName = resultSet.getString("username");
			String password = resultSet.getString("password");
			String mailAddress = resultSet.getString("mailAddress");
			String profile = resultSet.getString("profile");
			Byte icon = resultSet.getByte("icon");
			ArrayList<Integer> MIDI_IDs = (ArrayList<Integer>)resultSet.getObject("MIDI_IDs");
			ArrayList<Integer> comment_IDs = (ArrayList<Integer>) resultSet.getObject("commentIDs");
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
		String sql = "select * from user where userID=?";
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
				Byte icon = resultSet.getByte("icon");
				ArrayList<Integer> MIDI_IDs = (ArrayList<Integer>)resultSet.getObject("MIDI_IDs");
				ArrayList<Integer> comment_IDs = (ArrayList<Integer>) resultSet.getObject("commentIDs");
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
		String sql = "update user set username=?,password=?,mailAddress=?,profile=?,icon=?,MIDI_IDs=?,commentIDs=?,isManagaer=? where userID=?";

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);


			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getMailAddress());
			pstmt.setString(4, user.getProfile());
			pstmt.setByte(5, user.getIcon());
			pstmt.setObject(6, user.getMIDI_IDs());
			pstmt.setObject(7, user.getCommentIDs());
			pstmt.setBoolean(8, user.isManager());
			pstmt.setInt(9, user.getUserID());

			ResultSet resultSet = pstmt.executeQuery();

			resultSet.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void deleteUser(int userID)throws SQLException{
		Connection connection;
		String sql = "delete from user where userID=?";

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, userID);

			ResultSet resultSet = pstmt.executeQuery();

			resultSet.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public User searchUser(String mailAddress)throws SQLException{
		Connection connection;
		User user = new User();
		String sql = "select * from user where mailAddress=?";

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, mailAddress);

			ResultSet resultSet = pstmt.executeQuery();

			int userid = resultSet.getInt("userID");
			String userName = resultSet.getString("username");
			String password = resultSet.getString("password");
			String profile = resultSet.getString("profile");
			Byte icon = resultSet.getByte("icon");
			ArrayList<Integer> MIDI_IDs = (ArrayList<Integer>)resultSet.getObject("MIDI_IDs");
			ArrayList<Integer> comment_IDs = (ArrayList<Integer>) resultSet.getObject("commentIDs");
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


	public int searchNoUserID()throws SQLException{
		Connection connection;
		String sql = "select * from user where userID=?";

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet resultSet;

			int i=1;
			int id;

			while(true){
				pstmt.setInt(1,i );
				resultSet = pstmt.executeQuery();

				id = resultSet.getInt("userID");
				if(id==0){
					break;
				}
				i++;
			}
			resultSet.close();
			connection.close();
			return i;

		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}


}
