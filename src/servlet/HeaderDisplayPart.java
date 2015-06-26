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
import model.UserDAO;

/**
 * Servlet implementation class HeaderDisplayPart
 */
@WebServlet("/HeaderDisplayPart")
public class HeaderDisplayPart extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HeaderDisplayPart() {
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
	}

	public void header(HttpServletRequest request, HttpServletResponse response){

		request.setCharacterEncoding("UTF-8");

		User member = new User();
		UserDAO dao = new UserDAO();

		member.setName(request.getParameter("name"));
		member.setPass(request.getParameter("pass"));

		boolean result = false;
		try{
			result = dao.check(member);
		}catch(SQLException e){
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		session.setAttribute("member", member);
		if(result){
			this.getServletContext().getRequestDispatcher("/member.jsp").forward(request, response);
		}else{
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}


	}

}
