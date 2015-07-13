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
import model.Report;
import model.ReportManager;
import model.User;
import model.UserManager;

/**
 * Servlet implementation class ManagerTopWindow
 */
@WebServlet("/ManagerTopWindow")
public class ManagerTopWindow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerTopWindow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Midifile> midi = new ArrayList<Midifile>();
		List<Midifile> midiNew = new ArrayList<Midifile>();
		MidifileManager manager = new MidifileManager();
		ArrayList<Report> report = new ArrayList<Report>();
		ReportManager managerr = new ReportManager ();
		
		midiNew = manager.getArrival();
		report = managerr.getReportList();

		UserManager man = new UserManager();
		User user = null;
		try {
			user = man.getUser("ket@gmail.com");
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		request.setAttribute("user", user);
		request.setAttribute("midiNew",midiNew);
		request.setAttribute("Report", report);

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
