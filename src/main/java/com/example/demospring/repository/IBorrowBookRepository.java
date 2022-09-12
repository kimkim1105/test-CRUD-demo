package com.example.demospring.repository;

import com.example.demospring.model.BorrowBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBorrowBookRepository extends JpaRepository<BorrowBook,Long> {
    Iterable<BorrowBook> findAllByStatusIsTrueOrderByDateDesc();
    @Query(value = "select * from borrowbooks where status = true and date not between (select adddate(curdate(),-3)) and curdate()",nativeQuery = true)
    Iterable<BorrowBook> getOrderCompleted();
    @Query(value = "select * from borrowbooks where status = true and date between (select adddate(curdate(),-3)) and curdate()",nativeQuery = true)
    Iterable<BorrowBook> getOrderUnCompleted();
    @Query(value = "select * from borrowbooks where status = true and (student_id in (select id from  students where name like :search) or book_id in (select id from books where  name like :search)) order by date desc", nativeQuery = true)
    Iterable<BorrowBook> searchByBookOrStudent(String search);
}
