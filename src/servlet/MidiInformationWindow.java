package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Comment;
import model.CommentManager;
import model.Midifile;
import model.MidifileManager;
import model.Report;
import model.ReportManager;
import model.User;

/**
 * Servlet implementation class MidiInformationWindow
 */
@WebServlet("/MidiInformationWindow")
public class MidiInformationWindow extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MidiInformationWindow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MidiInformationWindowのdoGetが呼ばれました");

		this.getServletContext().getRequestDispatcher("/midifile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MidiInformationWindowのdoPostが呼ばれました");
		request.setCharacterEncoding("UTF-8");

		Midifile midifile = new Midifile();
		MidifileManager manager = new MidifileManager();
		User user = new User();
		HttpSession session = request.getSession();
		midifile = (Midifile)session.getAttribute("midifile");
		user = (User)session.getAttribute("user");

		if(request.getParameter("goSearch")!=null){
			request.setAttribute("search",request.getAttribute("search"));
			this.getServletContext().getRequestDispatcher("/SearchingResultWindow").forward(request, response);

		} else if(request.getParameter("favo") != null) {
			if(Integer.valueOf(request.getParameter("favo")) == null){
				midifile.setFavorite(1);
			}else{
				midifile.setFavorite( (Integer.valueOf(request.getParameter("favo"))+1));
			}
			System.out.println("favorite="+midifile.getFavorite());
			manager.update(midifile);
			this.getServletContext().getRequestDispatcher("/midifile.jsp").forward(request, response);

		} else if(request.getParameter("download") != null) {
			request.setAttribute("midifile",request.getAttribute("midifile"));
			OutputDownload out = new OutputDownload();
			out.doGet(request, response);
			this.getServletContext().getRequestDispatcher("/OutputDownload").forward(request, response);
		}
		 else if(request.getParameter("commentUpload") != null) {
			 Comment comment = new Comment();
			 CommentManager cmanager = new CommentManager();
			comment.setComment(request.getParameter("userComment"));
			comment.setUserID(user.getUserID());
			comment.setMidiID(midifile.getMidiID());
			boolean flag = false;;
			try {
				flag = cmanager.addComment(comment);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			if(flag){
				System.out.println("コメントの追加に成功しました");
			}else{
				System.out.println("コメントの追加に失敗しました");
			}
			this.getServletContext().getRequestDispatcher("/midifile.jsp").forward(request, response);
		}
		else if(request.getParameter("edit")!=null){
			this.getServletContext().getRequestDispatcher("/midiChange.jsp").forward(request, response);
		}
		else if(request.getParameter("delete")!=null){
			//midiファイルの削除
			session.removeAttribute("midifile");
			try{
				manager.delete(midifile.getMidiID());
				System.out.println("midiファイルの削除に成功しました");
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("midiファイルの削除に失敗しました");
			}
			this.getServletContext().getRequestDispatcher("/memberTop.jsp").forward(request, response);
		}
		else if(request.getParameter("report")!=null){
			Report report = new Report();
			ReportManager rmanager = new ReportManager();


			this.getServletContext().getRequestDispatcher("/midifile.jsp").forward(request, response);
		}
		else if(request.getParameter("deleteComment") != null){
			CommentManager cmanager = new CommentManager();
			try{
				cmanager.delCommentByID(Integer.parseInt(request.getParameter("deleteComment")));
				System.out.println("コメントの削除に失敗しました");
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("コメントの削除に成功しました");
			}

			this.getServletContext().getRequestDispatcher("/midifile.jsp").forward(request, response);
		}

	}

}
