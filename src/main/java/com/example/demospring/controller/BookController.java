package com.example.demospring.controller;

import com.example.demospring.model.Book;
import com.example.demospring.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public String getBookList(Model model, @RequestParam(required = false, name = "search") String search,@PageableDefault(value = 5) Pageable pageable){
        Book book = new Book();
        model.addAttribute("book", book);
        try {
            if (!search.isEmpty()&&search!=null){
                model.addAttribute("books", bookService.findAllByNameContainingAndStatusIsTrueOrderByIdDesc(search,pageable));
                model.addAttribute("currentPage", pageable.getPageNumber());
                return "book/bookList";
            }else {
                model.addAttribute("books", bookService.findAllByStatusIsTrueOrderByIdDesc(pageable));
                return "book/bookList";
            }
        }catch (Exception e){
            model.addAttribute("books", bookService.findAllByStatusIsTrueOrderByIdDesc(pageable));
            return "book/bookList";
        }
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute(name = "book") Book book, Model model,@PageableDefault(value = 5) Pageable pageable){
        if (book.getName()!=null&&!book.getName().isEmpty()){
            book.setStatus(true);
            book.setActive("free");
            bookService.save(book);
            model.addAttribute("books", bookService.findAllByStatusIsTrueOrderByIdDesc(pageable));
            return "redirect:/books";
        }else {
            model.addAttribute("mess","Name not valid");
            return "redirect:/books";
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
    public String updateBook(Model model, @ModelAttribute Book book,@PageableDefault(value = 5) Pageable pageable){
        if (book!=null&&!book.getName().isEmpty()){
            book.setStatus(bookService.findById(book.getId()).get().isStatus());
            book.setActive(bookService.findById(book.getId()).get().getActive());
            bookService.save(book);
            model.addAttribute("books", bookService.findAllByStatusIsTrueOrderByIdDesc(pageable));
            return "redirect:/books";
        }else {
            model.addAttribute("mess","Name not valid");
            return "redirect:/books";
        }
    }
    @RequestMapping(value = "/deleteBook", method = {RequestMethod.PUT, RequestMethod.GET})
    public String deleteBook(Model model, @ModelAttribute Book book,@PageableDefault(value = 5) Pageable pageable){
        Book book1 = bookService.findById(book.getId()).get();
        book1.setStatus(false);
        book1.setActive("nousing");
        bookService.save(book1);
        model.addAttribute("books", bookService.findAllByStatusIsTrueOrderByIdDesc(pageable));
        return "redirect:/books";
    }
}
