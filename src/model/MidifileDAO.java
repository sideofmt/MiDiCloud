package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MidifileDAO {
	final private static String dbname = "midifile";
	final private static String user = "wspuser";
	final private static String password = "hogehoge";
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;

	public void updateMidifile(Midifile midifile) {
		Connection connection;
		String sql = "UPDATE midifile SET title = ?,explanation = ?,favorite = ?,"
				+ "midifile = ?,userID = ?,commentID = ?,data = ? WHERE midiID = ?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, midifile.getTitle());
			pstmt.setString(2, midifile.getExplanation());
			pstmt.setInt(3, midifile.getFavorite());
			pstmt.setString(4, midifile.getMidifile());
			pstmt.setInt(5, midifile.getUserID());
			pstmt.setInt(6, midifile.getCommentID());
			pstmt.setObject(7, midifile.getDate());
			pstmt.setInt(8, midifile.getMidiID());
			pstmt.executeQuery();

			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteMidifile(Midifile midifile) {
		Connection connection;
		String sql = "DELETE FROM midifile WHERE midiID = ?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, midifile.getMidiID());
			pstmt.executeQuery();

			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void addMidifile(Midifile midifile) {
		Connection connection;
		String sql = "INSERT INTO midifile VALUES(?,?,?,?,?,?,?,?)";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, midifile.getMidiID());
			pstmt.setString(2, midifile.getTitle());
			pstmt.setString(3, midifile.getExplanation());
			pstmt.setInt(4, midifile.getFavorite());
			pstmt.setString(5, midifile.getMidifile());
			pstmt.setInt(6, midifile.getUserID());
			pstmt.setInt(7, midifile.getCommentID());
			pstmt.setObject(8, midifile.getDate());
			pstmt.executeQuery();

			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
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
				midifile.setMidifile(result.getString(5));
				midifile.setUserID(result.getInt(6));
				midifile.setCommentID(result.getInt(7));
				midifile.setDate((Calendar) result.getObject(8));
			}

			result.close();
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return midifile;
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
				midifile.setMidifile(result.getString(5));
				midifile.setUserID(result.getInt(6));
				midifile.setCommentID(result.getInt(7));
				midifile.setDate((Calendar) result.getObject(8));
				midifiles.add(midifile);
			}

			result.close();
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return midifiles;
	}
	public List<Midifile> getMidiList(String title) {
		ArrayList<Midifile> midifiles = new ArrayList<Midifile>();
		Connection connection;
		String sql = "SELECT * FROM midifile WHERE title LIKE '%?%'";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, title);
			ResultSet result = pstmt.executeQuery();

			while(result.next()) {
				Midifile midifile = new Midifile();
				midifile.setMidiID(result.getInt(1));
				midifile.setTitle(result.getString(2));
				midifile.setExplanation(result.getString(3));
				midifile.setFavorite(result.getInt(4));
				midifile.setMidifile(result.getString(5));
				midifile.setUserID(result.getInt(6));
				midifile.setCommentID(result.getInt(7));
				midifile.setDate((Calendar)result.getObject(8));
				midifiles.add(midifile);
			}

			result.close();
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return midifiles;
	}
	public List<Midifile> getMidiRankingList() {
		ArrayList<Midifile> midifiles = new ArrayList<Midifile>();
		Connection connection;
		String sql = "SELECT * FROM midifile ORDER BY favorite ASC";

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
				midifile.setMidifile(result.getString(5));
				midifile.setUserID(result.getInt(6));
				midifile.setCommentID(result.getInt(7));
				midifile.setDate((Calendar)result.getObject(8));
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
		String sql = "SELECT * FROM midifile ORDER BY date ASC";

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
				midifile.setMidifile(result.getString(5));
				midifile.setUserID(result.getInt(6));
				midifile.setCommentID(result.getInt(7));
				midifile.setDate((Calendar)result.getObject(8));
				midifiles.add(midifile);
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
