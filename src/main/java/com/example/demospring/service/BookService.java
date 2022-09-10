package com.example.demospring.service;

import com.example.demospring.model.Book;
import com.example.demospring.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService implements IBookService{
    @Autowired
    IBookRepository bookRepository;
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
    public Iterable<Book> findAllByStatusIsTrue() {
        return bookRepository.findAllByStatusIsTrue();
    }

    @Override
    public Iterable<Book> findAllByNameContainingAndStatusIsTrue(String name) {
        return bookRepository.findAllByNameContainingAndStatusIsTrue(name);
    }

    @Override
    public Iterable<Book> getListBookInBorrow() {
        return bookRepository.getListBookInBorrow();
    }
}
