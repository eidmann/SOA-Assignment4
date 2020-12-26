package main.java.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
	
	public String searchCourse(String courseCode) {
		String sourceURI = "https://cloud.timeedit.net/ltu/web/schedule1/objects.txt?max=15&fr=t&partajax=t&im=f&sid=3&l=sv_SE&search_text=course&types=28";
		sourceURI = sourceURI.replace("course", courseCode);
		
		ObjectMapper mapper = new ObjectMapper();
		String result="";
		try {
			result = mapper.writeValueAsString(getCourseID(sourceURI));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<getCourse> getCourseID(String searchURI) {
		
		ArrayList<getCourse> lekt = new ArrayList();
		JSONArray jsonarr_1 = new JSONArray();

		try {
			URL url = new URL(searchURI);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			String inputLine;
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			StringBuffer response = new StringBuffer();

			int responseCode = conn.getResponseCode();
			System.out.println("Response code is: " + responseCode);
			if (responseCode != 200)
				throw new RuntimeException("HttpResponseCode: " + responseCode);
			else {

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}

				in.close();
			}

			JSONObject jobj = new JSONObject(response.toString());
			jsonarr_1 = (JSONArray) jobj.get("records");
			if (jsonarr_1.length() == 0) {
				System.out.println("Couldnt find your course");
			} else {
				System.out.println("Elements under results array");
				for (int i = 0; i < jsonarr_1.length(); i++) {
					getCourse course = new getCourse();

					JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);

					course.setDecimalId(jsonobj_1.getString("idAndType"));
					course.setValue(jsonobj_1.getString("values"));
					course.setPlats(getCourseType(jsonobj_1.getString("textId")));

					System.out.println(course.getDecimalId() + "- " + course.getValue());
					lekt.add(course);

				}
			}
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lekt;
	}
	
	public String getCourseType(String courseID) {
		
		String sourceURI = "https://cloud.timeedit.net/ltu/web/schedule1/objects/courseID/o.json?fr=t&types=28&sid=3&l=sv_SE";
		sourceURI = sourceURI.replace("courseID", courseID);
		String type ="";
		
		
		try {
			URL url = new URL(sourceURI);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			String inputLine;
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			StringBuffer response = new StringBuffer();

			int responseCode = conn.getResponseCode();
			System.out.println("Response code is: " + responseCode);
			if (responseCode != 200)
				throw new RuntimeException("HttpResponseCode: " + responseCode);
			else {

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}

				in.close();
			}

			JSONObject jobj = new JSONObject(response.toString());
			type = jobj.get("Kommentar").toString();

			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return type;
	}
	
	
	public ArrayList<getCourse> searchCourseInfo(String courseId, String startDate, String endDate) {
		ArrayList<getCourse> lekt = new ArrayList();
//		System.out.println("Choose a decimal number(två sissta 2020) ex:133649.28");
//		String courseDecimal = input.nextLine();
//		System.out.println("Choose startdate, all in a row, no spaces, / or -/n ex:20200901");
//		String searchStartDate = input.nextLine();
//		System.out.println("Choose enddate, all in a row, no spaces, / or -/n ex:20210117");
//		String searchEndDate = input.nextLine();
		String sourceURI = "https://cloud.timeedit.net/ltu/web/schedule1/ri.json?h=t&sid=3&p=start.x,end.x&objects=course&ox=0&types=0&fe=0";
		sourceURI = sourceURI.replace("course", courseId);
		sourceURI = sourceURI.replace("start", startDate);
		sourceURI = sourceURI.replace("end", endDate);

		if (courseId.isEmpty()) {
			System.out.println("Non valid search");
		} else {
			getCourseInfo(sourceURI, lekt);
			System.out.println(lekt);
//			System.out.println(lekt.get(19));
		}
		return lekt;
	}

	public ArrayList<getCourse> getCourseInfo(String sourceURI, ArrayList<getCourse> lekt) {

		try {
			URL url = new URL(sourceURI);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			String inputLine;
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
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

			JSONObject jobj = new JSONObject(response.toString());
			JSONArray jsonarr_1 = (JSONArray) jobj.get("reservations");
			JSONArray jsonarr_2 = new JSONArray();

			System.out.println("Elements under results array");
			for (int i = 0; i < jsonarr_1.length(); i++) {
				getCourse course = new getCourse();

				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);
				jsonarr_2 = (JSONArray) jsonobj_1.get("columns");


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
					course.setColumns(courseInfo);

				}
				int index = 0;
				for (String s : courseInfo)
					System.out.println((index++) + ": " + s);

				lekt.add(course);


			} // slut p� f�rsta loopen
//			for (getCourse lekt2 : lekt) {
//				System.out.println(lekt2); // Will invoke overrided `toString()` method
//			}
//			int pos = 0;
//
//			Boolean done = false;
//			Boolean print = false;
//			// do
//			while (done == false) {
//				System.out.println("V�lj vilket lektions ID du vill �ndra.");
//				print = false;
//				String lid = input.next();
//				for (int i = 0; i < lekt.size(); i++) {
//					String id = lekt.get(i).getId();
//					if (id.equals(lid)) {
//						print = true;
//						lekt.get(i).getColumns();
//						System.out.println("Vilken position vill du �ndra? 0-12");
//						pos = input.nextInt();
//						if (pos <= 12 || pos >= 0) {
//							System.out.println("Skriv in ny info");
//							String newInfo = input.next();
//							lekt.get(i).setColumnsPos(pos, newInfo);
//						}
//						System.out.println("�ndra fler? j/n");
//						if (input.next().equals("n")) {
//							done = true;
//						}
//					}
//					if (print == true) {
//						System.out.println(lekt.get(i).getColumns());
//					}
//				}
//			}
			// while(done == false);

			// System.out.println("V�lj vilket lektions ID du vill �ndra.");

		} catch (Exception e) {
			System.out.println(e);
		}
		return lekt;
	}

	public ArrayList<getCourse> searchLektInfo(String courseId, String startDate, String endDate, String lektId) {
		ArrayList<getCourse> lekt = new ArrayList();
//		System.out.println("Choose a decimal number(två sissta 2020) ex:133649.28");
//		String courseDecimal = input.nextLine();
//		System.out.println("Choose startdate, all in a row, no spaces, / or -/n ex:20200901");
//		String searchStartDate = input.nextLine();
//		System.out.println("Choose enddate, all in a row, no spaces, / or -/n ex:20210117");
//		String searchEndDate = input.nextLine();
		String sourceURI = "https://cloud.timeedit.net/ltu/web/schedule1/ri.json?h=t&sid=3&p=start-end&objects=course&ox=0&types=0&fe=0&id=class&fr=t&step=0";
		sourceURI = sourceURI.replace("course", courseId);
		sourceURI = sourceURI.replace("start", startDate);
		sourceURI = sourceURI.replace("end", endDate);
		sourceURI = sourceURI.replace("class", lektId);

		if (courseId.isEmpty()) {
			System.out.println("Non valid search");
		} else {
			getCourseInfo(sourceURI, lekt);
			System.out.println(lekt);
//			System.out.println(lekt.get(19));
		}
		return lekt;
	}

}
