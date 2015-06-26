package model;

import java.sql.*;

public class UserDAO {
	final private static String dbname = "MidiCloud";
	final private static String user = "dbpuser";
	final private static String password = "hogehoge";
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;

	public boolean check(User user)throws SQLException{
		boolean result = false;
		Connection connection;
		String sql = "select * from member where name=? and pass=?";

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPass());

			ResultSet resultSet = pstmt.executeQuery();
			if(resultSet.next()) result = true;

			resultSet.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
