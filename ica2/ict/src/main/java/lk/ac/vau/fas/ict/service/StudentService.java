package lk.ac.vau.fas.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lk.ac.vau.fas.ict.models.Student;
import lk.ac.vau.fas.ict.repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    public StudentRepository studentRepository;

    public List<Student> getBorrowedStudent(String bookId){
        if(studentRepository.getBorrowedStudent(bookId).isEmpty()){
            throw new EntityNotFoundException("Students Not Found");
        }
        return studentRepository.getBorrowedStudent(bookId);
    }
}