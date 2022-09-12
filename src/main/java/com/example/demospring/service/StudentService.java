package com.example.demospring.service;

import com.example.demospring.model.Book;
import com.example.demospring.model.Student;
import com.example.demospring.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService{
    @Autowired
    IStudentRepository studentRepository;
    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void save(Student student) {
studentRepository.save(student);
    }

    @Override
    public void remove(Long id) {
studentRepository.deleteById(id);
    }

    @Override
    public Iterable<Student> getListFreeStudent() {
        return studentRepository.getListFreeStudent();
    }

    @Override
    public Iterable<Student> getListStudentInBorrow() {
        return studentRepository.getListStudentInBorrow();
    }

    @Override
    public Iterable<Student> findAllByStatusIsTrueOrderByIdDesc() {
        return studentRepository.findAllByStatusIsTrueOrderByIdDesc();
    }

    @Override
    public Iterable<Student> findAllByNameContainingAndStatusIsTrueOrderByIdDesc(String name) {
        return studentRepository.findAllByNameContainingAndStatusIsTrueOrderByIdDesc(name);
    }

    @Override
    public Boolean checkStudentInBorrow(Student student) {
        List<Student> studentList = (List<Student>) getListStudentInBorrow();
        if (studentList.contains(student)){
            return true;
        }
        return false;
    }

}
