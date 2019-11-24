package iiitb.ss.admin.service;

import java.util.List;

import iiitb.ss.admin.bean.Courses;
import iiitb.ss.admin.bean.Specialization;

public interface CourseService {

	public List<Courses> getCourse() ;
	public List<Specialization> getSpecializaton();
	public String processData(String data);
}
