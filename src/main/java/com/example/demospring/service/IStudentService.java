package com.example.demospring.service;

import com.example.demospring.model.Book;
import com.example.demospring.model.Student;

public interface IStudentService extends IGeneralService<Student> {
    Iterable<Student> getListFreeStudent();
    Iterable<Student> getListStudentInBorrow();
    Iterable<Student> findAllByStatusIsTrue();
    Iterable<Student> findAllByNameContainingAndStatusIsTrue(String name);
}