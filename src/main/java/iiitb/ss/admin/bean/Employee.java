package iiitb.ss.admin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int EmpID;
	
	@Column(nullable=false) 
	private String EmpName;
	@Column(nullable=false) 
	private String Username;
	@Column(nullable=false)
	private String Password;
	
	
	@ManyToOne
    @JoinColumn(name = "DeptID",nullable=false)
	private Department dept;
	
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public int getEmpID() {
		return EmpID;
	}
	public void setEmpID(int empID) {
		EmpID = empID;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmpName() {
		return EmpName;
	}
	public void setEmpName(String empName) {
		EmpName = empName;
	}
	/*@Override
	public String toString() {
		return "Admin [EmpID=" + EmpID + ", EmpName=" + EmpName + ", DeptID=" + DeptID + "]";
	}*/
}
