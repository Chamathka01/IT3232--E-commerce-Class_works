package lk.vau.fas.ict.dayseven.model;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class post_graduate extends Person {
    @Id
	 private int id;
	 private String institute;
	 private String research_interest;
	 private String second_degree;

     @ManyToMany(mappedBy = "postgraduates")
     private List<workshop>workshops;
	
	 
	 public post_graduate(int id, String email, String gender, String name, long phone, String institute,
			String research_interest, String second_degree) {
		super();
		this.id = id;
		this.institute = institute;
		this.research_interest = research_interest;
		this.second_degree = second_degree;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInstitute() {
		return institute;
	}
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	public String getResearch_interest() {
		return research_interest;
	}
	public void setResearch_interest(String research_interest) {
		this.research_interest = research_interest;
	}
	public String getSecond_degree() {
		return second_degree;
	}
	public void setSecond_degree(String second_degree) {
		this.second_degree = second_degree;
	}
    public List<workshop> getWorkshops() {
        return workshops;
    }
    public void setWorkshops(List<workshop> workshops) {
        this.workshops = workshops;
    }

}
