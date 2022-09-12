package com.example.demospring.controller;

import com.example.demospring.model.Book;
import com.example.demospring.model.Student;
import com.example.demospring.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    IBookService bookService;
    @GetMapping
    public String getBookList(Model model, @RequestParam(required = false, name = "search") String search){
        Book book = new Book();
        model.addAttribute("book", book);
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
    @RequestMapping("/listBookInBorrow")
    @ResponseBody
    public Boolean getListInBorrow(Long id) {
        List<Book> bookList = (List<Book>) bookService.getListBookInBorrow();
        if (bookList.contains(bookService.findById(id ).get())) {
            return true;
        }
        return false;
    }
    @RequestMapping("/findById")
    @ResponseBody
    public Optional<Book> updateBookForm(Long id, Model model) {
        Optional<Book> book = bookService.findById(id);
        return book;
    }
//    @GetMapping("/updateBook/{id}")
//    public String updateBookForm(Model model, @PathVariable Long id){
//        Book book = bookService.findById(id).get();
//        model.addAttribute("book", book);
//        return "book/updateBook";
//    }
    @RequestMapping(value = "/updateBook", method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateBook(Model model, @ModelAttribute Book book){
        if (book!=null&&!book.getName().isEmpty()){
            book.setStatus(bookService.findById(book.getId()).get().isStatus());
            bookService.save(book);
            model.addAttribute("books", bookService.findAllByStatusIsTrue());
            return "book/bookList";
        }else {
            model.addAttribute("mess","Name not valid");
            return "book/bookList";
        }
    }
    @RequestMapping(value = "/deleteBook", method = {RequestMethod.PUT, RequestMethod.GET})
    public String deleteBook(Model model, @ModelAttribute Book book){
        Book book1 = bookService.findById(book.getId()).get();
        book1.setStatus(false);
        bookService.save(book1);
        model.addAttribute("books", bookService.findAllByStatusIsTrue());
        return "redirect:/books";
    }
}
