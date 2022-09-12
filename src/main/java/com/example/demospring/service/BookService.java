package com.example.demospring.service;

import com.example.demospring.model.Book;
import com.example.demospring.model.BookDTO;
import com.example.demospring.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService{
    @Autowired
    IBookRepository bookRepository;
    @Autowired
    IBorrowBookService borrowBookService;

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void save(Book book) {
bookRepository.save(book);
    }

    @Override
    public void remove(Long id) {
bookRepository.deleteById(id);
    }

    @Override
    public Iterable<Book> getListFreeBook() {
        return bookRepository.getListFreeBook();
    }

    @Override
    public Iterable<Book> findAllByStatusIsTrueOrderByIdDesc() {
        return bookRepository.findAllByStatusIsTrueOrderByIdDesc();
    }

    @Override
    public Iterable<Book> findAllByNameContainingAndStatusIsTrueOrderByIdDesc(String name) {
        return bookRepository.findAllByNameContainingAndStatusIsTrueOrderByIdDesc(name);
    }

    @Override
    public Iterable<Book> getListBookInBorrow() {
        return bookRepository.getListBookInBorrow();
    }

    @Override
    public Boolean checkBookInBorrow(Book book) {
        List<Book> bookList = (List<Book>) getListBookInBorrow();
        if (bookList.contains(book)){
            return true;
        }
        return false;
    }

    @Override
    public Iterable<Book> findAllByNameContaining(String name) {
        return bookRepository.findAllByNameContaining(name);
    }
}
