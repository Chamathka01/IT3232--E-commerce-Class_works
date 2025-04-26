package lk.vau.fas.ict.dayseven.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity	
public class under_graduate extends Person {
    @Id
	 private int id;
	 private String degree;
	 private String university;
	
	 
	 public under_graduate(int id, String email, String gender, String name, long phone, String degree,
			String university) {
		super();
		this.id = id;
		this.degree = degree;
		this.university = university;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getDegree() {
		return degree;
	}


	public void setDegree(String degree) {
		this.degree = degree;
	}


	public String getUniversity() {
		return university;
	}


	public void setUniversity(String university) {
		this.university = university;
	}
}
