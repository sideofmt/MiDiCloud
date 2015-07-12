package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Midifile;
import model.Report;
import model.ReportManager;
import model.Translate;
import model.User;
import model.UserManager;

/**
 * Servlet implementation class AcountInfromationChangeWindow
 */
@WebServlet("/AcountInfromationChangeWindow")
public class AcountInfoWindow extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcountInfoWindow() {
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
			this.getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
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
			//同じ画面に遷移
			this.getServletContext().getRequestDispatcher("/AccountInfoWindow").forward(request, response);
		}
		else if("logout".equals(request.getParameter("action"))){
			//ユーザーデータを破棄してログアウト
			session.removeAttribute("user");
			this.getServletContext().getRequestDispatcher("/Login").forward(request, response);
		}
		else if("edit".equals(request.getParameter("action"))){
			//ユーザーの編集へ遷移
			this.getServletContext().getRequestDispatcher("/AccountInformationChangeWindow").forward(request, response);
		}
		else if("report".equals(request.getParameter("action"))){
			//不適切なユーザーを報告（書き途中）
			Report report = new Report();
			ReportManager rmanager = new ReportManager();
			User otherUser = (User)request.getAttribute("otherUser");

			report.setReportedUserID(otherUser.getUserID());
			report.setUserID(user.getUserID());
			rmanager.add(report);
			request.setAttribute("message","ユーザーを報告しました");
			this.getServletContext().getRequestDispatcher("/AccountInfoWindow").forward(request, response);
		}
		else if("delete".equals(request.getParameter("action"))){
			//ユーザーを削除
			session.removeAttribute("user");
			request.setAttribute("message", "アカウントを削除しました");
			if(user.isManager()){
				//管理者は管理者トップへ遷移
				this.getServletContext().getRequestDispatcher("/ManagerTopWindow").forward(request, response);;
			}else{
				//ユーザーはログイン画面へ
				this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
		else if("upload".equals(request.getParameter("action"))){
			//MIDIファイルのアップロード画面へ遷移
			this.getServletContext().getRequestDispatcher("/MidiUploadWindow").forward(request, response);
		}
		else if(request.getAttribute("midi") != null){
			//MIDIファイルの詳細画面へ遷移
			Midifile midifile = (Midifile)request.getAttribute("midi");
			request.setAttribute("midifile", midifile);
			this.getServletContext().getRequestDispatcher("/MidiDetailWindow").forward(request, response);
		}




	}

}
