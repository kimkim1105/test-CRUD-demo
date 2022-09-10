package com.example.demospring.controller;

import com.example.demospring.model.Book;
import com.example.demospring.model.Student;
import com.example.demospring.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    IBookService bookService;
    @GetMapping
    public String getBookList(Model model, @RequestParam(required = false, name = "search") String search){
        try {
            if (!search.isEmpty()&&search!=null){
                model.addAttribute("books", bookService.findAllByNameContainingAndStatusIsTrue(search));
                return "book/bookList";
            }else {
                model.addAttribute("books", bookService.findAllByStatusIsTrue());
                return "book/bookList";
            }
        }catch (Exception e){
            model.addAttribute("books", bookService.findAllByStatusIsTrue());
            return "book/bookList";
        }
    }
    @GetMapping("/bookFree")
    public String getBookListFree(Model model, @RequestParam(required = false, name = "search") String search){
        try {
            if (!search.isEmpty()&&search!=null){
                model.addAttribute("books", bookService.findAllByNameContainingAndStatusIsTrue(search));
                return "book/bookcheck";
            }else {
                model.addAttribute("books", bookService.getListFreeBook());
                return "book/bookcheck";
            }
        }catch (Exception e){
            model.addAttribute("books", bookService.getListFreeBook());
            return "book/bookcheck";
        }
    }
    @GetMapping("/bookBorrowed")
    public String getBookListBorowed(Model model, @RequestParam(required = false, name = "search") String search){
        try {
            if (!search.isEmpty()&&search!=null){
                model.addAttribute("books", bookService.findAllByNameContainingAndStatusIsTrue(search));
                return "book/bookcheck";
            }else {
                model.addAttribute("books", bookService.getListFreeBook());
                return "book/bookcheck";
            }
        }catch (Exception e){
            model.addAttribute("books", bookService.getListFreeBook());
            return "book/bookcheck";
        }
    }
    @PostMapping("/addBook")
    public String addBook(@ModelAttribute(name = "book") Book book, Model model){
        if (book.getName()!=null&&!book.getName().isEmpty()){
            book.setStatus(true);
            bookService.save(book);
            model.addAttribute("books", bookService.findAllByStatusIsTrue());
            return "book/bookList";
        }else {
            model.addAttribute("mess","Name not valid");
            return "book/addBook";
        }
    }
    @GetMapping("/addBook")
    public String addBookForm(Model model){
        Book book = new Book();
        model.addAttribute("book", book);
        return "book/addBook";
    }
    @GetMapping("/updateBook/{id}")
    public String updateBookForm(Model model, @PathVariable Long id){
        Book book = bookService.findById(id).get();
        model.addAttribute("book", book);
        return "book/updateBook";
    }
    @PostMapping("/updateBook/{id}")
    public String updateBook(Model model, @ModelAttribute Book book){
        List<Book> bookList = (List<Book>) bookService.getListBookInBorrow();
        if (bookList.contains(bookService.findById(book.getId()).get())){
            Book book1 = bookService.findById(book.getId()).get();
            model.addAttribute("book", book1);
            model.addAttribute("mess","Book is in borrowing, can't edit");
            return "book/updateBook";
        }
        if (book!=null&&!book.getName().isEmpty()){
            book.setStatus(bookService.findById(book.getId()).get().isStatus());
            bookService.save(book);
            model.addAttribute("books", bookService.findAllByStatusIsTrue());
            return "book/bookList";
        }else {
            model.addAttribute("mess","Name not valid");
            return "book/updateBook";
        }
    }
    @GetMapping("/deleteBook/{id}")
    public String deleteBookForm(Model model, @PathVariable Long id){
        Book book = bookService.findById(id).get();
        model.addAttribute("book", book);
        return "book/deleteBook";
    }
    @PostMapping("/deleteBook/{id}")
    public String deleteBook( @PathVariable Long id, Model model){
        Book book = bookService.findById(id).get();
        List<Book> bookList = (List<Book>) bookService.getListBookInBorrow();
        if (bookList.contains(bookService.findById(book.getId()).get())){
            model.addAttribute("book", book);
            model.addAttribute("mess","Book is in borrowing, can't edit");
            return "book/deleteBook";
        }
        book.setStatus(!book.isStatus());
        bookService.save(book);
//        bookService.findById(id).get().setStatus(!bookService.findById(id).get().isStatus());
        model.addAttribute("books", bookService.findAllByStatusIsTrue());
        return "book/bookList";
    }
}
