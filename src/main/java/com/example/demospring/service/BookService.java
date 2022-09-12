package com.example.demospring.service;

import com.example.demospring.model.Book;
import com.example.demospring.model.BookDTO;
import com.example.demospring.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Book save(Book book) {
return  bookRepository.save(book);
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
    public Page<Book> findAllByStatusIsTrueOrderByIdDesc(Pageable pageable) {
        return bookRepository.findAllByStatusIsTrueOrderByIdDesc(pageable);
    }

    @Override
    public Page<Book> findAllByNameContainingAndStatusIsTrueOrderByIdDesc(String name,Pageable pageable) {
        return bookRepository.findAllByNameContainingAndStatusIsTrueOrderByIdDesc(name,pageable);
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
