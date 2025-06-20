package lk.ac.vau.fas.ict.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lk.ac.vau.fas.ict.models.Book;
import lk.ac.vau.fas.ict.models.Borrow;
import lk.ac.vau.fas.ict.models.Student;
import lk.ac.vau.fas.ict.repository.BookRepository;
import lk.ac.vau.fas.ict.repository.BorrowRepository;
import lk.ac.vau.fas.ict.repository.StudentRepository;

@Service
public class BorrowService {
    @Autowired
    public BorrowRepository borrowRepository;

    @Autowired
    public BookRepository bookRepository;

    @Autowired
    public StudentRepository studentRepository;

    // Add a new Borrow Entry
    public Borrow addNewEntry(Borrow borrow) {
        Book book = bookRepository.findById(borrow.getBook().getId())
                .orElseThrow(() -> new EntityNotFoundException("Book Not Found"));

        Student student = studentRepository.findById(borrow.getStudent().getId())
                .orElseThrow(() -> new EntityNotFoundException("Student Not Found"));

        // Check available copies
        if (book.getCopiesAvailable() <= 1) {
            throw new IllegalStateException("Only one copy left. This must be reserved.");
        }

        // Count unreturned books
        long unreturnedCount = student.getBorrows().stream()
                .filter(b -> b.getReturned() == Borrow.Status.NO)
                .count();

        if (unreturnedCount >= 2) {
            throw new IllegalStateException("Student already has more than two unreturned books.");
        }

        // Proceed to borrow
        book.setCopiesAvailable(book.getCopiesAvailable() - 1);

        borrow.setBook(book);
        borrow.setStudent(student);
        borrow.setBorrowDate(new java.sql.Date(System.currentTimeMillis()));
        borrow.setReturned(Borrow.Status.NO);

        return borrowRepository.save(borrow);
    }

}