package iiitb.ss.admin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Schedule {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int sc_id;
	@Column(nullable = false)
	private String day;
	@Column(nullable = false)
	private String time;	
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "id")
	 */
	/*
	 * private Courses course;
	 * 
	 * public Courses getCourse() { return course; } public void setCourse(Courses
	 * course) { this.course = course; }
	 */
	public int getId() {
		return sc_id;
	}
	public void setId(int sc_id) {
		this.sc_id = sc_id;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
