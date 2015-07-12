package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Midifile implements Serializable{
	private int midiID;
	private String title;
	private String explanation;
	private int favorite;
	private byte[] midifile;
	private int userID;
	private Timestamp date;

	public int getMidiID() {
		return midiID;
	}
	public void setMidiID(int midiID) {
		this.midiID = midiID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public int getFavorite() {
		return favorite;
	}
	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}
	public byte[] getMidifile() {
		return midifile;
	}
	public void setMidifile(byte[] midifile) {
		this.midifile = midifile;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
}
