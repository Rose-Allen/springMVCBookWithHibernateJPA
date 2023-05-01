package ru.dimash.spring.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.dimash.spring.dao.BookDAO;
import ru.dimash.spring.dao.PersonDAO;
import ru.dimash.spring.models.Book;
import ru.dimash.spring.models.Person;
import ru.dimash.spring.services.BooksService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {


    private final BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
//        this.bookDAO = bookDAO;
//        this.personDAO = personDAO;
        this.booksService = booksService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", booksService.findAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", booksService.findOne(id));


        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "books/create";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/create";
        }
        booksService.save(book);
        return "redirect:/books";

    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findOne(id));
        return "books/edit";

    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

//    @PatchMapping("/{id}/release")
//    public String release(@PathVariable("id") int id) {
//        bookDAO.release(id);
//        return "redirect:/books/" + id;
//    }
//
//    @PatchMapping("/{id}/assign")
//    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
//        bookDAO.assign(id, selectedPerson);
//        return "redirect:/books/" + id;
//    }


}
