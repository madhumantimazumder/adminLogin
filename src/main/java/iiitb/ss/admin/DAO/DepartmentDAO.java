package iiitb.ss.admin.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import iiitb.ss.admin.bean.Department;
import iiitb.ss.admin.util.SessionUtil;

public class DepartmentDAO {
	private List<Department> dept=new ArrayList<Department>();
	public String getDepartmentNameById(int id){
		Session session = SessionUtil.getSession(); 
		session.beginTransaction();
		Query q = session.createQuery("from Department where DeptID = :deptid");
		q.setParameter("deptid", id);  
		dept =  q.list();
		session.close();
		return dept.get(0).getName();
	}
}
