package iiitb.ss.admin.DAO;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import iiitb.ss.admin.util.SessionUtil;
import iiitb.ss.admin.bean.Courses;

public class CourseDAO {
	private  List<Courses> courses=new ArrayList<Courses>();
	
	public List<Courses> getCourse(){		
		
		  Session session = SessionUtil.getSession(); 
		  session.beginTransaction();
		  courses =session.createQuery("from Courses").list();

		  
		  for (Iterator<Courses> iter = courses.iterator(); iter.hasNext();) { 
			  Courses element = (Courses) iter.next(); 
			  Hibernate.initialize(element.getPre_req());
			  Hibernate.initialize(element.getSchedule());
			  Hibernate.initialize(element.getSp()); 
		  }
		 
		  session.close();
		  return courses;
		
	}
	public Courses getCourseByCourseID(String id) {
		Session session = SessionUtil.getSession(); 
		session.beginTransaction();
		  
		  List result = session.createSQLQuery(
		  "select * from Courses s where s.courseId = :courseId")
		  .addEntity(Courses.class)
		  .setParameter("courseId",id).list();
		  session.close();
		  return (Courses) result.get(0);
	}
	
	  public void processData(Courses course) throws SQLException { 
		    
	        Session session = SessionUtil.getSession(); 
			session.beginTransaction();
			session.save(course);
			session.getTransaction().commit();
			session.close();        
			
	 }
}
