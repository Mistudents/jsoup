package souke;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 爬虫获取搜客上面的选择城市及id
 * 
 * @author fanjunsheng
 * 
 */
public class CityId {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "http://souke.xdf.cn/Search?cid=1&ccc=186&gc=0";
		 String file="F:\\souke\\cityid.sql";
		
		try {
			//浏览器方式
			Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
			//
			Element schools = doc.select(".area_school ul").first();
			Document one = Jsoup.parse(schools.html());
			Elements lis = one.select("li a");
			for (int i = 0; i < lis.size(); i++) {
				Element li = lis.get(i);
				String conent="insert city(cid,name) VALUES('"+li.attr("cid")+"','"+li.text()+"');";
				//System.out.println(conent);
				FileUtils.writefile(file, conent);
				
			}

		} catch (Exception ex) {

			ex.printStackTrace();
		}
	}

}
