package lk.ac.vau.fas.myapp.model;
public class Course {
    private String code;
	private String name;
	private String credits;
    public Course(String code, String name, String credits) {
        this.code = code;
        this.name = name;
        this.credits = credits;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCredits() {
        return credits;
    }
    public void setCredits(String credits) {
        this.credits = credits;
    }

      
}
