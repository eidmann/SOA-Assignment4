package main;

import java.io.BufferedReader; 
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONObject;
import org.json.JSONArray;

public class test1 {
	public static void main(String[] args) {
		
		test1 test1 = new test1();
		Scanner input = new Scanner(System.in);
		getCourse course = new getCourse();
		try {
			//Här ska koden från test komma
			System.out.println("Choose a decimal number");
			String courseDecimal = input.nextLine();
			test1.searchCourseInfo(courseDecimal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void searchCourseInfo(String courseDesimal){
		String sourceURI = "https://cloud.timeedit.net/ltu/web/schedule1/ri.json?h=t&sid=3&p=20200901.x,20210117.x&objects=course&ox=0&types=0&fe=0";
		sourceURI = sourceURI.replace("course", courseDesimal);

		getCourseInfo(sourceURI);
	}
	
	public void getCourseInfo(String sourceURI){	
		
		try {
			URL url = new URL(sourceURI);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			String inputLine;
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuffer response = new StringBuffer();

			int responseCode = conn.getResponseCode();
			System.out.println("\nSending 'Get' request to URL :" + url);
			System.out.println("Response code: " + responseCode);
			if (responseCode != 200)
				throw new RuntimeException("HttpResponseCode: " + responseCode);
			else {

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
			}
			// Printar hela json fr�n uri
			// System.out.println(response.toString());
			// JSONParser parser = new JSONParser(myResponse.toString(););
			// myResponse.toString();
			JSONObject jobj = new JSONObject(response.toString());
			JSONArray jsonarr_1 = (JSONArray) jobj.get("reservations");
			JSONArray jsonarr_2 = new JSONArray();
			getCourse course = new getCourse();
			System.out.println("Elements under results array");
			for (int i = 0; i < jsonarr_1.length(); i++) {
				// Store the JSON objects in an array
				// Get the index of the JSON object and print the values as per the index
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);
				jsonarr_2 = (JSONArray) jsonobj_1.get("columns");
				// Store the JSON object in JSON array as objects (For level 2 array element i.e
				// Address Components)
				// ----JSONArray jsonarr_2 = (JSONArray) jsonobj_1.get("address_components");

//				System.out.println("\nLecture id: " + jsonobj_1.get("id"));
//				System.out.println("Date: " + jsonobj_1.get("startdate"));
//				System.out.println("Start Time: " + jsonobj_1.get("starttime"));
//				System.out.println("End Time: " + jsonobj_1.get("endtime"));
				
				course.setId(jsonobj_1.getString("id"));
				course.setStartdate(jsonobj_1.getString("startdate"));
				course.setStarttime(jsonobj_1.getString("starttime"));
				course.setEndtime(jsonobj_1.getString("endtime"));
				
				System.out.println(course.getId());
				System.out.println(course.getStartdate());
				System.out.println(course.getStarttime());
				System.out.println(course.getEndtime());

				ArrayList<String> courseInfo = new ArrayList<>();
				for (int j = 0; j < jsonarr_2.length(); j++) {

					courseInfo.add(jsonarr_2.getString(j));

				}
				int index = 0;
				for (String s : courseInfo)
					System.out.println((index++) + ": " + s);
					
//					course.setId(courseInfo.get(2));
//					System.out.println(course.getId());
					
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
