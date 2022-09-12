package com.example.demospring.service;

import com.example.demospring.model.BorrowBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface IBorrowBookService extends IGeneralService<BorrowBook> {
    Page<BorrowBook> findAllByStatusIsTrueOrderByDateDesc(Pageable pageable);
    Iterable<BorrowBook> getOrderCompleted();
    Iterable<BorrowBook> getOrderUnCompleted();
    Page<BorrowBook> searchByBookOrStudent(String search, Pageable pageable);
    Integer countOrderByDateBorrow(LocalDate localDate);
}
