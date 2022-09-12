package com.example.demospring.service;

import com.example.demospring.model.BorrowBook;

public interface IBorrowBookService extends IGeneralService<BorrowBook> {
    Iterable<BorrowBook> findAllByStatusIsTrueOrderByDateDesc();
    Iterable<BorrowBook> getOrderCompleted();
    Iterable<BorrowBook> getOrderUnCompleted();
    Iterable<BorrowBook> searchByBookOrStudent(String search);
}
