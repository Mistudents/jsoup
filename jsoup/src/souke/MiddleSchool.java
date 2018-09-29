package souke;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MiddleSchool {

	/**
	 * 又能中学
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getList(1,"1");
	}

	//下载列表页http://souke.xdf.cn/MiddleSchool-1.html?pagesize=50&page=2
	public static void getList(int page, String cid) {
		try {
			String url = "http://souke.xdf.cn/MiddleSchool-"+cid+".html?page="+page;
			Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
			Elements links = doc.select("div.m-classlist-l h3");
			//System.out.println(links.html());
			for(Element one:links){
			Document docu = Jsoup.parse(one.html());
			Element name = docu.select("a").first();
			String href=name.attr("href");
			
			
			System.out.println(href);
			
			}
			
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
}
