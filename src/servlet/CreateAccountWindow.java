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
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import model.User;
import model.UserManager;
import model.Translate;

/**
 * Servlet implementation class CreateAccountWindow
 */
@WebServlet("/CreateAccountWindow")
public class CreateAccountWindow extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccountWindow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub]

		System.out.println("CreateAccountWindowのdoGetが呼び出されました");

		this.getServletContext().getRequestDispatcher("/makeAccount.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		System.out.println("CreateAccountWindowのdoPostが呼び出されました");



		//if(request.getParameter("createAccount") != null){


		System.out.println("アカウント作成ボタンが押されました");

		User user = new User();
		UserManager usermanager = new UserManager();
		Translate translate = new Translate();

		int userID = 0;
		String username=null;
		String mailAddress=null;
		String password=null;
		String profile=null;
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
		    		else if(item.getFieldName().equals("email")){
		    			mailAddress = item.getString();
		    		}
		    		else if(item.getFieldName().equals("pass")){
		    			password = item.getString();
		    		}
		    		else if(item.getFieldName().equals("profile")){
		    			profile = item.getString();
		    		}

		    		System.out.println(new String(item.getString().getBytes("UTF-8"), "UTF-8"));
		    	}else{
		    		icon = item.get();
		    	}
		      /* 取り出したFileItemに対する処理 */
		    }

		  }catch (FileUploadException e) {
		    e.printStackTrace();
		  }

		  if(icon==null){
			  icon = translate.fileLoad("/img/img.jpg");
		  }







		user.setUserID(userID);
		user.setUsername(username);
		user.setMailAddress(mailAddress);
		user.setPassword(password);
		user.setProfile(profile);
		user.setIcon(icon);

		System.out.println(user);

		boolean flag=false;

		try {
			flag = usermanager.createUserData(user);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		if(flag){
			this.getServletContext().getRequestDispatcher("/completeMakeAccount.jsp").forward(request, response);
		}else{
			request.setAttribute("error","<div class=\"alert alert-danger\" role=\"alert\">\n<span class=\"glyphicon glyphicon-exclamation-sign\" aria-hidden=\"true\"></span>\n<span class=\"sr-only\">Error:</span>\n  ユーザーを追加できませんでした\n</div>");
			this.getServletContext().getRequestDispatcher("/makeAccount.jsp").forward(request, response);
		}
		//}
	}

}
