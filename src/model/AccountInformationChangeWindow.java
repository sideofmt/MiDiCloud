package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Translate;
import model.User;
import model.UserManager;

/**
 * Servlet implementation class AccountInformationChangeWindow
 */
@WebServlet("/AccountInformationChangeWindow")
public class AccountInformationChangeWindow extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountInformationChangeWindow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null){
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else{
			this.getServletContext().getRequestDispatcher("/changeProfile.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		User user = (User)session.getAttribute("user");
		session.setAttribute("user",user);
		System.out.println(user);
		if(request.getParameter("change") == null){
			if(session.getAttribute("user")==null){
				this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
			else{
				this.getServletContext().getRequestDispatcher("/changeProfile.jsp").forward(request, response);
			}
		}else{
	//		User user = new User();
			UserManager manager = new UserManager();
			Translate translate = new Translate();
	//		HttpSession session = request.getSession();

			user.setUsername(request.getParameter("name"));
			user.setProfile(request.getParameter("profile"));
//			System.out.println("unko:"+request.getParameter("icon"));
//			System.out.println(((String)(request.getParameter("icon"))).length());
			if(((String)(request.getParameter("icon"))).length() != 0)user.setIcon(translate.fileLoad(request.getParameter("icon")));
			try {
				manager.updateUser(user);
			} catch (SQLException e) {
				e.printStackTrace();
			}
//			request.setAttribute("user", user);
			this.getServletContext().getRequestDispatcher("/AccountInfoWindow")
					.forward(request, response);
		}
	}
}
