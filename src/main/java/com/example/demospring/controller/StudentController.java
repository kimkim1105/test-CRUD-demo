package com.example.demospring.controller;

import com.example.demospring.model.Book;
import com.example.demospring.model.Student;
import com.example.demospring.service.IStudentService;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    IStudentService studentService;

    @GetMapping
    public String getStudentList(Model model, @RequestParam(required = false, name = "search") String search) {
        Student student = new Student();
        model.addAttribute("student", student);
        try {
            if (!search.isEmpty() && search != null) {
                model.addAttribute("students", studentService.findAllByNameContainingAndStatusIsTrueOrderByIdDesc(search));
                return "student/studentList";
            } else {
                model.addAttribute("students", studentService.findAllByStatusIsTrueOrderByIdDesc());
                return "student/studentList";
            }
        } catch (Exception e) {
            model.addAttribute("students", studentService.findAllByStatusIsTrueOrderByIdDesc());
            return "student/studentList";
        }
    }

    @PostMapping("/addStudent")
    public String addStudent(@ModelAttribute(name = "student") Student student, Model model) {
        if (student != null && !student.getName().isEmpty()) {
            student.setStatus(true);
            studentService.save(student);
            model.addAttribute("students", studentService.findAllByStatusIsTrueOrderByIdDesc());
            return "student/studentList";
        } else {
            model.addAttribute("mess", "Name not valid");
            return "student/studentList";
        }
    }

    @RequestMapping("/listStudentInBorrow")
    @ResponseBody
    public Boolean getListInBorrow(Long id) {
        List<Student> studentList = (List<Student>) studentService.getListStudentInBorrow();
        if (studentList.contains(studentService.findById(id ).get())) {
            return true;
        }
        return false;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Optional<Student> updateStudentForm(Long id, Model model) {
        Optional<Student> student = studentService.findById(id);
        return student;
    }

    @RequestMapping(value = "/updateStudent", method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateStudent(Model model, @ModelAttribute Student student) {
        if (student != null && !student.getName().isEmpty()) {
            student.setStatus(studentService.findById(student.getId()).get().isStatus());
            studentService.save(student);
            model.addAttribute("students", studentService.findAllByStatusIsTrueOrderByIdDesc());
            return "redirect:/students";
        } else {
            model.addAttribute("mess", "Name not valid");
            return "redirect:/students";
        }
    }

    @RequestMapping(value = "/deleteStudent", method = {RequestMethod.PUT, RequestMethod.GET})
    public String deleteStudent(@ModelAttribute Student student, Model model) {
        Student student1 = studentService.findById(student.getId()).get();
        student1.setStatus(false);
        studentService.save(student1);
        model.addAttribute("students", studentService.findAllByStatusIsTrueOrderByIdDesc());
        return "redirect:/students";
    }

}
