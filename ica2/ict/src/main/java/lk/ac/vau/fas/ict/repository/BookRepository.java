package lk.ac.vau.fas.ict.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lk.ac.vau.fas.ict.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String>{
    
    // Filter Books by genre
    @Query("SELECT b from Book b WHERE b.genre=?1")
    public List<Book> getByGenre(String genre);
}