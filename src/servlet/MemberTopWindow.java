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
		System.out.println("MemberTopWindowのdoGetが呼ばれました");
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null){
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else{
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
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("MemberTopWindowのdoPostが呼ばれました");
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		User user = (User)request.getAttribute("user");
		session.setAttribute("user",user);
		System.out.println(request.getParameter("midiID"));

		if(request.getParameter("goSearch")!=null){
			//検索結果画面へ遷移
//			request.setAttribute("search",request.getAttribute("search"));
			this.getServletContext().getRequestDispatcher("/SearchingResultWindow").forward(request, response);
		}
		else if(request.getParameter("midiID")!=null){
			//MIDI詳細表示画面へ遷移
			//Midifileオブジェクトを次画面へ送信
			System.out.println("midi詳細画面へ遷移します");

			MidifileManager manager = new MidifileManager();
			Midifile midifile = manager.search(Integer.parseInt(request.getParameter("midiID")));
			request.setAttribute("midifile",midifile);
			this.getServletContext().getRequestDispatcher("/midifile.jsp").forward(request, response);
		}

	}

}
