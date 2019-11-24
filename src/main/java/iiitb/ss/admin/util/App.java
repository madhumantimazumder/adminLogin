package iiitb.ss.admin.util;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import iiitb.ss.admin.bean.Employee;
import iiitb.ss.admin.bean.Courses;
import iiitb.ss.admin.bean.Department;
import iiitb.ss.admin.bean.Schedule;
import iiitb.ss.admin.bean.Specialization;
import iiitb.ss.admin.util.SessionUtil;


/*
 * Add dummy data in table using main
 */
public class App 
{
    public static void main( String[] args )
    {
    	App a=new App();
    	a.insertData();
    	a.getData();
    }
    private  List<Courses> courses=new ArrayList<Courses>();
    private void getData() {
      Session session = SessionUtil.getSession(); 
      session.beginTransaction();
	  courses =session.createQuery("from Courses").list();
	  for (Iterator iterator = courses.iterator(); iterator.hasNext();) {
          Courses employee = (Courses) iterator.next();
          System.out.println(employee);
         
      }
	  
	  session.close();
		
	}
	public void insertData() {
		
		
		Schedule sc=new Schedule();
		sc.setDay("Monday");
		sc.setTime("9:30");
		Schedule sc1=new Schedule();
		sc1.setDay("Monday");
		sc1.setTime("11:30");
		Set<Schedule> scs=new HashSet<Schedule>();
		scs.add(sc);
		scs.add(sc1);
		Schedule sc2=new Schedule();
		sc2.setDay("Monday");
		sc2.setTime("9:30");
		Schedule sc3=new Schedule();
		sc3.setDay("Monday");
		sc3.setTime("11:30");
		Set<Schedule> scs1=new HashSet<Schedule>();
		scs1.add(sc2);
		scs1.add(sc3);
		Specialization s=new Specialization();
		s.setName("DS");
		Specialization s1=new Specialization();
		s1.setName("CS");
		Courses course = new Courses();
		course.setCourseId(10);
		course.setCapacity(1);
		course.setCredit(1);
		course.setName("course1");
		
		
		Courses course1 = new Courses();
		course1.setCourseId(11);
		course1.setCapacity(1);
		course1.setCredit(1);
		course1.setName("course2");
		//course1.setSp(spc);
		course1.setSchedule(scs1);
		
		Courses course3 = new Courses();
		course3.setCourseId(12);
		course3.setCapacity(1);
		course3.setCredit(1);
		course3.setName("course3");
		//course3.setSp(spc);
		Session session = SessionUtil.getSession();
		session.beginTransaction();
		session.save(course1);
		session.save(course3);
		Department d=new Department();
		d.setDeptID(1);
		d.setName("Admin");
		Department d1=new Department();
		d1.setDeptID(2);
		d1.setName("Teacher");
		
		Employee admin=new Employee();
		admin.setDept(d);
		admin.setUsername("AD101");
		admin.setEmpName("Madhumanti");
		admin.setPassword("Madhumanti");
		
		Employee teacher=new Employee();
		teacher.setUsername("TC102");
		teacher.setEmpName("Ujan");
		teacher.setPassword("ujan");
		teacher.setDept(d1);
		
		session.save(d);
		session.save(d1);
		 session.save(s);
		 session.save(s1);
		 
		 session.save(admin);
		 session.save(teacher);
		session.getTransaction().commit();
		 session.close();
		 
		 
		Set<Courses> pre=new HashSet<Courses>();
		pre.add(course1);
		pre.add(course3);
		course.setPre_req(pre);
		
		
		Set<Specialization> spc=new HashSet<Specialization>();
		spc.add(s);
		spc.add(s1);
		course.setSp(spc);
		course.setSchedule(scs);
		
		 session = SessionUtil.getSession();
		 session.beginTransaction();
		 session.save(course);
		 
		 session.getTransaction().commit();
		 session.close();
    }
}
