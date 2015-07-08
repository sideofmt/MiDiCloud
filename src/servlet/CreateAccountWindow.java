package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CreateAccountWindow
 */
@WebServlet("/CreateAccountWindow")
public class CreateAccountWindow extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccountWindow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		User user = new User();
		UserManager manager = new UserManager();

		/*Userデータの入力*/
		user.setUserID(0);
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setMailAddress(request.getParameter("mailAddress"));
		user.setProfile("");
		user.setIcon(request.getParameter("icon"));
		user.setMIDI_IDs(null);
		user.setCommentIDs(null);
		user.IsManager(false);

		boolean result = false;

		/*入力された文字の長さを確認*/
		if(user.getName().length() <= 0 || user.getName().length() > 30 ||
				user.getMailAddress().length() <= 0 || user.getMailAddress().length() > 30 ||
					user.getPassword().length() <= 0 || user.getPassword().length() > 30){

		}


		/*メールアドレスが他者と被っていないかを確認*/
		try{
			result = manager.check(user.getMailAddress());
		}catch (SQLException e){
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		if(result){
			this.getServletContext().getRequestDispatcher("/completeMakeAccount.jsp").forward(request, response);
		}else{
			this.getServletContext().getRequestDispatcher("/makeAccount.jsp").forward(request, response);
		}
	}

}
