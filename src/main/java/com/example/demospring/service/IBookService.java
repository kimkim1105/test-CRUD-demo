package com.example.demospring.service;

import com.example.demospring.model.Book;

public interface IBookService extends IGeneralService<Book>{
    Iterable<Book> getListFreeBook();
    Iterable<Book> findAllByStatusIsTrueOrderByIdDesc();
    Iterable<Book> findAllByNameContainingAndStatusIsTrueOrderByIdDesc(String name);
    Iterable<Book> getListBookInBorrow();
    Boolean checkBookInBorrow(Book book);
    Iterable<Book> findAllByNameContaining(String name);
}
