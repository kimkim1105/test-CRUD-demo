package com.example.demospring.service;

import com.example.demospring.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookService extends IGeneralService<Book>{
    Iterable<Book> getListFreeBook();
    Page<Book> findAllByStatusIsTrueOrderByIdDesc(Pageable pageable);
    Page<Book> findAllByNameContainingAndStatusIsTrueOrderByIdDesc(String name,Pageable pageable);
    Iterable<Book> getListBookInBorrow();
    Boolean checkBookInBorrow(Book book);
    Iterable<Book> findAllByNameContaining(String name);
}
