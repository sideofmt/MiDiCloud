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
import model.Translate;

/**
 * Servlet implementation class OutputFile
 */
@WebServlet("/OutputFile")
public class OutputFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OutputFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Translate translate = new Translate();

		//Midifile midi = (Midifile)session.getAttribute("midifile");
		//System.out.println(midi.getTitle());
		Midifile midi = new Midifile();
		midi.setTitle("test");
		midi.setMidifile(translate.fileLoad("C:/Users/shigetoshi.n/Desktop/ソフ研/曲/e.t.c/Argento/a.mid"));
		java.io.ByteArrayOutputStream byteOut = new java.io.ByteArrayOutputStream();
		byteOut.write(midi.getMidifile());
		//byteOut.write(midi.getMidifile(),0,translate.size(midi.getMidifile()));

		response.setContentType( "application/x-mplayer2" );
		//response.setContentType( "audio/mid" );

		String filename = midi.getTitle()+".mid";
		response.setHeader("Content-Disposition","attachment;filename=\""+filename+"\"");

		/*File f = new File(filename);
		OutputStream os = new FileOutputStream(f);
		response.setContentLength( byteOut.size() );
		os = response.getOutputStream();
		os.write(byteOut.toByteArray());
		os.close();*/

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
