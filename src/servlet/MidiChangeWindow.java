package servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Midifile;
import model.MidifileManager;
import model.Translate;

/**
 * Servlet implementation class MidiChangeWindow
 */
@WebServlet("/MidiChangeWindow")
public class MidiChangeWindow extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MidiChangeWindow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MidiChangeWindowのdoGetが呼ばれました");
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null){
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else{
			this.getServletContext().getRequestDispatcher("/midiChange.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MidiChangeWindowのdoPostが呼ばれました");
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		Midifile midifile = new Midifile();
		MidifileManager manager = new MidifileManager();
		Translate translate = new Translate();
		midifile = (Midifile)session.getAttribute("midifile");
		System.out.println("midifile"+midifile);

		if(request.getParameter("change") != null) {
			if(request.getParameter("title").isEmpty()) {
				request.setAttribute("error", "<div class=\"alert alert-danger\" role=\"alert\">\n"
						+ "<span class=\"glyphicon glyphicon-exclamation-sign\" aria-hidden=\"true\"></span>\n"
						+ "<span class=\"sr-only\">Error:</span>\n"
						+ "曲名をつけて下さい\n</div>");
				this.getServletContext().getRequestDispatcher("/midiChange.jsp").forward(request, response);
			} else {
				System.out.println(request.getParameter("title"));
				midifile.setTitle(request.getParameter("title"));
				midifile.setExplanation(request.getParameter("exp"));
				if(!request.getParameter("path").isEmpty()) midifile.setMidifile(translate.fileLoad(request.getParameter("path")));
				midifile.setDate(new Timestamp(System.currentTimeMillis()));

				manager.update(midifile);

				session.setAttribute("midifile",midifile);

				this.getServletContext().getRequestDispatcher("/midifile.jsp").forward(request, response);
		}
	}
	}

}
