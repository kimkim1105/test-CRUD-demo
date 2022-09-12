package com.example.demospring.service;

import com.example.demospring.model.BorrowBook;
import com.example.demospring.repository.IBorrowBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public BorrowBook save(BorrowBook borrowBook) {
 return borrowBookRepository.save(borrowBook);
    }

    @Override
    public void remove(Long id) {
borrowBookRepository.deleteById(id);
    }

    @Override
    public Page<BorrowBook> findAllByStatusIsTrueOrderByDateDesc(Pageable pageable) {
        return borrowBookRepository.findAllByStatusIsTrueOrderByDateDesc(pageable);
    }

    @Override
    public Iterable<BorrowBook> getOrderCompleted() {
        return borrowBookRepository.getOrderCompleted();
    }

    @Override
    public Iterable<BorrowBook> getOrderUnCompleted() {
        return borrowBookRepository.getOrderUnCompleted();
    }

    @Override
    public Page<BorrowBook> searchByBookOrStudent(String search, Pageable pageable) {
        return borrowBookRepository.searchByBookOrStudent(search, pageable);
    }

    @Override
    public Integer countOrderByDateBorrow(LocalDate localDate) {
        return borrowBookRepository.countOrderByDateBorrow(localDate);
    }
}
