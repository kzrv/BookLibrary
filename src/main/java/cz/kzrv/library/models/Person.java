package cz.kzrv.library.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Person name should not be empty")
    @Column(name = "name")
    private String name;
    @Min(value = 1900, message = "year of born should be more than 1900")
    @Max(value = 2022, message = "year of born should be less than 2022")
    @Column(name="year")
    private int year;

    @OneToMany(mappedBy = "owner")
    private List<Book> list;

    public Person(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public Person() {
    }

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return name + ", " + year;
    }
}
