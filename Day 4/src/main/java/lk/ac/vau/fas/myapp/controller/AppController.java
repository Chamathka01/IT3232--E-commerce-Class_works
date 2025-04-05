package lk.ac.vau.fas.myapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lk.ac.vau.fas.myapp.model.Student;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/app")
public class AppController {

    Student Bob = new Student("2020IT01","Bob Marely",23,"IT",3.2);
	Student Amal = new Student("2020IT02","Amal Perera",24,"AMC",3.7);
	Student Nimal = new Student("2020IT03","Nimal Hettiarachchi",22,"IT",3.0);
    private static List<Student> students = new ArrayList<Student>();
    private Map<String, Student> mstudents = new hashMap<String,Student>();

    public AppController() {
        students.add(Bob);
        students.add(Amal);
        students.add(Nimal);
    }

    @GetMapping("msg")
    public String myMessage() {
        return "Hello SpringBoot";
    }
    @GetMapping("/name")
	public String myName() {
		return "My name is SpringBoot";
	}
    @GetMapping("/age/{ag}")
	public String MyAge(@PathVariable("ag") int age) {
		return "My age is "+age;
	}
    //a method to return a student
    @GetMapping("/student")
	public Student getStudent() {
		return Nimal;
	}
    //return multiple students
    @GetMapping("/students")
	public List<Student> getStudents() {
		students.add(Bob);
		students.add(Amal);
		students.add(Nimal);
		return students;
	}
    //find a student from the list by regno
    @GetMapping("/student/{id}")
	public Student getStudent(@PathVariable("id") String regNo) {
		for(Student student : students) {
			if(student.getRegNo().equals(regNo)) {
				return student;
			}
		}	
		return null;
    }
    //find the students whose age is between 20 and 23
    @GetMapping("/students/age")
    public List<Student> getStudentsByAgeRange() {
    List<Student> filteredStudents = new ArrayList<>();
    for (Student student : students) {
        if (student.getAge() >= 20 && student.getAge() <= 23) {
            filteredStudents.add(student);
        }
    }
    return filteredStudents;
}
    //sort the students by their GPA
    @GetMapping("/order/all-students")
    public List<Student> getAllStudents() {
        students.sort((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa())); 
        return students;
    }
    //create CRUD operations for students
    //create
    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        students.add(student);  // Add the student to the list
        return student;
    }
    //Update
    @PutMapping("/student/update/{regno}")
    public Student updateStudent(@PathVariable("regno") String regNo, @RequestBody Student updatedStudent) {

        for (Student student : students) {
            if (student.getRegNo().equals(regNo)) {
                student.setName(updatedStudent.getName());
                student.setAge(updatedStudent.getAge());
                student.setCourse(updatedStudent.getCourse());
                student.setGpa(updatedStudent.getGpa());
                return student;
            }
        }
        return null;
    }
    //Delete
    @DeleteMapping("/student/delete/{regno}")
    public String deleteStudent(@PathVariable("regno") String regNo) {
        for (Student student : students) {
            if (student.getRegNo().equals(regNo)) {
                students.remove(student);
                return "Student with regNo " + regNo + " has been deleted."; 
            }
        }
        return "Student with regNo " + regNo + " not found."; 
    }

}