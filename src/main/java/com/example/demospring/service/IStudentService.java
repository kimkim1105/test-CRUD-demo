package com.example.demospring.service;

import com.example.demospring.model.Book;
import com.example.demospring.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudentService extends IGeneralService<Student> {
    Iterable<Student> getListFreeStudent();
    Iterable<Student> getListStudentInBorrow();
    Page<Student> findAllByStatusIsTrueOrderByIdDesc(Pageable pageable);
    Page<Student> findAllByNameContainingAndStatusIsTrueOrderByIdDesc(String name, Pageable pageable);
    Boolean checkStudentInBorrow(Student student);
}
