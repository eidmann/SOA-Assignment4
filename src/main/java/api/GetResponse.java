package main.java.api;

import java.util.ArrayList;

import javax.ws.rs.GET;
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
	
	
	
}
