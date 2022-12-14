package com.example.demospring.controller;

import com.example.demospring.model.*;
import com.example.demospring.service.IBookService;
import com.example.demospring.service.IBorrowBookService;
import com.example.demospring.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

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
    public String getListBorrowBook(Model model, @RequestParam(required = false, name = "search") String search,@PageableDefault(value = 5) Pageable pageable ){
        BorrowBook order = new BorrowBook();
        model.addAttribute("order", order);
        try {
            if (!search.isEmpty()&&search!=null){
                model.addAttribute("borrowbooks", borrowBookService.searchByBookOrStudent('%'+search+'%', pageable));
                return "borrow/borrowList";
            }else {
                model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrueOrderByDateDesc(pageable));
                return "borrow/borrowList";
            }
        }catch (Exception e){
            model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrueOrderByDateDesc(pageable));
            return "borrow/borrowList";
        }
    }
//    @GetMapping("/quicksearch")
//    public String getListQuickSearchBorrow(Model model, @RequestParam String quick){
//        if (quick.equalsIgnoreCase("completed")){
//            model.addAttribute("borrowbooks", borrowBookService.getOrderCompleted());
//        }else if (quick.equalsIgnoreCase("uncompleted")){
//            model.addAttribute("borrowbooks", borrowBookService.getOrderUnCompleted());
//        }else {
//            model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrueOrderByDateDesc());
//        }
//        return "borrow/borrowList";
//    }

//    @GetMapping("/bookFree")
//    public String getBookListFree(Model model, @RequestParam(required = false, name = "search") String search,@PageableDefault(value = 5) Pageable pageable){
//        bookDTOList = new ArrayList<>();
//        try {
//            if (!search.isEmpty()&&search!=null){
//                Iterator<Book> bookIterator = bookService.findAllByNameContainingAndStatusIsTrueOrderByIdDesc(search,pageable).iterator();
//                while (bookIterator.hasNext()){
//                    Book book = bookIterator.next();
//                    if (bookService.checkBookInBorrow(book)){
//                        BookDTO bookDTO = new BookDTO(book.getId(), book.getName(), "in borrowing");
//                        bookDTOList.add(bookDTO);
//                    }else {
//                        BookDTO bookDTO = new BookDTO(book.getId(), book.getName(), "in free");
//                        bookDTOList.add(bookDTO);
//                    }
//                }
//                model.addAttribute("books", bookDTOList);
//                return "borrow/book";
//            }else {
//                Iterator<Book> bookIterator = bookService.findAllByStatusIsTrueOrderByIdDesc(pageable).iterator();
//                while (bookIterator.hasNext()){
//                    Book book = bookIterator.next();
//                    if (bookService.checkBookInBorrow(book)){
//                        BookDTO bookDTO = new BookDTO(book.getId(), book.getName(), "in borrowing");
//                        bookDTOList.add(bookDTO);
//                    }else {
//                        BookDTO bookDTO = new BookDTO(book.getId(), book.getName(), "in free");
//                        bookDTOList.add(bookDTO);
//                    }
//                }
//                model.addAttribute("books", bookDTOList);
//                return "borrow/book";
//            }
//        }catch (Exception e){
//            Iterator<Book> bookIterator = bookService.findAllByStatusIsTrueOrderByIdDesc(pageable).iterator();
//            while (bookIterator.hasNext()){
//                Book book = bookIterator.next();
//                if (bookService.checkBookInBorrow(book)){
//                    BookDTO bookDTO = new BookDTO(book.getId(), book.getName(), "in borrowing");
//                    bookDTOList.add(bookDTO);
//                }else {
//                    BookDTO bookDTO = new BookDTO(book.getId(), book.getName(), "in free");
//                    bookDTOList.add(bookDTO);
//                }
//            }
//            model.addAttribute("books", bookDTOList);
//            return "borrow/book";
//        }
//    }
    @GetMapping("/bookFree")
    public String getBookListFree(Model model, @RequestParam(required = false, name = "search") String search,@PageableDefault(value = 5) Pageable pageable){
        try {
            if (!search.isEmpty()&&search!=null){
                model.addAttribute("books", bookService.findAllByNameContainingAndStatusIsTrueOrderByIdDesc(search,pageable));
                return "borrow/book";
            }else {
                model.addAttribute("books", bookService.findAllByStatusIsTrueOrderByIdDesc(pageable));
                return "borrow/book";
            }
        }catch (Exception e){
            model.addAttribute("books", bookService.findAllByStatusIsTrueOrderByIdDesc(pageable));
            return "borrow/book";
        }
    }
//    @GetMapping("/student")
//    public String getStudentListFree(Model model, @RequestParam(required = false, name = "search") String search){
//        studentDTOList = new ArrayList<>();
//        try {
//            if (!search.isEmpty()&&search!=null){
//                Iterator<Student> studentIterator = studentService.findAllByNameContainingAndStatusIsTrueOrderByIdDesc(search).iterator();
//                while (studentIterator.hasNext()){
//                    Student student = studentIterator.next();
//                    if (studentService.checkStudentInBorrow(student)){
//                        StudentDTO studentDTO = new StudentDTO(student.getId(), student.getName(), "in borrowing");
//                        studentDTOList.add(studentDTO);
//                    }else {
//                        StudentDTO studentDTO = new StudentDTO(student.getId(), student.getName(), "in free");
//                        studentDTOList.add(studentDTO);
//                    }
//                }
//                model.addAttribute("students", studentDTOList);
//                return "borrow/student";
//            }else {
//                Iterator<Student> studentIterator = studentService.findAllByStatusIsTrueOrderByIdDesc().iterator();
//                while (studentIterator.hasNext()){
//                    Student student = studentIterator.next();
//                    if (studentService.checkStudentInBorrow(student)){
//                        StudentDTO studentDTO = new StudentDTO(student.getId(), student.getName(), "in borrowing");
//                        studentDTOList.add(studentDTO);
//                    }else {
//                        StudentDTO studentDTO = new StudentDTO(student.getId(), student.getName(), "in free");
//                        studentDTOList.add(studentDTO);
//                    }
//                }
//                model.addAttribute("students", studentDTOList);
//                return "borrow/student";
//            }
//        }catch (Exception e){
//            Iterator<Student> studentIterator = studentService.findAllByStatusIsTrueOrderByIdDesc().iterator();
//            while (studentIterator.hasNext()){
//                Student student = studentIterator.next();
//                if (studentService.checkStudentInBorrow(student)){
//                    StudentDTO studentDTO = new StudentDTO(student.getId(), student.getName(), "in borrowing");
//                    studentDTOList.add(studentDTO);
//                }else {
//                    StudentDTO studentDTO = new StudentDTO(student.getId(), student.getName(), "in free");
//                    studentDTOList.add(studentDTO);
//                }
//            }
//            model.addAttribute("students", studentDTOList);
//            return "borrow/student";
//        }
//    }
    @GetMapping("/statistics")
    public String getStatistics(Model model){
        List<Book> bookList = (List<Book>) bookService.getListFreeBook();
        List<Student> studentList = (List<Student>) studentService.getListFreeStudent();
        List<Book> bookList1 = (List<Book>) bookService.getListBookInBorrow();
        List<Student> studentList1 = (List<Student>) studentService.getListStudentInBorrow();
        model.addAttribute("countBookOfFree", bookList.size());
        model.addAttribute("countBookOfBorrow", bookList1.size());
        model.addAttribute("countStudentOfFree", studentList.size());
        model.addAttribute("countStudentOfBorrow", studentList1.size());
        Map<LocalDate, Integer> data = new LinkedHashMap<LocalDate, Integer>();
        data.put(LocalDate.now().minusDays(6), borrowBookService.countOrderByDateBorrow(LocalDate.now().minusDays(6)));
        data.put(LocalDate.now().minusDays(5), borrowBookService.countOrderByDateBorrow(LocalDate.now().minusDays(5)));
        data.put(LocalDate.now().minusDays(4), borrowBookService.countOrderByDateBorrow(LocalDate.now().minusDays(4)));
        data.put(LocalDate.now().minusDays(3), borrowBookService.countOrderByDateBorrow(LocalDate.now().minusDays(3)));
        data.put(LocalDate.now().minusDays(2), borrowBookService.countOrderByDateBorrow(LocalDate.now().minusDays(2)));
        data.put(LocalDate.now().minusDays(1), borrowBookService.countOrderByDateBorrow(LocalDate.now().minusDays(1)));
        data.put(LocalDate.now(), borrowBookService.countOrderByDateBorrow(LocalDate.now()));
        model.addAttribute("keySet", data.keySet());
        System.out.println("in ra keySet"+data.keySet());
        model.addAttribute("values", data.values());
        return "borrow/statistics";
    }
    @GetMapping("/student")
    public String getStudentListFree(Model model, @RequestParam(required = false, name = "search") String search,@PageableDefault(value = 5) Pageable pageable ){
        try {
            if (!search.isEmpty()&&search!=null){
                model.addAttribute("students", studentService.findAllByNameContainingAndStatusIsTrueOrderByIdDesc(search,pageable));
                return "borrow/student";
            }else {
                model.addAttribute("students", studentService.findAllByStatusIsTrueOrderByIdDesc(pageable));
                return "borrow/student";
            }
        }catch (Exception e){
            model.addAttribute("students", studentService.findAllByStatusIsTrueOrderByIdDesc(pageable));
            return "borrow/student";
        }
    }

    @GetMapping("/checkOrderInput")
    @ResponseBody
    public Boolean getFree(String bookId, String studentId){
        List<Book> bookList = (List<Book>) bookService.getListFreeBook();
        List<Student> studentList = (List<Student>) studentService.getListFreeStudent();
        try {
            if (bookList.contains(bookService.findById(Long.valueOf(bookId)).get())&&studentList.contains(studentService.findById(Long.valueOf(studentId)).get())){
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }

    @PostMapping("/addOrder")
    public String addOrder(Model model, @RequestParam String studentId, @RequestParam String bookId,@PageableDefault(value = 5) Pageable pageable ){
        List<Book> bookList = (List<Book>) bookService.getListFreeBook();
        List<Student> studentList = (List<Student>) studentService.getListFreeStudent();
        BorrowBook order = new BorrowBook();
//        model.addAttribute("order", order);
        try {
            if (!studentId.isEmpty()&&!bookId.isEmpty()){
            if (bookList.contains(bookService.findById(Long.valueOf(bookId)).get())&&studentList.contains(studentService.findById(Long.valueOf(studentId)).get())){
                Book book = bookService.findById(Long.valueOf(bookId)).get();
                book.setActive("borrowing");
                bookService.save(book);
                Student student = studentService.findById(Long.valueOf(studentId)).get();
                student.setActive("borrowing");
                studentService.save(student);
                order.setStatus(true);
                order.setBook(bookService.findById(Long.valueOf(bookId)).get());
                order.setStudent(studentService.findById(Long.valueOf(studentId)).get());
                order.setDate(LocalDate.now());
                order.setType("processing");
                borrowBookService.save(order);
                model.addAttribute("mess","Add new order success");
                model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrueOrderByDateDesc(pageable));
                return "redirect:/borrowbooks";
            }else {
                model.addAttribute("mess","Book Id or Student Id was borrow or not valid");
                model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrueOrderByDateDesc(pageable));
                return "redirect:/borrowbooks";
            }
        }else {
            model.addAttribute("mess","Book Id and Student Id are nesscessary");
                model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrueOrderByDateDesc(pageable));
                return "redirect:/borrowbooks";
        }
        }
        catch (Exception e){
            model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrueOrderByDateDesc(pageable));
            model.addAttribute("mess","Book Id and Student Id are nesscessary");
            return "redirect:/borrowbooks";
        }
    }

//    @PostMapping("/addOrder")
//    public ResponseEntity<?> addOrder(Model model, @RequestParam String studentId, @RequestParam String bookId, @PageableDefault(value = 5) Pageable pageable ){
//        List<Book> bookList = (List<Book>) bookService.getListFreeBook();
//        List<Student> studentList = (List<Student>) studentService.getListFreeStudent();
//        BorrowBook order = new BorrowBook();
////        model.addAttribute("order", order);
//        try {
//            if (!studentId.isEmpty()&&!bookId.isEmpty()){
//                if (bookList.contains(bookService.findById(Long.valueOf(bookId)).get())&&studentList.contains(studentService.findById(Long.valueOf(studentId)).get())){
//                    Book book = bookService.findById(Long.valueOf(bookId)).get();
//                    book.setActive("borrowing");
//                    bookService.save(book);
//                    Student student = studentService.findById(Long.valueOf(studentId)).get();
//                    student.setActive("borrowing");
//                    studentService.save(student);
//                    order.setStatus(true);
//                    order.setBook(bookService.findById(Long.valueOf(bookId)).get());
//                    order.setStudent(studentService.findById(Long.valueOf(studentId)).get());
//                    order.setDate(LocalDate.now());
//                    order.setType("processing");
//                    borrowBookService.save(order);
//                    model.addAttribute("addmess","Add new order success");
//                    model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrueOrderByDateDesc(pageable));
//                    return new  ResponseEntity<>("ok", HttpStatus.OK);
//                }else {
//                    model.addAttribute("addmess","Book Id or Student Id was borrow or not valid");
//                    model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrueOrderByDateDesc(pageable));
//                    return new  ResponseEntity<>("invalid", HttpStatus.OK);
//                }
//            }else {
//                model.addAttribute("addmess","Book Id and Student Id are nesscessary");
//                model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrueOrderByDateDesc(pageable));
//                return new  ResponseEntity<>("lack", HttpStatus.OK);
//            }
//        }
//        catch (Exception e){
//            model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrueOrderByDateDesc(pageable));
//            model.addAttribute("addmess","Book Id and Student Id are nesscessary");
//            return new  ResponseEntity<>("lack", HttpStatus.OK);
//        }
//    }

    @RequestMapping(value = "/returnOrder", method = {RequestMethod.PUT, RequestMethod.GET})
    public String returnOrder(Model model, @ModelAttribute BorrowBook order,@PageableDefault(value = 5) Pageable pageable ) {
//
        BorrowBook order1 = borrowBookService.findById(order.getId()).get();
        Book book = bookService.findById(order1.getBook().getId()).get();
            book.setActive("free");
            bookService.save(book);
            Student student = studentService.findById(Long.valueOf(order1.getStudent().getId())).get();
            student.setActive("free");
            studentService.save(student);
            order1.setStatus(order1.isStatus());
            order1.setType("completed");
            order1.setReturnDate(LocalDate.now());
            borrowBookService.save(order1);
            model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrueOrderByDateDesc(pageable));
            return "redirect:/borrowbooks";
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
    public String deleteOrder(Model model, @ModelAttribute BorrowBook order,@PageableDefault(value = 5) Pageable pageable ){
        if (order.getNote()!= null&&!order.getNote().isEmpty()){
            BorrowBook order1 = borrowBookService.findById(order.getId()).get();
            Book book = bookService.findById(order1.getBook().getId()).get();
            book.setActive("free");
            bookService.save(book);
            Student student = studentService.findById(Long.valueOf(order1.getStudent().getId())).get();
            student.setActive("free");
            studentService.save(student);
            order1.setStatus(false);
            order1.setType("nousing");
            order1.setNote(order.getNote());
            borrowBookService.save(order1);
            model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrueOrderByDateDesc(pageable));
            return "redirect:/borrowbooks";
        }else {
            model.addAttribute("borrowbooks", borrowBookService.findAllByStatusIsTrueOrderByDateDesc(pageable));
            model.addAttribute("mess", "Note not valid");
            return "redirect:/borrowbooks";
        }
    }

}
