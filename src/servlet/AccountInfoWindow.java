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
import javax.servlet.http.HttpSession;

import model.Midifile;
import model.Report;
import model.ReportManager;
import model.Translate;
import model.User;
import model.UserManager;
import model.MidifileManager;

/**
 * Servlet implementation class AcountInfromationChangeWindow
 */
@WebServlet("/AccountInfoWindow")
public class AccountInfoWindow extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountInfoWindow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("AccountInfoWindowのdoGetが呼ばれました");

		HttpSession session = request.getSession();

		if(request.getAttribute("message") != null){
			request.setAttribute("message",request.getAttribute("message"));
		}

		if(session.getAttribute("user")==null){
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else{

			MidifileManager m = new MidifileManager();
			UserManager manager = new UserManager();
			User showuser = null;
			try {
				showuser = manager.returnUser(Integer.parseInt(request.getParameter("midiID")));
			} catch (NumberFormatException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch(NullPointerException e){
				User user = (User)session.getAttribute("user");
				showuser = user;
			}

			List<Midifile> midifiles;

			try{
			midifiles = m.searchList(showuser.getUserID());
			request.setAttribute("midifiles",midifiles);
			}catch(NullPointerException e){
				System.out.println(e);
			}

			request.setAttribute("showuser", showuser);
			this.getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("AccountInfoWindowのdoPostが呼ばれました");

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		if(request.getParameter("goSearch")!=null){
			//検索結果画面へ遷移
			request.setAttribute("search",request.getAttribute("search"));
			this.getServletContext().getRequestDispatcher("/SearchingResultWindow").forward(request, response);
		}
		else if(request.getParameter("edit") != null){
			//ユーザーの編集へ遷移
			this.getServletContext().getRequestDispatcher("/changeProfile.jsp").forward(request, response);
		}
		else if(request.getParameter("report") != null){
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
		else if(request.getParameter("delete") != null){
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
		else if(request.getParameter("upload") != null){
			//MIDIファイルのアップロード画面へ遷移
			System.out.println("midiUpload画面へ遷移します");
//			this.getServletContext().getRequestDispatcher("/MidiUploadWindow").forward(request, response);
			this.getServletContext().getRequestDispatcher("/midiUpload.jsp").forward(request, response);
		}
		else if(request.getParameter("midiID")!=null){
			//MIDI詳細表示画面へ遷移
			//Midifileオブジェクトを次画面へ送信
			System.out.println("midi詳細画面へ遷移します");
			session.setAttribute("user",user);

			MidifileManager manager = new MidifileManager();
			Midifile midifile = manager.search(Integer.parseInt(request.getParameter("midiID")));
			System.out.println(midifile.toString());
			session.setAttribute("midifile",midifile);
			this.getServletContext().getRequestDispatcher("/midifile.jsp").forward(request, response);
		}

	}

}
