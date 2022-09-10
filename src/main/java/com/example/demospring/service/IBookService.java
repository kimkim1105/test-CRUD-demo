package com.example.demospring.service;

import com.example.demospring.model.Book;

public interface IBookService extends IGeneralService<Book>{
    Iterable<Book> getListFreeBook();
    Iterable<Book> findAllByStatusIsTrue();
    Iterable<Book> findAllByNameContainingAndStatusIsTrue(String name);
    Iterable<Book> getListBookInBorrow();
}
