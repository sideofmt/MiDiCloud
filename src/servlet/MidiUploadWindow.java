package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

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



			String title = null;
			String exp = null;
			byte[] midifile = null;
			int userID = user.getUserID();

			 DiskFileItemFactory factory = new DiskFileItemFactory();
			  ServletFileUpload sfu = new ServletFileUpload(factory);

			  try {
			     List<FileItem> items = sfu.parseRequest(new ServletRequestContext(request));

			    for(FileItem item : items){

			    	//フォームフィールドがファイルかテキストかを判別
			    	if(item.isFormField()){
			    		if(item.getFieldName().equals("title")){
			    			title = item.getString();
			    		}
			    		else if(item.getFieldName().equals("exp")){
			    			exp = item.getString();
			    		}

			    		System.out.println(new String(item.getString().getBytes("UTF-8"), "UTF-8"));
			    	}else{
			    		midifile = item.get();
			    	}
			      /* 取り出したFileItemに対する処理 */
			    }







			midi.setMidiID(0);
			midi.setTitle(title);
			midi.setExplanation(exp);
			midi.setFavorite(0);
			midi.setMidifile(midifile);
			midi.setUserID(userID);
			midi.setDate(new Timestamp(System.currentTimeMillis()));

			int id = 0;

//			try{
				id = manager.add(midi);
//			}catch(Exception e){
//				e.printStackTrace();
//			}

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

			  }catch (Exception e) {
				    e.printStackTrace();
						request.setAttribute("error", "<div class=\"alert alert-danger\" role=\"alert\">\n"
								+ "<span class=\"glyphicon glyphicon-exclamation-sign\" aria-hidden=\"true\"></span>\n"
								+ "<span class=\"sr-only\">Error:</span>\n"
								+ "MIDIファイルをアップロードできませんでした\n</div>");
						this.getServletContext().getRequestDispatcher("/midiUpload.jsp").forward(request, response);



				  }

		}


}
