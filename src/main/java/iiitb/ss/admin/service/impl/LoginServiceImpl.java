package iiitb.ss.admin.service.impl;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import iiitb.ss.admin.DAO.AdminDAO;
import iiitb.ss.admin.bean.Employee;
import iiitb.ss.admin.service.LoginService;

public class LoginServiceImpl implements LoginService{

	public String processData(String data) {
		JSONObject jObj;
		Employee admin=new Employee();
		try {
			jObj = (JSONObject)new JSONParser().parse(data);
			String username = (String) jObj.get("name");
			String password = (String) jObj.get("password");
			admin=new AdminDAO().getEmployeeLogin(username, password);
		
			if(admin==null) {
				return null;
			}
			else if(admin.getPassword().equals(password) && admin.getUsername().equals(username)) {
				return admin.getEmpName();
			}
			else {
				return null;
			}
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		return admin.getEmpName();
		
	}

}
