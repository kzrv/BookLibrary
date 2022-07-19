package cz.kzrv.library.dao;//package cz.kzrv.library.dao;
//
//import cz.kzrv.library.models.Book;
//import cz.kzrv.library.models.Person;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class Mapper implements RowMapper<Book> {
//    @Override
//    public Book mapRow(ResultSet result, int rowNum) throws SQLException {
//        Book book = new Book();
//        var a = result.getInt("id_person");
//        book.setId(result.getInt("id"));
//        book.setName(result.getString("name"));
//        book.setAuthor(result.getString("author"));
//        book.setYear(result.getInt("year"));
//        return book;
//    }
//}