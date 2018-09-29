package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Teacher {

	// http://souke.xdf.cn/Teacher/1.html?pagesize=100&page=5

	public static void main(String args[]) {

//       String readfile="e:\\teacher.txt";
//		readFile(readfile);
		String url =  "http://souke.xdf.cn/Teacher/1-1-QK1087-0.html";
		jsoupa(url,"utf-8");
//		for(int i=1;i<272;i++){
//		String url = "http://souke.xdf.cn/Teacher/1.html?page="+i;
//		jsoup(url);
//		}
		
	}

	public static void method2(String file, String conent) {
		
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, true)));
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

	public static  void  jsoupa(String url,String code){
		String file="e:\\teachimgage.txt";
		try {
			Document doc = Jsoup.connect(url).get();
			// <dl class="tea_list fix">
			Element links = doc.select("dl.teacherInfo").first();
			//System.out.println(":" + links.html());
			Document  one=Jsoup.parse(links.html());
			Element img=one.select("dt img").first();
			//image
			String images=img.attr("src");
			System.out.println(images);
			//age
			Element age=one.select("div.fl p").last();
			System.out.println(age.text());
			method2(file,code+"||"+images+"||"+age.text());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
	}
	
	
	
	public static  void  jsoup(String url){
		String file="e:\\teachimgage.txt";
		try {
			
			Document doc = Jsoup.connect(url).get();
			// <dl class="tea_list fix">
			Elements links = doc.select("div dl.tea_list dd");
			System.out.println("size:" + links.size());
			for (Element el : links) {
				String html = el.html();
				Document teachdoc = Jsoup.parse(html);
				Element hrefv = teachdoc.select("h5 a").first();
				// teachcode
				//String teacode = sub(hrefv.attr("href"));
				String teacode = hrefv.attr("href");
				//System.out.println(hrefv.attr("href"));
				// teacher jieshao
				Element jianshao = teachdoc.select("p.h33").first();
				String js = jianshao.text();
				System.out.println(js);
				
				//method2(file,teacode+"||"+js);
				method2(file,teacode);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
	}
	
	public static String sub(String teachercode){
			int start=teachercode.indexOf("1-1-");
			int end=teachercode.indexOf("-0");
	        String code=teachercode.substring(start+4, end);
	        return code;
	}
	
	
	public static String  readFile(String filename){
		try{
			 String encoding="GBK";
			 InputStreamReader read = new InputStreamReader(new FileInputStream(filename),encoding);//¿¼ÂÇµ½±àÂë¸ñÊ½
	                    BufferedReader bufferedReader = new BufferedReader(read);
	                    String lineTxt = null;
	                    while((lineTxt = bufferedReader.readLine()) != null){
	                       
	                        String url="http://souke.xdf.cn"+lineTxt;
	                        String code=sub(lineTxt);
	                        System.out.println(url);
	                        jsoupa(url,code);
	                        
	                    }
	                    read.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return "";
	}
	
	
	
	
}
