package souke;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TypeId {

	/**
	 * 一级分类
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "http://souke.xdf.cn/Category/1.html";
		 String file="F:\\souke\\typeid.sql";
		
		try {
			Document doc = Jsoup.connect(url).get();
			System.out.println(doc.html());
			Element schools = doc.select("ul.classification").first();
			//System.out.println(schools.html());
			Document one = Jsoup.parse(schools.html());
			Elements lis = one.select("li");
			for (int i = 0; i < lis.size(); i++) {
				Element li = lis.get(i);
				String conent="insert type(id,type) VALUES('"+li.attr("code")+"','"+li.text()+"');";
				FileUtils.writefile(file, conent);
			}

		} catch (Exception ex) {

			ex.printStackTrace();
		}
	}

}
