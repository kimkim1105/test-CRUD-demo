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
    @Query(value = "select * from students where status = true and id not in (select student_id from borrowbooks where date between (select adddate(curdate(),-3)) and curdate())",nativeQuery = true)
    Iterable<Student> getListFreeStudent();
    @Query(value = "select * from students where status = true and id in (select student_id from borrowbooks where date between (select adddate(curdate(),-3)) and curdate())", nativeQuery = true)
    Iterable<Student> getListStudentInBorrow();
    Iterable<Student> findAllByStatusIsTrue();
    Iterable<Student> findAllByNameContainingAndStatusIsTrue(String name);

}
