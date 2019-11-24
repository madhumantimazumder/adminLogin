package iiitb.ss.admin.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import iiitb.ss.admin.bean.Courses;
import iiitb.ss.admin.bean.Specialization;
import iiitb.ss.admin.util.SessionUtil;

public class SpecializationDAO {
	private  List<Specialization> spec=new ArrayList<Specialization>();	
	public List<Specialization> getSpecializaton(){		
		
		  Session session = SessionUtil.getSession(); session.beginTransaction();
		  spec =session.createQuery("from Specialization").list();
		  session.close();
		  return spec;
		
	}
	public Specialization getSpecializatonByID(int id){		
		
		Session session = SessionUtil.getSession(); 
		session.beginTransaction();
		  
		  List result = session.createSQLQuery(
		  "select * from Specialization s where s.spId = :spId")
		  .addEntity(Specialization.class)
		  .setParameter("spId",id).list();
		  session.close();
		  return (Specialization) result.get(0);
		
	}
}
