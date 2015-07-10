package model;

import java.util.ArrayList;


public class CommentManager {
	public void addComment(int commentID,String comment,int userID, int midiID){
		Comment com = new Comment();
		CommentDAO comDAO = new CommentDAO();

		com.setCommentID(commentID);
		com.setComment(comment);
		com.setUserID(userID);
		com.setMidiID(midiID);

		comDAO.add(com);
	}

	public void delComment(int midiID){
		CommentDAO comDAO = new CommentDAO();
		comDAO.delete(midiID);
	}

	public ArrayList<Comment> returnComments(int midiID){
		CommentDAO comDAO = new CommentDAO();
		ArrayList<Comment> comList = new ArrayList<Comment>();

		comList = comDAO.returnComment(midiID);
		return comList;
	}
}