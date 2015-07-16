package servlet;

import java.io.IOException;
import java.sql.SQLException;
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

import model.Translate;
import model.User;
import model.UserManager;

/**
 * Servlet implementation class AccountInformationChangeWindow
 */
@WebServlet("/AccountInformationChangeWindow")
public class AccountInformationChangeWindow extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountInformationChangeWindow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AccountInformationChangeWindowのdoGetが呼ばれました");
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null){
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else{
			this.getServletContext().getRequestDispatcher("/changeProfile.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AccountInformationChangeWindowのdoPostが呼ばれました");
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		User user = (User)session.getAttribute("user");
		session.setAttribute("user",user);
//		if(request.getParameter("change") == null){
//			if(session.getAttribute("user")==null){
//				this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
//			}
//			else{
//				this.getServletContext().getRequestDispatcher("/changeProfile.jsp").forward(request, response);
//			}
//		}
//		if(request.getParameter("change") != null){
			UserManager manager = new UserManager();
			Translate translate = new Translate();

			String username = request.getParameter("name");
			String profile =request.getParameter("profile");
			byte[] icon = null;

			 DiskFileItemFactory factory = new DiskFileItemFactory();
			  ServletFileUpload sfu = new ServletFileUpload(factory);

			  try {
			     List<FileItem> items = sfu.parseRequest(new ServletRequestContext(request));

			    for(FileItem item : items){

			    	//フォームフィールドがファイルかテキストかを判別
			    	if(item.isFormField()){
			    		if(item.getFieldName().equals("name")){
			    			username = item.getString();
			    		}
			    		else if(item.getFieldName().equals("profile")){
			    			profile = item.getString();
			    		}

			    		System.out.println(new String(item.getString().getBytes("UTF-8"), "UTF-8"));
			    	}else{

			    		if(item.getString().equals("")){
			    			icon = user.getIcon();
			    		}else{
				    		icon = item.get();
			    		}
			    		System.out.println(icon);
			    	}
			      /* 取り出したFileItemに対する処理 */
			    }

			  }catch (FileUploadException e) {
			    e.printStackTrace();
			  }


			user.setUsername(username);
			user.setProfile(profile);
			user.setIcon(icon);
			try {
				manager.updateUser(user);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			session.setAttribute("user", user);
			this.getServletContext().getRequestDispatcher("/memberTop.jsp").forward(request, response);
		//}
	}
}
