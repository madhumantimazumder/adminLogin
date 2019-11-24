package iiitb.ss.admin.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Specialization {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int spId;
	@Column(nullable = false)
	private String name;
	public int getSpId() {
		return spId;
	}
	public void setSpId(int spId) {
		this.spId = spId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Specialization [spId=" + spId + ", name=" + name + "]";
	}
	
}
