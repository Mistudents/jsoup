package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class readcsv {
	public static void main(String[] args)
	{
	    File csv = new File("\\\\ZB-WY-P4DRB\\mysharetest\\11.csv");  // CSV�ļ�·��
	    BufferedReader br = null;
	    try
	    {
	        br = new BufferedReader(new FileReader(csv));
	    } catch (FileNotFoundException e)
	    {
	        e.printStackTrace();
	    }
	    String line = "";
	    String everyLine = "";
	    try {
	            List<String> allString = new ArrayList<>();
	            while ((line = br.readLine()) != null)  //��ȡ�������ݸ�line����
	            {
	                everyLine = line;
	                System.out.println(everyLine);
	                allString.add(everyLine);
	            }
	            System.out.println("csv���������������"+allString.size());
	    } catch (IOException e)
	    {
	        e.printStackTrace();
	    }

	}
}
