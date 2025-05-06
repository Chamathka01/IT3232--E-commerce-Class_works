package lk.vau.fas.ict.dayseven.model;
import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class session {
    @Id
	private int id;
	private Date date;
	private String description;
	private String name;
	private Time time;
	private int workshop_id;
	
	
	public session(int id, Date date, String description, String name, Time time, int workshop_id) {
		super();
		this.id = id;
		this.date = date;
		this.description = description;
		this.name = name;
		this.time = time;
		this.workshop_id = workshop_id;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Time getTime() {
		return time;
	}


	public void setTime(Time time) {
		this.time = time;
	}


	public int getWorkshop_id() {
		return workshop_id;
	}


	public void setWorkshop_id(int workshop_id) {
		this.workshop_id = workshop_id;
	}
}
