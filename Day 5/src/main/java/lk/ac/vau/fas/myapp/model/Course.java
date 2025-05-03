package lk.ac.vau.fas.myapp.model;
public class Course {
    private String courseId;
	private String courseName;
	private String lecName;

    public Course(String courseId, String courseName, String lecName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.lecName = lecName;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getLecName() {
        return lecName;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setLecName(String lecName) {
        this.lecName = lecName;
    }

    



    
}
