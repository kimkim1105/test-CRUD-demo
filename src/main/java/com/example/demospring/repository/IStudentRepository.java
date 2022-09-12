package com.example.demospring.repository;

import com.example.demospring.model.Book;
import com.example.demospring.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "select * from students where status = true and active = 'free'",nativeQuery = true)
    Iterable<Student> getListFreeStudent();
    @Query(value = "select * from students where status = true and active = 'borrowing'", nativeQuery = true)
    Iterable<Student> getListStudentInBorrow();
    Page<Student> findAllByStatusIsTrueOrderByIdDesc(Pageable pageable);
    Page<Student> findAllByNameContainingAndStatusIsTrueOrderByIdDesc(String name, Pageable pageable);

}
