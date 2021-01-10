package main.java.api;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("response")
public class GetResponse {

	
	@GET
	//@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response Message(){
		return Response.ok("Hej").build();
	}
	
	
	@GET
	@Path("/kurskod/{kod}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response Courses(@PathParam("kod") String courseCode){
		String App = new App().searchCourse(courseCode);
		return Response.ok(App).build();
	}
	
	@GET
	@Path("/kursid/{id}/{startDate}/{endDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response events(@PathParam("id") String courseId,@PathParam("startDate") String startDate, @PathParam("endDate") String endDate){
		ArrayList<getCourse> App = new App().searchCourseInfo(courseId,startDate,endDate);
		App.toString();
		return Response.ok(App).build();
	}
	@GET
	@Path("/lektid/{id}/{startDate}/{endDate}/{lektId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response clas(@PathParam("id") String courseId,@PathParam("startDate") String startDate, @PathParam("endDate") String endDate, @PathParam("lektId") String lektId){
		ArrayList<getCourse> App = new App().searchLektInfo(courseId,startDate,endDate, lektId);
		App.toString();
		return Response.ok(App).build();
	}	
	@POST
	@Path("/postEvent/{date}/{starttime}/{endtime}/{info}/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response postEvent(@PathParam("date")String date,@PathParam("starttime")String startTime,@PathParam("endtime")String endTime,@PathParam("info")String info,@PathParam("title")String titel) throws Exception {
		
		test3 test = new test3();
		
		test.sendCanvas(date,startTime,endTime,info,titel);
		
		return Response.created(URI.create("response/postEvent/" + titel + "/" + date + "/" + startTime + "/" + endTime + "/" + info)).build();
		
	}
	
}
