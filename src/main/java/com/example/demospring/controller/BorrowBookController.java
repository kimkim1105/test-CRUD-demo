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

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        BorrowBook order = new BorrowBook();
        model.addAttribute("order", order);
        model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrueOrderByDateDesc());
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
                model.addAttribute("mess","Add new order success");
                model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrueOrderByDateDesc());
                return "borrow/borrowList";
            }else {
                model.addAttribute("mess","Book Id or Student Id was borrow or not valid");
                model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrueOrderByDateDesc());
                return "borrow/borrowList";
            }
        }else {
            model.addAttribute("mess","Book Id and Student Id are nesscessary");
                model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrueOrderByDateDesc());
                return "borrow/borrowList";
        }
        }
        catch (Exception e){
            model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrueOrderByDateDesc());
            model.addAttribute("mess","Book Id and Student Id are nesscessary");
            return "borrow/borrowList";
        }
    }
    @RequestMapping("/findById")
    @ResponseBody
    public Optional<BorrowBook> updateBorrowForm(Long id) {
        Optional<BorrowBook> order = borrowBookService.findById(id);
        return order;
    }
    @RequestMapping("/listOrderCompleted")
    @ResponseBody
    public Boolean getOrderCompleted(Long id) {
        List<BorrowBook> orderList = (List<BorrowBook>) borrowBookService.getOrderCompleted();
        if (orderList.contains(borrowBookService.findById(id).get())){
            return true;
        }
        return false;
    }
    @RequestMapping(value = "/deleteOrder", method = {RequestMethod.PUT, RequestMethod.GET})
//    @PostMapping("/deleteOrder")
    public String deleteOrder(Model model, @ModelAttribute BorrowBook order){
        BorrowBook order1 = borrowBookService.findById(order.getId()).get();
        order1.setStatus(false);
        order1.setNote(order.getNote());
        borrowBookService.save(order1);
        model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrueOrderByDateDesc());
        return "redirect:/borrowbooks";
    }

}
