package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.UserManager;
import model.Translate;

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
		// TODO Auto-generated method stub]

		System.out.println("CreateAccountWindowのdoGetが呼び出されました");

		this.getServletContext().getRequestDispatcher("/makeAccount.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		System.out.println("CreateAccountWindowのdoPostが呼び出されました");


		if ("midicloud".equals(request.getParameter("action"))) {
			System.out.println("MidiCloudが押されました");
			this.getServletContext().getRequestDispatcher("/Login").forward(request, response);
		}
		else if(request.getParameter("createAccount") != null){


		System.out.println("アカウント作成ボタンが押されました");

		User user = new User();
		UserManager usermanager = new UserManager();

		int userID = 0;
		String username = request.getParameter("name");
		String mailAddress = request.getParameter("email");
		String password = request.getParameter("pass");
		String profile =request.getParameter("profile");

		Translate translate = new Translate();

		byte[] icon = translate.fileLoad(request.getParameter("icon"));




		user.setUserID(userID);
		user.setUsername(username);
		user.setMailAddress(mailAddress);
		user.setPassword(password);
		user.setProfile(profile);
		user.setIcon(icon);

		System.out.println(user);

		boolean flag=false;

		try {
			flag = usermanager.createUserData(user);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		if(flag){
			this.getServletContext().getRequestDispatcher("/completeMakeAccount.jsp").forward(request, response);
		}else{
			request.setAttribute("error","<div class=\"alert alert-danger\" role=\"alert\">\n<span class=\"glyphicon glyphicon-exclamation-sign\" aria-hidden=\"true\"></span>\n<span class=\"sr-only\">Error:</span>\n  ユーザーを追加できませんでした\n</div>");
			this.getServletContext().getRequestDispatcher("/makeAccount.jsp").forward(request, response);
		}
		}
	}

}
