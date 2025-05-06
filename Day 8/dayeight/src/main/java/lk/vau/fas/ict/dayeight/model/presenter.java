package lk.vau.fas.ict.dayseven.model;

    import jakarta.persistence.Entity;
    import jakarta.persistence.Id;  

    @Entity
    public class presenter {
        @Id
	private int id;
	private String email;
	private String gender;
	private String name;
	private long phone;
	private String affiliation;
	private String country;
	private String job_title;
	
	
	public presenter(int id, String email, String gender, String name, long phone, String affiliation, String country,
			String job_title) {
		super();
		this.id = id;
		this.email = email;
		this.gender = gender;
		this.name = name;
		this.phone = phone;
		this.affiliation = affiliation;
		this.country = country;
		this.job_title = job_title;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getPhone() {
		return phone;
	}


	public void setPhone(long phone) {
		this.phone = phone;
	}


	public String getAffiliation() {
		return affiliation;
	}


	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getJob_title() {
		return job_title;
	}


	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}
}
