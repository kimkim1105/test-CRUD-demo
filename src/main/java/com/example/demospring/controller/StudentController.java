package com.example.demospring.controller;

import com.example.demospring.model.Book;
import com.example.demospring.model.Student;
import com.example.demospring.service.IStudentService;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getStudentList(Model model, @RequestParam(required = false, name = "search") String search){
        Student student = new Student();
        model.addAttribute("student", student);
        try {
            if (!search.isEmpty()&&search!=null){
                model.addAttribute("students", studentService.findAllByNameContainingAndStatusIsTrue(search));
                return "student/studentList";
            }else {
                model.addAttribute("students", studentService.findAllByStatusIsTrue());
                return "student/studentList";
            }
        }catch (Exception e){
            model.addAttribute("students", studentService.findAllByStatusIsTrue());
            return "student/studentList";
        }
    }
    @PostMapping("/addStudent")
    public String addStudent(@ModelAttribute(name = "student") Student student, Model model){
        if (student!=null&&!student.getName().isEmpty()){
            student.setStatus(true);
            studentService.save(student);
            model.addAttribute("students", studentService.findAllByStatusIsTrue());
            return "student/studentList";
        }else {
            model.addAttribute("mess","Name not valid");
            return "student/studentList";
        }
    }
    @GetMapping("/addStudent")
    public String addStudentForm(Model model){
        Student student = new Student();
        model.addAttribute("notice","Create Success");
        model.addAttribute("student", student);
        return "student/addStudent";
    }
    @RequestMapping("/findById")
    @ResponseBody
    public Optional<Student> updateStudentForm(Long id,Model model){
        Optional<Student> student = studentService.findById(id);
        List<Student> studentList = (List<Student>) studentService.getListStudentInBorrow();
        if (studentList.contains(studentService.findById(student.get().getId()).get())){
            model.addAttribute("mess","Student is in borrowing, can't edit");
        }
        return student;
    }
    @RequestMapping(value = "/updateStudent", method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateStudent(Model model, @ModelAttribute Student student){
        if (student!=null&&!student.getName().isEmpty()){
            student.setStatus(studentService.findById(student.getId()).get().isStatus());
            studentService.save(student);
            model.addAttribute("students", studentService.findAllByStatusIsTrue());
            return "student/studentList";
        }else {
            model.addAttribute("mess","Name not valid");
            return "student/studentList";
        }
    }
    @GetMapping("/deleteStudent/{id}")
    public String deleteStudentForm(Model model, @PathVariable Long id){
        Student student = studentService.findById(id).get();
        model.addAttribute("student", student);
        return "student/deleteStudent";
    }
    @PostMapping("/deleteStudent/{id}")
    public String deleteStudent( @PathVariable Long id, Model model){
        Student student = studentService.findById(id).get();
        List<Student> studentList = (List<Student>) studentService.getListStudentInBorrow();
        if (studentList.contains(studentService.findById(student.getId()).get())){
            model.addAttribute("student", student);
            model.addAttribute("mess","Student is in borrowing, can't edit");
            return "student/deleteStudent";
        }
        student.setStatus(!student.isStatus());
        studentService.save(student);
        model.addAttribute("students", studentService.findAllByStatusIsTrue());
        return "student/studentList";
    }

}
