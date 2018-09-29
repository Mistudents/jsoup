package souke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileUtils {

	/**
	 * 写文件
	 * @param file
	 * @param conent
	 */
	public static void writefile(String file, String conent) {
		
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, true),"UTF-8"));
			out.write(conent + "\r\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 读文件
	 * @param sourceFilePath
	 * @param encode
	 * @throws IOException
	 */
	public static void readFile(String sourceFilePath, String encode)
			throws IOException {
		File file = new File(sourceFilePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), encode));
		StringBuilder strBuilder = new StringBuilder();
		String sLine = null;
		while ((sLine = br.readLine()) != null) {
			strBuilder.append(sLine);
			strBuilder.append("\r\n");
		}
		br.close();
		System.out.println(strBuilder.toString());
	}
	
	
}
