package com.example.demospring.repository;

import com.example.demospring.model.BorrowBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface IBorrowBookRepository extends JpaRepository<BorrowBook,Long> {
    Page<BorrowBook> findAllByStatusIsTrueOrderByDateDesc(Pageable pageable);
    @Query(value = "select * from borrowbooks where status = true and type = 'completed'",nativeQuery = true)
    Iterable<BorrowBook> getOrderCompleted();
    @Query(value = "select * from borrowbooks where status = true and date between (select adddate(curdate(),-3)) and curdate()",nativeQuery = true)
    Iterable<BorrowBook> getOrderUnCompleted();
    @Query(value = "select * from borrowbooks where status = true and (student_id in (select id from  students where name like :search) or book_id in (select id from books where  name like :search)) order by date desc", nativeQuery = true)
    Page<BorrowBook> searchByBookOrStudent(String search, Pageable pageable);
    @Query(value = "select count(id) from borrowbooks where date = :localDate", nativeQuery = true)
    Integer countOrderByDateBorrow(LocalDate localDate);
}
