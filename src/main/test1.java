package main;

import java.io.BufferedReader; 
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;

public class test1 {
	public static void main(String[] args) {

		try {

			String url = "https://cloud.timeedit.net/ltu/web/schedule1/ri.json?h=t&sid=3&p=20200901.x,20210117.x&objects=132867.28&ox=0&types=0&fe=0";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'Get' request to URL :" + url);
			System.out.println("Response code: " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// Printar hela json fr�n uri
			// System.out.println(response.toString());
			// JSONParser parser = new JSONParser(myResponse.toString(););
			// myResponse.toString();
			JSONObject myResponse = new JSONObject(response.toString());
			JSONObject secondResponse = new JSONObject(myResponse.toString());
			JSONArray jsonarr_1 = (JSONArray) myResponse.get("reservations");
			JSONArray jsonarr_2 = new JSONArray();
			System.out.println("Elements under results array");
			for (int i = 0; i < jsonarr_1.length(); i++) {
				// Store the JSON objects in an array
				// Get the index of the JSON object and print the values as per the index
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);
				jsonarr_2 = (JSONArray) jsonobj_1.get("columns");
				// Store the JSON object in JSON array as objects (For level 2 array element i.e
				// Address Components)
				// ----JSONArray jsonarr_2 = (JSONArray) jsonobj_1.get("address_components");

				System.out.println("\nLecture id: " + jsonobj_1.get("id"));
				System.out.println("Date: " + jsonobj_1.get("startdate"));
				System.out.println("Start Time: " + jsonobj_1.get("starttime"));
				System.out.println("End Time: " + jsonobj_1.get("endtime"));
				System.out.println("info: " + jsonobj_1.get("columns"));
				System.out.println("array: " + jsonarr_2);
				ArrayList<String> courseInfo = new ArrayList<>();
				System.out.println("array: " + courseInfo);
				for (int j = 0; j < jsonarr_2.length(); j++) {
					// JSONObject jsonobj_2 = (JSONObject)jsonarr_2.get(j);
					// String info = jsonobj_2.toString();
					// JSONObject jsonobj_2 = new JSONObject((String)jsonarr_2.get(j));
					courseInfo.add(jsonarr_2.getString(j));
					// System.out.println("info: " + info);
//	    		  String keyValue = (String)courseInfo.next();
//	    	        String valueString = jsonobj_2.getString(keyValue);
//	    		  
				}
				System.out.println("array: " + courseInfo);
				int index = 0;
				for (String s : courseInfo)
					System.out.println((index++) + ": " + s);
				System.out.println(courseInfo.get(1));
				String indexValue = courseInfo.get(2);
				System.out.println(indexValue);
			}

			// System.out.println("id " + myResponse.getString("id"));

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
