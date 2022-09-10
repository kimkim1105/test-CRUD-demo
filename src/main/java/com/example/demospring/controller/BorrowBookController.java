package com.example.demospring.controller;

import com.example.demospring.model.Book;
import com.example.demospring.model.BorrowBook;
import com.example.demospring.model.Student;
import com.example.demospring.service.IBookService;
import com.example.demospring.service.IBorrowBookService;
import com.example.demospring.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/borrowbooks")
public class BorrowBookController {
    @Autowired
    IBorrowBookService borrowBookService;
    @Autowired
    IBookService bookService;
    @Autowired
    IStudentService studentService;
    @GetMapping
    public String getListBorrowBook(Model model){
        model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrue());
        return "borrow/borrowList";
    }
    @GetMapping("/addOrder")
    public String addOrderForm(Model model){
        BorrowBook order = new BorrowBook();
        model.addAttribute("order", order);
//        model.addAttribute("books", bookService.findAll());
//        model.addAttribute("students", studentService.findAll());
        return "borrow/addOrder";
    }
    @PostMapping("/addOrder")
    public String addOrder(Model model, @RequestParam String studentId, @RequestParam String bookId){
        List<Book> bookList = (List<Book>) bookService.getListFreeBook();
        List<Student> studentList = (List<Student>) studentService.getListFreeStudent();
        BorrowBook order = new BorrowBook();
        try {
            if (!studentId.isEmpty()&&!bookId.isEmpty()){
            if (bookList.contains(bookService.findById(Long.valueOf(bookId)).get())&&studentList.contains(studentService.findById(Long.valueOf(studentId)).get())){
                order.setStatus(true);
                order.setBook(bookService.findById(Long.valueOf(bookId)).get());
                order.setStudent(studentService.findById(Long.valueOf(studentId)).get());
                order.setDate(LocalDate.now());
                borrowBookService.save(order);
                model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrue());
                return "borrow/borrowList";
            }else {
                model.addAttribute("mess","Book Id or Student Id was borrow or not valid");
                return "borrow/addOrder";
            }
        }else {
            model.addAttribute("mess","Book Id and Student Id are nesscessary");
            return "borrow/addOrder";
        }
        }
        catch (Exception e){
            model.addAttribute("mess","Book Id and Student Id are nesscessary");
            return "borrow/addOrder";
        }
    }
    @GetMapping("/deleteOrder/{id}")
    public String deleteOrderForm(Model model, @PathVariable Long id){
        BorrowBook order = borrowBookService.findById(id).get();
        model.addAttribute("order", order);
        return "borrow/deleteOrder";
    }
    @PostMapping("/deleteOrder/{id}")
    public String deleteOrder( @PathVariable Long id, Model model, @ModelAttribute BorrowBook order){
        BorrowBook order1 = borrowBookService.findById(id).get();
        List<BorrowBook> orderList = (List<BorrowBook>) borrowBookService.getOrderCompleted();
        if (orderList.contains(borrowBookService.findById(order1.getId()).get())){
            model.addAttribute("order", order1);
            model.addAttribute("mess","Order is completed, can't edit");
            return "borrow/deleteOrder";
        }
//        order.setStudent(order1.getStudent());
//        order.setBook(order1.getBook());
        order1.setStatus(!order1.isStatus());
        order1.setNote(order.getNote());
        borrowBookService.save(order1);
        model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrue());
        return "borrow/borrowList";
    }

}
