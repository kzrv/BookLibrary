package cz.kzrv.library.services;

import cz.kzrv.library.models.Book;
import cz.kzrv.library.models.Person;
import cz.kzrv.library.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }
    public List<Person> index(){
        return peopleRepository.findAll();
    }
    public Person show(int id){
        return peopleRepository.findById(id).orElse(null);
    }
    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }
    @Transactional
    public void edit(Person person){
        peopleRepository.save(person);
    }
    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }
    public Optional<Person> find(String name){
        return peopleRepository.findByName(name);
    }
    public List<Book> check(int id){
        Person person = peopleRepository.findById(id).orElse(null);
        List<Book> list = person.getList();
        Hibernate.initialize(list);
        long tenDays=864000000;
        list.forEach(s->{
            if(s.getDate()!=null)s.setEnd(s.getDate().getTime() + tenDays < new Date().getTime());
        });
        return list;
    }
}
