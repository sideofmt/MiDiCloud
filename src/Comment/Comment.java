package Comment;

public class Comment {
	private int commentID;
	private String comment;
	private int userID;
	private int midiID;

	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getMidiID() {
		return midiID;
	}
	public void setMidiID(int midiID) {
		this.midiID = midiID;
	}
}
