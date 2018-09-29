package souke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * ������ѧ
 * 
 * @author fanjunsheng
 * 
 */
public class Studyabroad {
	static String file = "F:\\souke\\abroad.sql";
	static String herffile = "F:\\souke\\href.sql";
	static String cityid = "F:\\souke\\city.txt";
	static String encode = "utf-8";
	static String datailfile = "F:\\souke\\datailfile.sql";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// allcitydown(cityid);

		alldetaildown(herffile);

	}

	// ȫ�����������б���Ϣ
	public static void allcitydown(String cityfile) {
		try {
			File file = new File(cityfile);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), encode));
			String sLine = null;
			while ((sLine = br.readLine()) != null) {

				System.out.println(sLine);
				for (int i = 1; i < 40; i++) {
					getList(i, sLine);
				}
			}
			br.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	// �����б�ҳ
	public static void getList(int page, String cid) {
		try {
			String url = "http://souke.xdf.cn/Search?cid=" + cid
					+ "&ccc=145&page=" + page;
			Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
			Elements links = doc.select(".m-courselist");
			for (Element el : links) {
				Document one = Jsoup.parse(el.html());
				Element name = one.select("h2 a").first();
				String herf = name.attr("href");// ��ϸҳ����
				String classname = name.text();// �༶����

				Element xueyan = one.select(".max-h").first();
				String xy = xueyan.text();// ����ѧԱ

				Element kecheng = one.select(".u-title").last();
				String kc = kecheng.text();// �γ̼��

				Element jiage = one.select(".u-price").first();
				String jg = jiage.text();// �۸�

				Element type = one.select("h2 span").first();
				String ty = type.text();// �Ͽη�ʽ

				String conent = "insert  studyabroad(shoolid,href,classname,student,couse,price,classtype) "
						+ " VALUES('"
						+ cid
						+ "','"
						+ herf
						+ "','"
						+ classname
						+ "','"
						+ xy
						+ "','"
						+ kc
						+ "','"
						+ jg
						+ "','"
						+ ty
						+ "');";

				String herfcontext = herf;
				FileUtils.writefile(file, conent);
				FileUtils.writefile(herffile, herfcontext);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// �����б�ҳ������ϸҳ
	public static void alldetaildown(String filepath) {
		try {
			File file = new File(filepath);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), encode));
			String sLine = null;
			while ((sLine = br.readLine()) != null) {
				System.out.println(sLine);
				parseDetai(sLine);
			}
			br.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	// ������ϸҳ����
	public static void parseDetai(String line) {
		try {
			String url = "http://souke.xdf.cn" + line;
			Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
			Elements links = doc.select("div.m-crumbs a");
			String onetype = links.get(1).text(); // һ��Ŀ¼
			String twotype = links.get(2).text(); // ����Ŀ¼
			// System.out.println(onetype);
			// System.out.println(twotype);

			Elements xx = doc.select("dl.title-dl dd p");
			String bbsj = "";
			String bx = "";
			String kcjs = "";
			String kcmyd = "";
			if (xx.size() > 0) {
				bbsj = xx.get(0).text();// ���鱨��ʱ��
				bx = xx.get(1).text();// ����
				kcjs = xx.get(2).text();// �γ̽���
				kcmyd = xx.get(3).html();// �γ������
			}
			Elements kctx = doc.select("#bjxdf_tixi li");
			StringBuffer sb = new StringBuffer();
			for (Element el : kctx) {
				sb.append(el.text()).append("/");
			}
			// System.out.println(sb.toString());//�γ���ϵ

			Elements kctxdetail = doc.select("#bjxdf_tixi_cont li");
			StringBuffer sb1 = new StringBuffer();
			for (Element el : kctxdetail) {
				sb1.append(el.text()).append("/");
			}
			// System.out.println(sb1.toString());//�γ���ϵ��ϸ˵��

			Element shxy = doc.select("p.stu-intro").first();
			String shxys = "";
			if (shxy != null)
				shxys = shxy.text();
			// System.out.println(shxys);//�ʺ�ѧԱ

			Element sk = doc.select("dl.shouke-dl dd").first();
			String sks = "";
			if (sk != null)
				sks = sk.text();
			// System.out.println(sks);//�ڿ�����

			Element kcts = doc.select("div.tese-wrap.cfix").first();
			String ts = "";
			if (kcts != null)
				ts = kcts.text();
			// System.out.println(ts);//�γ���ɫ

			Element tjsj = doc.select("div.tjjc").first();
			String sj = "";
			if (tjsj != null)
				sj = tjsj.text();
			// System.out.println(sj);//�Ƽ��鼮��

			Element xqhj = doc.select("div.books-scroll").first();
			String xq = "";
			if (xqhj != null)
				xq = xqhj.text();
			// System.out.println(xq);//У��������

			Element bkzn = doc.select("div.guide-wrap").first();
			String bk = "";
			if (bkzn != null)
				bk = bkzn.text();
			System.out.println(bk);// ����ָ��

//			String conent = onetype + twotype + bbsj + bx + kcjs + kcmyd
//					+ sb.toString() + sb1.toString() + shxys + sks + ts + sj
//					+ xq + bk;
			
			String sql="UPDATE  studyabroad SET onetype='"+onetype+"', twotype='"+twotype+"' ,bbsj='"+bbsj+"',bx='"+bx+"',kcjs='"+kcjs+"',kcmyd='"+kcmyd+"',kctx='"+sb.toString()+"', shxys='"+shxys+"',sks='"+sks+"',ts='"+ts+"',sj='"+sj+"',xq='"+xq+"',bk='"+bk+"',kctxdetail='"+sb1.toString()+"'  WHERE href='"+line+"';";
			
			
			FileUtils.writefile(datailfile, sql);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
