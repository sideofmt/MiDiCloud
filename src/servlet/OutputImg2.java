package servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.UserManager;

/**
 * Servlet implementation class OutputImg
 */
@WebServlet("/OutputImg2")
public class OutputImg2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OutputImg2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//Translate t = new Translate();

		UserManager manager = new UserManager();
		User user = null;
		try {
			user = manager.returnUser(Integer.parseInt(request.getParameter("userID")));
		} catch (NumberFormatException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//User user = new User();
		//user.setIcon(t.fileLoad("C:/Users/shigetoshi.n/Desktop/ソフ研/mudai.png"));
		if(user != null){

		java.io.ByteArrayOutputStream byteOut = new java.io.ByteArrayOutputStream();
		byteOut.write(user.getIcon());
		//byteOut.write(midi.getMidifile(),0,translate.size(midi.getMidifile()));

		response.setContentType( "image/jpeg" );

		response.setContentLength( byteOut.size() );
		OutputStream out = response.getOutputStream();
		out.write( byteOut.toByteArray() );
		out.close();
		byteOut.close();

		}else{
			System.out.println("ユーザーデータを正しく取得できず、画像を表示できませんでした");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
