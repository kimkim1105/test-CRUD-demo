package com.example.demospring.repository;

import com.example.demospring.model.Book;
import com.example.demospring.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
    @Query(value = "select * from books where status = true and id not in (select book_id from borrowbooks where date between (select adddate(curdate(),-3)) and curdate())",nativeQuery = true)
    Iterable<Book> getListFreeBook();
    @Query(value = "select * from books where status = true and id in (select book_id from borrowbooks where date between (select adddate(curdate(),-3)) and curdate())",nativeQuery = true)
    Iterable<Book> getListBookInBorrow();
    Iterable<Book> findAllByStatusIsTrueOrderByIdDesc();
    Iterable<Book> findAllByNameContainingAndStatusIsTrueOrderByIdDesc(String name);
    Iterable<Book> findAllByNameContaining(String name);


}
