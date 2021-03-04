package authorbookspring.demo.controller;

import authorbookspring.demo.model.Author;
import authorbookspring.demo.model.Book;
import authorbookspring.demo.repository.AuthorRepository;
import authorbookspring.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookContrcoler {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @GetMapping("/book/save")
    public String saveAuthor(@RequestParam(name = "id", required = false) Integer id, ModelMap modelMap) {

        if (id != null) {
            modelMap.addAttribute("books", bookRepository.getOne(id));
        }else{
            modelMap.addAttribute("books", new Book());
        }
        modelMap.addAttribute("author", authorRepository.findAll());
        return "editBook";

    }

    @GetMapping("/book/delete")
    public String deleteTask(@RequestParam("id") int id) {
        bookRepository.deleteById(id);
        return "redirect:/";
    }
        @GetMapping("/book")
        public String homePage(Model modelMap) {
            List<Book> all = bookRepository.findAll();
            modelMap.addAttribute("books", all);
            return "allBook";
        }

    @PostMapping("/book/savee")
    public String addAuthor(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/";
    }

}
