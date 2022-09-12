package com.example.demospring.repository;

import com.example.demospring.model.Book;
import com.example.demospring.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
    @Query(value = "select * from books where status = true and active = 'free'",nativeQuery = true)
    Iterable<Book> getListFreeBook();
    @Query(value = "select * from books where status = true and active = 'borrowing'",nativeQuery = true)
    Iterable<Book> getListBookInBorrow();
//    Iterable<Book> findAllByStatusIsTrueOrderByIdDesc();
    Page<Book> findAllByStatusIsTrueOrderByIdDesc(Pageable pageable);
//    Iterable<Book> findAllByNameContainingAndStatusIsTrueOrderByIdDesc(String name);
    Page<Book> findAllByNameContainingAndStatusIsTrueOrderByIdDesc(String name,Pageable pageable);
    Iterable<Book> findAllByNameContaining(String name);


}
