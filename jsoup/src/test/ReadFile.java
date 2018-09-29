package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFile {

	public static void main(String[] args) {
		// 读取单个文件
		try {
		//readFile("\\\\192.168.0.100\\public\\aa\\a.txt", "utf-8");
		readFile("\\\\ZB-WY-P4DRB\\mysharetest\\11.txt", "utf-8");
		
		// 读取某个目录下所有文件
//		String[] fileNames = getFileNames("\\\\192.168.0.100\\public\\aa");
//		String encode = "utf-8";
//		for (String fileName : fileNames) {
//				readFile(fileName, encode);
//		}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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

	public static String[] getFileNames(String path) {
		File dirFile = new File(path);
		if (dirFile.isDirectory()) {
			File[] files = dirFile.listFiles();
			String[] fileNames = new String[files.length];
			for (int i = 0; i < files.length; i++) {
				fileNames[i] = files[i].getAbsolutePath();
			}
			return fileNames;
		} else {
			return null;
		}
	}

}
