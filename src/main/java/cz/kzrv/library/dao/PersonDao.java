package cz.kzrv.library.dao;//package cz.kzrv.library.dao;
//
//
//import cz.kzrv.library.models.Book;
//import cz.kzrv.library.models.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class PersonDao {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index(){
//        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
//    }
//    public Person show(int id){
//        return jdbcTemplate.query("select * from person where id=?",new BeanPropertyRowMapper<>(Person.class),
//                new Object[]{id}).stream().findAny().orElse(null);
//    }
//    public void save(Person person){
//        jdbcTemplate.update("INSERT INTO person(name,year) values (?,?)", person.getName(),person.getYear());
//    }
//    public void edit(Person person){
//        jdbcTemplate.update("UPDATE person SET name=?,year=? where id=?", person.getName(),person.getYear(),person.getId());
//    }
//    public void delete(int id){
//        jdbcTemplate.update("delete from person where id=?",id);
//    }
//
//    public Optional<Person> find(String name){
//        return jdbcTemplate.query("SELECT * from person where name=?", new BeanPropertyRowMapper<>(Person.class),
//                new Object[]{name}).stream().findAny();
//    }
//}
