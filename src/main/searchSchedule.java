package main;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
//import java.util.*;

import org.json.JSONException;
//import org.json.simple.*;  
import org.json.simple.parser.*;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;




public class searchSchedule {

	public void searchCourse(String courseCode) throws FileNotFoundException, IOException, ParseException {
		String sourceURI = "https://cloud.timeedit.net/ltu/web/schedule1/objects.txt?max=15&fr=t&partajax=t&im=f&sid=3&l=sv_SE&search_text=course&types=28";
		sourceURI = sourceURI.replace("course", courseCode);
		
		getCourseID(sourceURI);
	}
	//var kod ="";
	//https://cloud.timeedit.net/ltu/web/schedule1/objects/132868(KOD)/o.json?fr=t&types=28&sid=3&l=sv_SE
	
	private void getCourseID(String searchURI) throws FileNotFoundException, IOException, ParseException{
		Object object = new JSONParser().parse(new FileReader(searchURI)); 
        JSONObject skr = (JSONObject) object;  
        String id = (String) skr.get("data-id");
        System.out.println(id);
	}
	
	public static void main(String args []) throws FileNotFoundException, IOException, ParseException, JSONException {
		//searchSchedule test = new searchSchedule();
		//test.searchCourse("D0031N");
		JSONObject json = readJsonFromUrl("https://cloud.timeedit.net/ltu/web/schedule1/objects.txt?max=15&fr=t&partajax=t&im=f&sid=3&l=sv_SE&search_text=D0023E&types=28");
	    System.out.println(json.toString());
	    System.out.println(json.get("id"));
	}
	
	
	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	  }
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
}
