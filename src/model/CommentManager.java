package model;

import java.sql.SQLException;
import java.util.ArrayList;


public class CommentManager {

	public boolean addComment(Comment com) throws SQLException{
		CommentDAO comDAO = new CommentDAO();

		com.setCommentID(comDAO.searchNoID());
		boolean flag = comDAO.add(com);
		return flag;

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
