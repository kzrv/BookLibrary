package cz.kzrv.library.repositories;

import cz.kzrv.library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepositories extends JpaRepository<Book,Integer> {
    List<Book> findByNameStartingWith(String name);
}
