package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;
import org.json.JSONArray;

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
		// JSONObject json =
		// readJsonFromUrl("https://cloud.timeedit.net/ltu/web/schedule1/objects.html?max=15&fr=t&partajax=t&im=f&sid=3&l=sv_SE&search_text=D0023E&types=28");
		test test = new test();
		try {
			test.searchCourse();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchCourse() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter course code ex:D0023E");
		String courseCode = input.nextLine();
		String sourceURI = "https://cloud.timeedit.net/ltu/web/schedule1/objects.txt?max=15&fr=t&partajax=t&im=f&sid=3&l=sv_SE&search_text=course&types=28";
		sourceURI = sourceURI.replace("course", courseCode);

		getCourseID(sourceURI);
	}

	private void getCourseID(String searchURI) {
		
		// String uri =
		// "https://cloud.timeedit.net/ltu/web/schedule1/objects.txt?max=15&fr=t&partajax=t&im=f&sid=3&l=sv_SE&search_text=D0023E&types=28";
		try {
			URL url = new URL(searchURI);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			
			String inputLine;
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuffer response = new StringBuffer();

			int responseCode = conn.getResponseCode();
			System.out.println("Response code is: " + responseCode);
			if (responseCode != 200)
				throw new RuntimeException("HttpResponseCode: " + responseCode);
			else {
				
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
//				System.out.println("\nJSON data in string format");
//				System.out.println(inputLine);
				in.close();
			}

			JSONObject jobj = new JSONObject(response.toString());
			JSONArray jsonarr_1 = (JSONArray) jobj.get("records");
			getCourse course = new getCourse();
			if (jsonarr_1.length() == 0) {
				System.out.println("Couldnt find your course");
				searchCourse();
			}else {
			System.out.println("Elements under results array");
			for (int i = 0; i < jsonarr_1.length(); i++) {
				// Store the JSON objects in an array
				// Get the index of the JSON object and print the values as per the index
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);
				// Store the JSON object in JSON array as objects (For level 2 array element i.e
				// Address Components)
				// ----JSONArray jsonarr_2 = (JSONArray) jsonobj_1.get("address_components");

//				System.out.println("\nCourse id: " + jsonobj_1.get("idAndType"));
//				System.out.println("Course Name: " + jsonobj_1.get("values"));
				course.setDecimalId(jsonobj_1.getString("idAndType"));
				course.setValue(jsonobj_1.getString("values"));
				
				System.out.println(course.getDecimalId() + "- "+ course.getValue());
				// Get data for the Address Components array
				// System.out.println("The long names, short names and types are:");
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
			}
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
