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

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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

		if(request.getParameter("login")!=null){
			//ログインボタンを押していれば遷移処理をする。

		User user = null;
		UserManager usermanager = new UserManager();

		String mailAddress = request.getParameter("email");
		String password = request.getParameter("pass");
		System.out.println(request.getParameter("login"));

		int result = 0;

		try {
			result = usermanager.login(mailAddress, password);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		if(result==1 || result==2){
			try {
				user = usermanager.getUser(mailAddress);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		if(result==1){
			this.getServletContext().getRequestDispatcher("/memberTop.jsp").forward(request, response);
		}else if(result==2){
			this.getServletContext().getRequestDispatcher("/managerTop.jsp").forward(request, response);
		}else{
			request.setAttribute("error", "<div class=\"alert alert-danger\" role=\"alert\"><span class=\"glyphicon glyphicon-exclamation-sign\" aria-hidden=\"true\"></span><span class=\"sr-only\">Error:</span>メールアドレスまたはパスワードが間違っています</div>");
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
		}
	}

}
