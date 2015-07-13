package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MidifileManager;
import model.Report;
import model.ReportManager;
import model.UserManager;

/**
 * Servlet implementation class ReportListDisplayWindow
 */
@WebServlet("/ReportListDisplayWindow")
public class ReportListDisplayWindow extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportListDisplayWindow() {
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
			this.getServletContext().getRequestDispatcher("/Report.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		ReportManager manager = new ReportManager();
		//Report report = (Report)session.getAttribute("report");


		//"report".equals(request.getParameter("action"))

		ArrayList<Report> reportList = manager.getReportList();

		for(Report report : reportList){
		if( ("report"+report.getReportID() ).equals(request.getParameter("action") ) ){

		}
		}


		if("midicloud".equals(request.getParameter("action"))){
			//ユーザートップ画面へ遷移
			this.getServletContext().getRequestDispatcher("/MemberTopWindow").forward(request, response);
		}
		
		if(request.getAttribute("report") != null){
			Report report = (Report)request.getAttribute("report");
			request.setAttribute("report", report);
			if(report.getReportedmidiID() != 0) {
				MidifileManager midi = new MidifileManager();
				request.setAttribute("midifile", midi.search(report.getReportedmidiID()));
				this.getServletContext().getRequestDispatcher("/MidiInformationWindow").forward(request, response);
			} else {
				UserManager user = new UserManager();
				try {
					request.setAttribute("user", user.returnUser(report.getReportedUserID()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				this.getServletContext().getRequestDispatcher("/AccountInfoWindow").forward(request, response);
			}
		}else{
			System.out.println("ボタンが押されました");
		}
	}

}
