package cz.kzrv.library.services;

import cz.kzrv.library.models.Book;
import cz.kzrv.library.models.Person;
import cz.kzrv.library.repositories.BookRepositories;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepositories bookRepositories;

    @Autowired
    public BookService(BookRepositories bookRepositories) {
        this.bookRepositories = bookRepositories;
    }
    public List<Book> index(){
        return bookRepositories.findAll();
    }
    public Book show(int id){
        return bookRepositories.findById(id).orElse(null);
    }
    @Transactional
    public void save(Book book){
        bookRepositories.save(book);
    }
    @Transactional
    public void edit(Book book){
        bookRepositories.save(book);
    }
    @Transactional
    public void delete(int id){
        bookRepositories.deleteById(id);
    }
    public Optional<Person> getOwner(int id){
        Book book = bookRepositories.findById(id).orElse(null);
        return Optional.ofNullable(book.getOwner());
    }

    @Transactional
    public void makeOwner(Person person, int id_book){
        Book book = bookRepositories.findById(id_book).orElse(null);
        book.setDate(new Date());
        book.setOwner(person);
    }
    @Transactional
    public void makeFree(int id){
        Book book = bookRepositories.findById(id).orElse(null);
        book.setOwner(null);
        book.setDate(null);
    }

    public List<Book> indexPage(Integer page, Integer bookPerPage) {
        return bookRepositories.findAll(PageRequest.of(page,bookPerPage)).getContent();
    }
    public List<Book> sortByYear(){
        return bookRepositories.findAll(Sort.by("year"));
    }

    public List<Book> sortAndPage(Integer page, Integer bookPerPage) {
        return bookRepositories.findAll(PageRequest.of(page,bookPerPage,Sort.by("year"))).getContent();
    }

    public List<Book> find(String key) {
        List<Book> list = bookRepositories.findByNameStartingWith(key);
        Hibernate.initialize(list);
        return  list;
    }
}
