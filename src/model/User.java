package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class User {

	private int userID;
	private String username;
	private String password;
	private String mailAddress;
	private String profile;
	private byte[] icon;
	private ArrayList<Integer> MIDI_IDs;
	private ArrayList<Integer> commentIDs;
	private boolean isManager;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public byte[] getIcon() {
		return icon;
	}
	public void setIcon(byte[] icon) {
		this.icon = icon;
	}
	public ArrayList<Integer> getMIDI_IDs() {
		return MIDI_IDs;
	}
	public void setMIDI_IDs(ArrayList<Integer> mIDI_IDs) {
		MIDI_IDs = mIDI_IDs;
	}
	public ArrayList<Integer> getCommentIDs() {
		return commentIDs;
	}
	public void setCommentIDs(ArrayList<Integer> commentIDs) {
		this.commentIDs = commentIDs;
	}
	public boolean isManager() {
		return isManager;
	}
	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username
				+ ", password=" + password + ", mailAddress=" + mailAddress
				+ ", profile=" + profile + ","
				+ ", MIDI_IDs=" + MIDI_IDs + ", commentIDs=" + commentIDs
				+ ", isManager=" + isManager + "]";
	}

}
