package cz.kzrv.library.util;


import cz.kzrv.library.models.Person;
import cz.kzrv.library.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class Validation implements Validator {
    private final PeopleService personDao;

    @Autowired
    public Validation(PeopleService personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(personDao.find(person.getName()).isPresent()){
            errors.rejectValue("name","","Creat another name, this name is already exist");
        }
    }
}
