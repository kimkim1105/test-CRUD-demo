package com.example.demospring.service;

import com.example.demospring.model.BorrowBook;
import com.example.demospring.repository.IBorrowBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BorrowBookService implements IBorrowBookService{
    @Autowired
    IBorrowBookRepository borrowBookRepository;
    @Override
    public Iterable<BorrowBook> findAll() {
        return borrowBookRepository.findAll();
    }

    @Override
    public Optional<BorrowBook> findById(Long id) {
        return borrowBookRepository.findById(id);
    }

    @Override
    public void save(BorrowBook borrowBook) {
borrowBookRepository.save(borrowBook);
    }

    @Override
    public void remove(Long id) {
borrowBookRepository.deleteById(id);
    }

    @Override
    public Iterable<BorrowBook> findAllByStatusIsTrueOrderByDateDesc() {
        return borrowBookRepository.findAllByStatusIsTrueOrderByDateDesc();
    }

    @Override
    public Iterable<BorrowBook> getOrderCompleted() {
        return borrowBookRepository.getOrderCompleted();
    }
}
