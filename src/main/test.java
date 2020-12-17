package main;

import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.nio.charset.Charset;
//import java.util.Scanner;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class test {

	
//	private static String readAll(Reader rd) throws IOException {
//	    StringBuilder sb = new StringBuilder();
//	    int cp;
//	    while ((cp = rd.read()) != -1) {
//	      sb.append((char) cp);
//	    }
//	    return sb.toString();
//	  }
//
//	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
//	    InputStream is = new URL(url).openStream();
//	    try {
//	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//	      String jsonText = readAll(rd);
//	      JSONObject json = new JSONObject(jsonText);
//	      return json;
//	    } finally {
//	      is.close();
//	    }
//	  }

	  public static void main(String[] args) {
	    //JSONObject json = readJsonFromUrl("https://cloud.timeedit.net/ltu/web/schedule1/objects.html?max=15&fr=t&partajax=t&im=f&sid=3&l=sv_SE&search_text=D0023E&types=28");
		  test test = new test();
		  try {
		  test.searchCourse("D0023E");
		  }
		  catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	  
	  public void searchCourse(String courseCode) throws FileNotFoundException, IOException, ParseException {
			String sourceURI = "https://cloud.timeedit.net/ltu/web/schedule1/objects.txt?max=15&fr=t&partajax=t&im=f&sid=3&l=sv_SE&search_text=course&types=28";
			sourceURI = sourceURI.replace("course", courseCode);
			
			getCourseID(sourceURI);
		}
	  
	  private void getCourseID(String searchURI) {
		  String inline = "";
		  //String uri = "https://cloud.timeedit.net/ltu/web/schedule1/objects.txt?max=15&fr=t&partajax=t&im=f&sid=3&l=sv_SE&search_text=D0023E&types=28";
		  try
			{  
	    URL url = new URL(searchURI); 
	    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.connect();
	    int responsecode = conn.getResponseCode();
	    System.out.println("Response code is: " +responsecode);
	    if(responsecode != 200)
	    	throw new RuntimeException("HttpResponseCode: " +responsecode);
	    	else
	    	{
	    		Scanner sc = new Scanner(url.openStream());
	    		while(sc.hasNext())
	    		{
	    		inline+=sc.nextLine();
	    		}
	    		System.out.println("\nJSON data in string format");
	    		System.out.println(inline);
	    		sc.close();
	    	}
	    
	    JSONParser parse = new JSONParser();
	    JSONObject jobj = (JSONObject)parse.parse(inline);
	    JSONArray jsonarr_1 = (JSONArray) jobj.get("records");
	    System.out.println("Elements under results array");
	    for(int i=0;i<jsonarr_1.size();i++)
	    	{
	    	  //Store the JSON objects in an array
	    	  //Get the index of the JSON object and print the values as per the index
	    	  JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(i);
	    	  //Store the JSON object in JSON array as objects (For level 2 array element i.e Address Components)
	    	  //----JSONArray jsonarr_2 = (JSONArray) jsonobj_1.get("address_components");
	    	  
	    	  System.out.println("\nCourse id: " +jsonobj_1.get("idAndType"));
	    	  System.out.println("Course Name: " +jsonobj_1.get("values"));
	    	  //Get data for the Address Components array
	    	  //System.out.println("The long names, short names and types are:");
//	    	  for(int j=0;j<jsonarr_2.length();j++)
//	    	  {
//	    	     //Same just store the JSON objects in an array
//	    	     //Get the index of the JSON objects and print the values as per the index
//	    	     JSONObject jsonobj_2 = (JSONObject) jsonarr_2.get(j);
//	    	     //Store the data as String objects
//	    	     String str_data1 = (String) jsonobj_2.get("long_name");
//	    	     String str_data2 = (String) jsonobj_2.get("short_name");
//	    	     System.out.println(str_data2);
//	    	     System.out.println(jsonobj_2.get("types"));
//	    	     System.out.println("\n");
//	    	  }
	    	}
	    conn.disconnect();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	  }
	    
	  }
