package lk.vau.fas.ict.dayseven.model;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class workshop {
    @Id
	private int id;
	private String description;
	private Date end_date;
	private String name;
	private Date start_date;

    @ManyToMany
    private List<under_graduate>undergraduates;

    @ManyToMany
    private List<post_graduate>postgraduates;


	
	
	public workshop(int id, String description, Date end_date, String name, Date start_date) {
		super();
		this.id = id;
		this.description = description;
		this.end_date = end_date;
		this.name = name;
		this.start_date = start_date;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getEnd_date() {
		return end_date;
	}


	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getStart_date() {
		return start_date;
	}


	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}


    public List<under_graduate> getUndergraduates() {
        return undergraduates;
    }


    public void setUndergraduates(List<under_graduate> undergraduates) {
        this.undergraduates = undergraduates;
    }


    public List<post_graduate> getPostgraduates() {
        return postgraduates;
    }


    public void setPostgraduates(List<post_graduate> postgraduates) {
        this.postgraduates = postgraduates;
    }


}
