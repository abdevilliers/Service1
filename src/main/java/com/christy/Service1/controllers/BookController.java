package com.christy.Service1.controllers;


import com.christy.Service1.models.Book;
import com.christy.Service1.repositories.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book-service")
public class BookController {

   private BookRepository bookRepository;
   public BookController(BookRepository bookRepository){
       this.bookRepository = bookRepository;
   }
    @GetMapping("/books")
    public List<Book> getBooks(){
        return this.bookRepository.findAll();
    }

    @GetMapping("/books/{bookId}")
    public Book getBookById(@PathVariable("bookId") Integer bookId){
       Optional<Book> returnedBook = this.bookRepository.findById(bookId);
       if(returnedBook.isEmpty()){
           String res  = String.format("bookId %s not present in db!!",Integer.toString(bookId));
           System.out.println(res);
           return null;
       }
       return returnedBook.get();
    }
    @PostMapping("/books")
    public String addBook(@RequestBody Book book){
       this.bookRepository.save(book);
       return "Book saved Successfully";
    }
    @DeleteMapping("/books/{bookId}")
    public String deleteBook(@PathVariable("bookId") Integer bookId){
       this.bookRepository.deleteById(bookId);
       return "Book deleted successfully if present in db!!";
    }
    @PutMapping("/books/{bookId}")
    public String updateBookById(@PathVariable("bookId") Integer bookId,
                                 @RequestBody Book book){
       Optional<Book> returnedBook =this.bookRepository.findById((bookId));
       if(returnedBook.isEmpty()){
           return String.format("bookid %s not present in db!!",Integer.toString(bookId));
       }
       Book newBook = returnedBook.get();
       newBook.setId(book.getId());
       newBook.setPrice(book.getPrice());
       newBook.setAuthor(book.getAuthor());
       newBook.setTitle(book.getTitle());
       this.bookRepository.save(newBook);
       return "book saved successfully into db";
    }
}
