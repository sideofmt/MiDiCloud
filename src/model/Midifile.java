package model;

import java.io.Serializable;
import java.util.Calendar;

public class Midifile implements Serializable{
	private int midiID;
	private String title;
	private String explanation;
	private int favorite;
	private String midifile;
	private int userID;
	private int commentID;
	private Calendar date;

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
	public String getMidifile() {
		return midifile;
	}
	public void setMidifile(String midifile) {
		this.midifile = midifile;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
}
