package test;

import java.io.File;
import java.sql.SQLException;

import model.User;
import model.UserManager;

public class testUser {

	public testUser(){
		super();
	}

	public static void main(String arg[]){

	User user = new User();
	UserManager usermanager = new UserManager();

	int userID = 0;
	String username = "testuser";
	String mailAddress = "test.example.com";
	String password = "pass";
	String profile = "my profile.";
	byte[] icon;

	File file = new File("C:/Users/yu-12_000/Pictures/GIMP/profile.jpg");
	byte[] readBinary = new byte[(int)file.length()];
	icon = readBinary;

	user.setUserID(userID);
	user.setUsername(username);
	user.setMailAddress(mailAddress);
	user.setPassword(password);
	user.setProfile(profile);
//	user.setIcon(icon);

	try {
		usermanager.createUserData(user);
	} catch (SQLException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}

	User usershow = null;

	try {
		usershow = usermanager.getUser(mailAddress);
	} catch (SQLException e1) {
		// TODO 自動生成された catch ブロック
		e1.printStackTrace();
	}


	System.out.println(usershow);

	try {
		System.out.println("login:"+usermanager.login(mailAddress, password));
	} catch (SQLException e1) {
		// TODO 自動生成された catch ブロック
		e1.printStackTrace();
	}



	try {
		usermanager.deleteUser(userID);
	} catch (SQLException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}



	}

}
