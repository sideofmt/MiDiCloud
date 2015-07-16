package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

/**
 * Servlet implementation class MidiUploadCompleteWindow
 */
@WebServlet("/MidiUploadCompleteWindow")
public class MidiUploadCompleteWindow extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MidiUploadCompleteWindow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println("MidiUploadCompleteWindowのdoGetが呼ばれました");
		if(session.getAttribute("user")==null){
			this.getServletContext().getRequestDispatcher("/Login").forward(request, response);
		} else {
			this.getServletContext().getRequestDispatcher("/midiUploadComplete.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("MidiUploadCompleteWindowのdoPostが呼ばれました");

		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");
		session.setAttribute("user",(User)session.getAttribute("user"));
		request.setAttribute("userID",user.getUserID());
		this.getServletContext().getRequestDispatcher("/memberTop.jsp" ).forward(request, response);
	}

}
