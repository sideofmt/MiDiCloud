package Comment;

import java.util.ArrayList;


public class CommentManager {
	public void addComment(String comment,int commentID,int userID, int midiID){
		Comment com = new Comment();
		CommentDAO comDAO = new CommentDAO();

		com.setComment(comment);
		com.setCommentID(commentID);
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
