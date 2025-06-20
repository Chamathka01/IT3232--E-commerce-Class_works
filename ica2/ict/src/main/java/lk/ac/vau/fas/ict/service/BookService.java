package lk.ac.vau.fas.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lk.ac.vau.fas.ict.models.Book;
import lk.ac.vau.fas.ict.repository.BookRepository;

@Service
public class BookService {
    @Autowired
    public BookRepository bookRepository;

    // Get book by genre
    public List<Book> getBooksByGenre(String genre){
        if(bookRepository.getByGenre(genre).isEmpty()){
            throw new EntityNotFoundException("Books Not Found for mentioned Genre");
        }
        return bookRepository.getByGenre(genre);
    }
}