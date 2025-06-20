package lk.ac.vau.fas.ict.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lk.ac.vau.fas.ict.models.Student;
import lk.ac.vau.fas.ict.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    public StudentService studentService;

    @GetMapping("/{bookId}")
    public ResponseEntity<List<Student>> getBorrowedStudents(@PathVariable("bookId") String bookId) {
        return new ResponseEntity<List<Student>>(studentService.getBorrowedStudent(bookId),HttpStatus.OK);
    }
}