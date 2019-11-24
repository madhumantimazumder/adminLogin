package iiitb.ss.admin.controller;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

//import org.json.simple.parser.ParseException;

import iiitb.ss.admin.bean.Courses;
import iiitb.ss.admin.bean.Specialization;
import iiitb.ss.admin.service.impl.CourseServiceImpl;

import java.util.List;

@Path("/api")
public class CourseController {
	@GET  @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "working...";
    }

    @GET 
    @Path("/getAllCourses")
    @Produces({"application/json","application/xml"})
    public List<Courses> FindCourses() {        
        CourseServiceImpl cimpl=new CourseServiceImpl();
        List<Courses> c=cimpl.getCourse();
        return c;
    }
    @GET 
    @Path("/getAllSpecialization")
    @Produces({"application/json","application/xml"})
    public List<Specialization> FindSpecializations() {
    	CourseServiceImpl cimpl=new CourseServiceImpl();
        List<Specialization> s=cimpl.getSpecializaton();
        return s;
    }
    @POST 
    @Path("/addCourse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addCourseDb(String json) {
    	JSONObject jo = new JSONObject(); 
        CourseServiceImpl cimpl=new CourseServiceImpl();
        jo.put("status", cimpl.processData(json));
        return jo.toJSONString();        
    }
}

