package cz.kzrv.library.dao;

/*
 */
//@Component
//public class BookDao {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public BookDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Book> index(){
//        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
//    }
//    public Book show(int id){
//        return jdbcTemplate.query("select * from book where id=?",new BeanPropertyRowMapper<>(Book.class),
//                new Object[]{id}).stream().findAny().orElse(null);
//    }
//    public void save(Book book){
//        jdbcTemplate.update("INSERT INTO book(name,author,year) values (?,?,?)", book.getName(),book.getAuthor(),book.getYear());
//    }
////    public void edit(Person person){
////        jdbcTemplate.update("UPDATE book SET name=?,year=? where id=?", person.getName(),person.getYear(),person.getId());
////    }
//    public void delete(int id){
//    jdbcTemplate.update("delete from book where id=?",id);
//}
//
//    public Optional<Person> getOwner(int id){
//        return jdbcTemplate.query("select Person.* from book join person on person.id=book.id_person where book.id=?", new BeanPropertyRowMapper<>(Person.class),
//                new Object[]{id}).stream().findAny();
//
//    }
//    public void makeOwner(int id_person, int id_book){
//        jdbcTemplate.update("UPDATE book set id_person=? where id=?",id_person,id_book);
//
//    }
//    public void makeFree(int id_book){
//        jdbcTemplate.update("UPDATE book set id_person=? where id=?",null,id_book);
//
//    }
//
//    public void update(Book book) {
//        jdbcTemplate.update("UPDATE book SET name=?,author=?, year=? where id=?", book.getName(),book.getAuthor(),book.getYear(),book.getId());
//    }
//    public List<Book> check(int id){
//        return jdbcTemplate.query("select Book.* from person join book on person.id=book.id_person where book.id_person=?;",
//                new BeanPropertyRowMapper<>(Book.class),new Object[]{id});
//    }
//}
