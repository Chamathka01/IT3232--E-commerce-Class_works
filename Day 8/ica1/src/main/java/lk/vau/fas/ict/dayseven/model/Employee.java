import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Employee extends Person{
    @Id
    private Long studentId;
    private String jobPosition;
    
    public Long getStudentId() {
        return studentId;
    }
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    public String getJobPosition() {
        return jobPosition;
    }
    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    
}
