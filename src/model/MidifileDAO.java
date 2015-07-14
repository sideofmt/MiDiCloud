package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MidifileDAO {
	final private static String dbname = "MidiCloud";
	final private static String user = "dbpuser";
	final private static String password = "hogehoge";
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;

	public void updateMidifile(Midifile midifile) {
		Connection connection;
		String sql = "UPDATE midifile SET title = ?,explanation = ?,favorite = ?,"
				+ "midifile = ?,userID = ?,date = ? WHERE midiID = ?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, midifile.getTitle());
			pstmt.setString(2, midifile.getExplanation());
			pstmt.setInt(3, midifile.getFavorite());
			pstmt.setBytes(4, midifile.getMidifile());
			pstmt.setInt(5, midifile.getUserID());
			pstmt.setTimestamp(6, midifile.getDate());
			pstmt.setInt(7, midifile.getMidiID());
			pstmt.executeUpdate();

			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteMidifile(int midiID) {
		Connection connection;
		String sql = "DELETE FROM midifile WHERE midiID = ?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, midiID);
			pstmt.executeUpdate();

			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public int addMidifile(Midifile midifile) {
		Connection connection,connection2;
		String sql = "INSERT INTO midifile VALUES(?,?,?,?,?,?,?)";
		String sql2 = "SELECT MAX(midiID) FROM midifile";
		int id = 0;

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			connection2 = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			PreparedStatement pstmt2 = connection2.prepareStatement(sql2);

			pstmt2.executeQuery();
			ResultSet result = pstmt2.executeQuery();
			//result.next();
			if(result.wasNull()) {
				id = 1;
			} else {
				result.next();
				id = result.getInt(1) + 1;
			}
			result.close();

			pstmt.setInt(1, id);
			pstmt.setString(2, midifile.getTitle());
			pstmt.setString(3, midifile.getExplanation());
			pstmt.setInt(4, midifile.getFavorite());
			pstmt.setBytes(5, midifile.getMidifile());
			pstmt.setInt(6, midifile.getUserID());
			pstmt.setTimestamp(7, midifile.getDate());
			pstmt.executeUpdate();

			connection.close();

			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	public Midifile getMidifile(int midiID) {
		Midifile midifile = new Midifile();
		Connection connection;
		String sql = "SELECT * FROM midifile WHERE midiID = ?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, midiID);
			ResultSet result = pstmt.executeQuery();

			if(result.next()) {
				midifile.setMidiID(result.getInt(1));
				midifile.setTitle(result.getString(2));
				midifile.setExplanation(result.getString(3));
				midifile.setFavorite(result.getInt(4));
				midifile.setMidifile(result.getBytes(5));
				midifile.setUserID(result.getInt(6));
				midifile.setDate(result.getTimestamp(7));
			}

			result.close();
			connection.close();
			return midifile;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	public List<Midifile> getMidiList(int userID) {
		ArrayList<Midifile> midifiles = new ArrayList<Midifile>();
		Connection connection;
		String sql = "SELECT * FROM midifile WHERE userID = ?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, userID);
			ResultSet result = pstmt.executeQuery();

			while(result.next()) {
				Midifile midifile = new Midifile();
				midifile.setMidiID(result.getInt(1));
				midifile.setTitle(result.getString(2));
				midifile.setExplanation(result.getString(3));
				midifile.setFavorite(result.getInt(4));
				midifile.setMidifile(result.getBytes(5));
				midifile.setUserID(result.getInt(6));
				midifile.setDate(result.getTimestamp(7));
				midifiles.add(midifile);
			}

			result.close();
			connection.close();

			return midifiles;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	public List<Midifile> getMidiList(String title) {
		ArrayList<Midifile> midifiles = new ArrayList<Midifile>();
		Connection connection;
		String sql = "SELECT * FROM midifile WHERE title LIKE ?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, "%" + title + "%");
			ResultSet result = pstmt.executeQuery();

			while(result.next()) {
				Midifile midifile = new Midifile();
				midifile.setMidiID(result.getInt(1));
				midifile.setTitle(result.getString(2));
				midifile.setExplanation(result.getString(3));
				midifile.setFavorite(result.getInt(4));
				midifile.setMidifile(result.getBytes(5));
				midifile.setUserID(result.getInt(6));
				midifile.setDate(result.getTimestamp(7));
				midifiles.add(midifile);
			}

			result.close();
			connection.close();
			return midifiles;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<Midifile> getMidiRankingList() {
		ArrayList<Midifile> midifiles = new ArrayList<Midifile>();
		Connection connection;
		String sql = "SELECT * FROM midifile ORDER BY favorite DESC";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			ResultSet result = pstmt.executeQuery();

			while(result.next()) {
				Midifile midifile = new Midifile();
				midifile.setMidiID(result.getInt(1));
				midifile.setTitle(result.getString(2));
				midifile.setExplanation(result.getString(3));
				midifile.setFavorite(result.getInt(4));
				midifile.setMidifile(result.getBytes(5));
				midifile.setUserID(result.getInt(6));
				midifile.setDate(result.getTimestamp(7));
				midifiles.add(midifile);
			}

			result.close();
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return midifiles;
	}
	public List<Midifile> getMidiArrivalList() {
		ArrayList<Midifile> midifiles = new ArrayList<Midifile>();
		Connection connection;
		String sql = "SELECT * FROM midifile ORDER BY date DESC";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet result = null;

			try{
			result = pstmt.executeQuery();

			while(result.next()) {
				Midifile midifile = new Midifile();
				midifile.setMidiID(result.getInt(1));
				midifile.setTitle(result.getString(2));
				midifile.setExplanation(result.getString(3));
				midifile.setFavorite(result.getInt(4));
				midifile.setMidifile(result.getBytes(5));
				midifile.setUserID(result.getInt(6));
				midifile.setDate(result.getTimestamp(7));
				midifiles.add(midifile);
			}

			}catch(NullPointerException e){
				System.out.println("NullPointerExceptionが投げられました");
				e.printStackTrace();
			}

			result.close();
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return midifiles;
	}
	public void deleteMidiList(int userID) {
		Connection connection;
		String sql = "DELETE FROM midifile WHERE userID = ?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, userID);
			pstmt.executeQuery();

			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
