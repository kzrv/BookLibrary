package cz.kzrv.library.controllers;

import cz.kzrv.library.models.Book;
import cz.kzrv.library.models.Person;
import cz.kzrv.library.services.BookService;
import cz.kzrv.library.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/books")
@Controller
public class BookController {

    private final BookService bookDao;
    private final PeopleService personDao;

    @Autowired
    public BookController(BookService bookDao, PeopleService personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }
//    ,@RequestParam(value = "page")Integer page, @RequestParam(value = "books_per_page")Integer bookPerPage

    @GetMapping()
    public String index(Model model,@RequestParam(value = "page",required = false)Integer page,
                        @RequestParam(value = "books_per_page",required = false)Integer bookPerPage,
                        @RequestParam(value = "sort_by_year",required = false)boolean sort){
        if(page!=null &&bookPerPage!=null && sort){
            model.addAttribute("books", bookDao.sortAndPage(page,bookPerPage));
        }
        else if(page!=null || bookPerPage!=null){
            model.addAttribute("books", bookDao.indexPage(page,bookPerPage));
        }
        else if (sort){
            model.addAttribute("books",bookDao.sortByYear());
        }
        else model.addAttribute("books",bookDao.index());
        return "/books/index";
    }
    @GetMapping("{id}")
    public String show(Model model, @PathVariable("id") int id, @ModelAttribute("person") Person person){
        model.addAttribute("book", bookDao.show(id));
        Optional<Person> optionalBook = bookDao.getOwner(id);
        if(optionalBook.isPresent()) model.addAttribute("owner",optionalBook.get());
        else model.addAttribute("people",personDao.index());
        return "books/show";
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id")int id){
        bookDao.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newBook(Model model){
        model.addAttribute("book", new Book());
        return "/books/new";
    }
    @PostMapping()
    public String creatBook(@ModelAttribute("book")@Valid Book book, BindingResult result){
        if(result.hasErrors()) return "/books/new";
        bookDao.save(book);
        return "redirect:/books";
    }

    @PatchMapping("{id}/make")
    public String makeOwner(@ModelAttribute("person") Person person,@PathVariable("id") int id){
        bookDao.makeOwner(person,id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("{id}/free")
    public String makeFree(@PathVariable("id") int id){
        bookDao.makeFree(id);
        return "redirect:/books/" + id;
    }
    @GetMapping("{id}/edit")
    public String edit(@PathVariable("id")int id,Model model){
        model.addAttribute("book",bookDao.show(id));
        return "books/edit";
    }
    @PatchMapping("{id}")
    public String editPatch(@ModelAttribute("book") @Valid Book book, BindingResult result){
        if(result.hasErrors()){
            return "books/edit";
        } else {
            bookDao.edit(book);
            return "redirect:/books";
        }
    }
    @GetMapping("/search")
    public String find(){
        return "books/findBook";
    }
    @PatchMapping ("/search")
    public String find(@RequestParam("key")String key,Model model){
        model.addAttribute("books",bookDao.find(key));
        return "books/findBook";
    }
}
