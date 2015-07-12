package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Midifile;
import model.User;

/**
 * Servlet implementation class SearchingResultWindow
 */
@WebServlet("/SearchingResultWindow")
public class SearchingResultWindow extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchingResultWindow() {
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
			this.getServletContext().getRequestDispatcher("/searchResult.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		if("midicloud".equals(request.getParameter("action"))){
			//ユーザートップ画面へ遷移
			this.getServletContext().getRequestDispatcher("/MemberTopWindow").forward(request, response);
		}
		else if("goSearch".equals(request.getParameter("action"))){
			//検索結果画面へ遷移
			request.setAttribute("search",request.getAttribute("search"));
			this.getServletContext().getRequestDispatcher("/SearchingResultWindow").forward(request, response);
		}
		else if("detail".equals(request.getParameter("action"))){
			//アカウント表示画面へ遷移
			request.setAttribute("otherUser", user);
			this.getServletContext().getRequestDispatcher("/AccountInfoWindow").forward(request, response);
		}
		else if("logout".equals(request.getParameter("action"))){
			//ユーザーデータを破棄してログアウト
			session.removeAttribute("user");
			this.getServletContext().getRequestDispatcher("/Login").forward(request, response);
		}
		else if(request.getAttribute("midi") != null){
			//MIDIファイルの詳細画面へ遷移
			Midifile midifile = (Midifile)request.getAttribute("midi");
			request.setAttribute("midifile", midifile);
			this.getServletContext().getRequestDispatcher("/MidiDetailWindow").forward(request, response);
		}
	}

}
