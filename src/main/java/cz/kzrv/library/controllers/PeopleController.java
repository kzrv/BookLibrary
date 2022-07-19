package cz.kzrv.library.controllers;

import cz.kzrv.library.models.Book;
import cz.kzrv.library.models.Person;
import cz.kzrv.library.services.PeopleService;
import cz.kzrv.library.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService personDao;
    private final Validation validation1;

    @Autowired
    public PeopleController(PeopleService personDao, Validation validation1) {
        this.personDao = personDao;
        this.validation1 = validation1;
    }

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("people",personDao.index());
        return "/people/index";
    }

    @GetMapping("new")
    public String newPerson(Model model){
        model.addAttribute("person",new Person());
        return "/people/new";
    }
    @GetMapping("{id}")
    public String showPerson(Model model, @PathVariable("id") int id){
        model.addAttribute("person",personDao.show(id));
        List<Book> list= personDao.check(id);
        if(list.stream().count()>0) {
            model.addAttribute("list",list);
        }
        return "/people/show";
    }
    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult result){
        validation1.validate(person,result);
        if(result.hasErrors()){
            return "people/new";
        }
        else
        personDao.save(person);
        return "redirect:/people";
    }
    @GetMapping("{id}/edit")
    public String edit(Model model,@PathVariable("id") int id){
        model.addAttribute("person",personDao.show(id));
        return "people/edit";
    }
    @PostMapping("{id}")
    public String editPerson(@ModelAttribute("person") @Valid Person person, BindingResult result){
        if(result.hasErrors()){
            return "/people/edit";
        }
        personDao.edit(person);
        return "redirect:/people";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id")int id ){
        personDao.delete(id);
        return "redirect:/people";
    }

}
