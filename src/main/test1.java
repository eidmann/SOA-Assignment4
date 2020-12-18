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

		try {
			//Här ska översatta kurs koden från test komma
			
			test1.searchCourseInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void searchCourseInfo(){
		/*Ska kunna modda datum i länken, skulle vara opti med
		 * -att se vilka datum varje kod gäller för
		 * -kanske skicka med om det är distans/campus
		 * 
		 * Worst case får vi bara modda datum i blindo
		 */
		Scanner input = new Scanner(System.in);
		System.out.println("Choose a decimal number(två sissta 2020) ex:133649.28");
		String courseDecimal = input.nextLine();
		System.out.println("Choose startdate, all in a row, no spaces, / or -/n ex:20200901");
		String searchStartDate = input.nextLine();
		System.out.println("Choose enddate, all in a row, no spaces, / or -/n ex:20210117");
		String searchEndDate = input.nextLine();
		String sourceURI = "https://cloud.timeedit.net/ltu/web/schedule1/ri.json?h=t&sid=3&p=start.x,end.x&objects=course&ox=0&types=0&fe=0";
		sourceURI = sourceURI.replace("course", courseDecimal);
		sourceURI = sourceURI.replace("start", searchStartDate);
		sourceURI = sourceURI.replace("end", searchEndDate);
		
		if(courseDecimal.isEmpty()) {
			System.out.println("Non valid search");
			searchCourseInfo();
		}else {
			getCourseInfo(sourceURI);
		}
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
