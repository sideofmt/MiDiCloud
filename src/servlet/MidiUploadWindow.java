package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Midifile;
import model.MidifileManager;
import model.Translate;
import model.User;
import model.UserManager;

/**
 * Servlet implementation class MidiUploadWindow
 */
@WebServlet("/MidiUploadWindow")
public class MidiUploadWindow extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MidiUploadWindow() {
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
		} else {
			this.getServletContext().getRequestDispatcher("/midiUpload.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");

		Midifile midi = new Midifile();
		MidifileManager manager = new MidifileManager();
		Translate translate = new Translate();

		if(request.getParameter("midifile").isEmpty()) {
			request.setAttribute("error", "<div class=\"alert alert-danger\" role=\"alert\">\n"
					+ "<span class=\"glyphicon glyphicon-exclamation-sign\" aria-hidden=\"true\"></span>\n"
					+ "<span class=\"sr-only\">Error:</span>\n"
					+ "midiファイルを選択して下さい\n</div>");
			this.getServletContext().getRequestDispatcher("/midiUpload.jsp").forward(request, response);
		} else if(request.getParameter("title").isEmpty()) {
			request.setAttribute("error2", "<div class=\"alert alert-danger\" role=\"alert\">\n"
					+ "<span class=\"glyphicon glyphicon-exclamation-sign\" aria-hidden=\"true\"></span>\n"
					+ "<span class=\"sr-only\">Error:</span>\n"
					+ "タイトルを入力して下さい\n</div>");
			this.getServletContext().getRequestDispatcher("/midiUpload.jsp").forward(request, response);
		} else {
			midi.setMidiID(0);
			midi.setTitle(request.getParameter("title"));
			midi.setExplanation(request.getParameter("exp"));
			midi.setFavorite(0);
			midi.setMidifile(translate.fileLoad(request.getParameter("midifile")));

			midi.setUserID(user.getUserID());
			midi.setDate(new Timestamp(System.currentTimeMillis()));

			int id = manager.add(midi);
			UserManager um = new UserManager();

			ArrayList<Integer> midiIDs;
			try{
				midiIDs = user.getMIDI_IDs();
				midiIDs.add(id);
				user.setMIDI_IDs(midiIDs);
				um.updateUser(user);

			}catch(Exception e){
				System.out.println("midifileをユーザーに関連付けられませんでした");
			}
			session.setAttribute("user",user);


			this.getServletContext().getRequestDispatcher("/midiUploadComplete.jsp")
				.forward(request, response);
		}
	}

}
