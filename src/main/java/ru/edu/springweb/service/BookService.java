package ru.edu.springweb.service;

import org.springframework.stereotype.Service;
import ru.edu.springweb.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final List<Book> books = new ArrayList<>();
    private int nextId = 1;

    public List<Book> getAllBooks(){
        return books;
    }

    public Optional<Book> getBookId(int id){
        return books.stream().filter(book -> book.getId() == id).findFirst();
    }

    public Book addBook(Book book){
        book.setId(nextId++);
        books.add(book);
        return book;
    }

    public boolean updateBook(int id, Book updateBook){
        Optional<Book> existingBook = getBookId(id);
        if(existingBook.isPresent()){
            Book book = existingBook.get();
            book.setTitle(updateBook.getTitle());
            book.setAuthor(updateBook.getAuthor());
            return true;
        }
        return false;
    }

    public boolean deleteBook(int id){
        return books.removeIf(book -> book.getId() == id);
    }

}
