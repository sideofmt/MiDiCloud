package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Midifile;
import model.MidifileManager;
import model.Report;
import model.ReportManager;
import model.User;

/**
 * Servlet implementation class RankingWindow
 */
@WebServlet("/RankingWindow")
public class RankingWindow extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RankingWindow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();

		if(session.getAttribute("user")==null){
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else{
			this.getServletContext().getRequestDispatcher("/ranking.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		if(request.getParameter("goSearch") != null){
			//検索結果画面へ遷移
			request.setAttribute("search",request.getAttribute("search"));
			this.getServletContext().getRequestDispatcher("/SearchingResultWindow").forward(request, response);
		}
		else if(request.getParameter("midifile") != null){
			//MIDIファイルの詳細画面へ遷移
			MidifileManager manager = new MidifileManager();
			Midifile midifile = manager.search(Integer.parseInt(request.getParameter("midifile")));
			request.setAttribute("midiID", midifile);
			this.getServletContext().getRequestDispatcher("/MidiDetailWindow").forward(request, response);
		}

	}

}
