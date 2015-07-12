package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Midifile;
import model.MidifileManager;
import model.User;
import model.UserManager;

/**
 * Servlet implementation class MemberTopWindow
 */
@WebServlet("/MemberTopWindow")
public class MemberTopWindow extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberTopWindow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Midifile> midi = new ArrayList<Midifile>();
	   	List<Midifile> midiRank = new ArrayList<Midifile>();
		List<Midifile> midiNew = new ArrayList<Midifile>();
		MidifileManager manager = new MidifileManager();

		midiRank = manager.getRanking();
		midiNew = manager.getArrival();

//		User user =  (User)request.getAttribute("user");
		UserManager man = new UserManager();
		User user = null;
		try {
			user = man.getUser("ket@gmail.com");
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		request.setAttribute("midiRank",midiRank);
		request.setAttribute("midiNew",midiNew);
		request.setAttribute("user", user);

		this.getServletContext().getRequestDispatcher("/memberTop.jsp").forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Hi");
		request.setCharacterEncoding("UTF-8");
		User user = (User)request.getAttribute("user");

		this.getServletContext().getRequestDispatcher("/new.jsp").forward(request, response);
		this.getServletContext().getRequestDispatcher("/ranking.jsp").forward(request, response);
		this.getServletContext().getRequestDispatcher("").forward(request, response);

	}

}
