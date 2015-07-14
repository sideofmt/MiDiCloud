package servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Midifile;

/**
 * Servlet implementation class OutputDownload
 */
@WebServlet("/OutputDownload")
public class OutputDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OutputDownload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		Midifile midi = (Midifile)session.getAttribute("midifile");
		/*Translate translate = new Translate();
		midi.setTitle("test");
		midi.setMidifile(translate.fileLoad("C:/Users/shigetoshi.n/Desktop/ソフ研/曲/e.t.c/Argento/a.mid"));
		*/
		java.io.ByteArrayOutputStream byteOut = new java.io.ByteArrayOutputStream();
		byteOut.write(midi.getMidifile());

		response.setContentType( "application/x-mplayer2" );
		//response.setContentType( "audio/x-mid" );

		String filename = midi.getTitle()+".mid";
		response.setHeader("Content-Disposition","attachment;filename=\""+filename+"\"");

		response.setContentLength( byteOut.size() );
		OutputStream out = response.getOutputStream();
		out.write( byteOut.toByteArray() );
		out.close();
		byteOut.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
