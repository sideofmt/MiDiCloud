package model;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Translate {



	public byte[] fileLoad(String path) throws FileNotFoundException{

		File file = new File(path);
		InputStream inputStream = new FileInputStream(file);
		byte[] icon = getBytes(inputStream);

		return icon;
	}

	public ByteArrayOutputStream fileOutput(byte[] bytes) throws IOException{
		return getImage(bytes);
	}

	private byte[] getBytes(InputStream is){

		ByteArrayOutputStream b = new ByteArrayOutputStream();
		OutputStream os = new BufferedOutputStream(b);
		int c;

		try {
			while ((c = is.read()) != -1) {
				os.write(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// 書き込み先はByteArrayOutputStreamクラス内部となる。
		// この書き込まれたバイトデータをbyte型配列として取り出す場合には、
		// toByteArray()メソッドを呼び出す。
		return b.toByteArray();
	}

	private ByteArrayOutputStream getImage(byte[] icon) throws IOException{

		OutputStream output = (OutputStream)(new ByteArrayOutputStream());
		output.write(icon);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write(output, 0, output.length );
		return out;
	}


}
