package iiitb.ss.admin.service.impl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import iiitb.ss.admin.DAO.CourseDAO;
import iiitb.ss.admin.DAO.SpecializationDAO;
import iiitb.ss.admin.bean.Courses;
import iiitb.ss.admin.bean.Schedule;
import iiitb.ss.admin.bean.Specialization;
import iiitb.ss.admin.service.CourseService;

public class CourseServiceImpl implements CourseService {
	public List<Courses> getCourse() {
		CourseDAO cDAO=new CourseDAO();
        List<Courses> c=cDAO.getCourse();
        return c;
	}
	public List<Specialization> getSpecializaton(){
		SpecializationDAO cDAO=new SpecializationDAO();
	    List<Specialization> s=cDAO.getSpecializaton();
	    return s;
	}
	public String processData(String data) {
		CourseDAO crDAO=new CourseDAO();
		Courses course=new Courses();
		Set<Specialization> sp=new HashSet<Specialization>();
		Set<Schedule> sc=new HashSet<Schedule>();
		Set<Courses> prereqs=new HashSet<Courses>();
		try {
			JSONObject jObj =(JSONObject)new JSONParser().parse(data);
			course.setCapacity(Integer.parseInt((String) jObj.get("capacity")));
			course.setCredit(Integer.parseInt((String) jObj.get("credit")));
			course.setName((String) jObj.get("name"));
			course.setCourseId(Integer.parseInt((String) jObj.get("course_code")));
			
			JSONArray ja = (JSONArray) jObj.get("schedule");
			sc=processSchedule(sc,ja);
			
			
			JSONArray pre_reqs = (JSONArray) jObj.get("preReq");
			prereqs=processPreReq(prereqs,pre_reqs,crDAO);
			   
			    
			JSONArray specs = (JSONArray) jObj.get("specializations");
			sp=processSpecs(sp,specs);
			    
			course.setPre_req(prereqs);
			course.setSchedule(sc);
			course.setSp(sp);
			try {
				crDAO.processData(course);
			} catch (Exception e) {
				return "202";
			}
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
			return "200";
	}
	private Set<Specialization> processSpecs(Set<Specialization> sp, JSONArray specs) {
		Iterator itr2 = specs.iterator(); 	 
	    while (itr2.hasNext())  
	    { 
	    	SpecializationDAO cDAO=new SpecializationDAO();
	    	Specialization pre=new Specialization();
	    	JSONObject sObj =(JSONObject) itr2.next();
	    	pre=cDAO.getSpecializatonByID(Integer.parseInt((String)sObj.get("spId")));
	    	sp.add(pre);
	    }
		return sp; 
	}
	private Set<Courses> processPreReq(Set<Courses> prereqs, JSONArray pre_reqs, CourseDAO crDAO) {
		Iterator itr2 = pre_reqs.iterator(); 	 
	    while (itr2.hasNext())  
	    { 
	    	Courses pre=new Courses();
	    	JSONObject sObj =(JSONObject) itr2.next();
	    	pre=crDAO.getCourseByCourseID(Integer.parseInt((String)sObj.get("courseId")));
	    	prereqs.add(pre);
	    }
		return prereqs; 
	}
	public static Set<Schedule> processSchedule(Set<Schedule> sc, JSONArray ja) {
		Iterator itr2 = ja.iterator(); 	          
	    while (itr2.hasNext())  
	    { 
			Schedule schedule=new Schedule();
			JSONObject sObj =(JSONObject) itr2.next();
			schedule.setDay((String)sObj.get("day"));
			schedule.setTime((String)sObj.get("time"));
			sc.add(schedule);	                        
	    } 
	    return sc;
	}
}
