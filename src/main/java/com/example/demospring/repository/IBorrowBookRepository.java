package com.example.demospring.repository;

import com.example.demospring.model.BorrowBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBorrowBookRepository extends JpaRepository<BorrowBook,Long> {
    Iterable<BorrowBook> findAllByStatusIsTrue();
    @Query(value = "select * from borrowbooks where status = true and date not between (select adddate(curdate(),-3)) and curdate()",nativeQuery = true)
    Iterable<BorrowBook> getOrderCompleted();
}
