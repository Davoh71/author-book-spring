package authorbookspring.demo.controller;

import authorbookspring.demo.model.Author;
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
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @GetMapping("/author/save")
    public String saveAuthor(@RequestParam(name = "id", required = false) Integer id, ModelMap modelMap) {

        if (id != null) {
            modelMap.addAttribute("authors", authorRepository.getOne(id));
        }else{
            modelMap.addAttribute("authors", new Author());
        }
        modelMap.addAttribute("books", bookRepository.findAll());
        return "editAuthor";

    }
    @GetMapping("/author/delete")
    public String deleteTask(@RequestParam("id") int id) {
        authorRepository.deleteById(id);
        return "redirect:/";
    }
    @PostMapping("/author/savee")
    public String addAuthor(@ModelAttribute Author author) {
        authorRepository.save(author);
        return "redirect:/";
    }

    @GetMapping("/author")
    public String homePage(Model modelMap) {
        List<Author> all = authorRepository.findAll();
        modelMap.addAttribute("authors", all);
        return "allAuthor";
    }
}
