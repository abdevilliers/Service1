package com.christy.Service1.repositories;

import com.christy.Service1.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends CrudRepository<Book,Integer> {

    public Optional<Book> findById(Integer id);
    public List<Book> findAll();
    public Book save(Book book);
    public void deleteById(Integer id);
}
