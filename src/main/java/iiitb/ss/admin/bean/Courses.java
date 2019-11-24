package iiitb.ss.admin.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Courses {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int id;
	@Column(unique=true, nullable=false) 
	private int courseId;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private int credit;
	@Column(nullable = false)
	private int capacity;
	
	@Column(nullable = false)
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval=true)
	@JoinColumn(name="courseId" , referencedColumnName="id")
	private Set<Schedule> schedules=new HashSet<Schedule>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "PreReq", joinColumns = { @JoinColumn(name="courseId" , referencedColumnName="id") }, inverseJoinColumns = { @JoinColumn(name = "preReqId") })
	private Set<Courses> pre_req=new HashSet<Courses>();
	
	@Column(nullable = false)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "CourseSpecialization", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = { @JoinColumn(name = "spId") })
	private Set<Specialization> sp=new HashSet<Specialization>();
	
	
	public Set<Schedule> getSchedule() {
		return schedules;
	}
	public void setSchedule(Set<Schedule> schedules) {
		this.schedules = schedules;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Set<Specialization> getSp() {
		return sp;
	}
	public void setSp(Set<Specialization> sp) {
		this.sp = sp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public Set<Courses> getPre_req() {
		return pre_req;
	}
	public void setPre_req(Set<Courses> pre_req) {
		this.pre_req = pre_req;
	}
	
	@Override 
	public String toString() { return "Courses [courseId=" + courseId +
	  ", name=" + name + ", credit=" + credit + ", capacity=" + capacity +
	  ", pre_req=" + pre_req + "]"; }
	 
}
