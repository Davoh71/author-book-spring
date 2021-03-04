package authorbookspring.demo.controller;

import authorbookspring.demo.model.Author;
import authorbookspring.demo.model.Book;
import authorbookspring.demo.repository.AuthorRepository;
import authorbookspring.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @GetMapping("/")
    public String abaut() {
        return "home";
    }

    @PostMapping("/author/save")
    public String addAuthor(@ModelAttribute Author author) {
        authorRepository.save(author);
        return "redirect:/";
    }

    @PostMapping("/book/save")
    public String addBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/";
    }
}
