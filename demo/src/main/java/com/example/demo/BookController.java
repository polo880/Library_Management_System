package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book>getBook(@PathVariable Integer bookId){//book存在與否
        Book book=bookService.getBookById(bookId);
        if(book!=null){
            return ResponseEntity.status(HttpStatus.OK).body(book);//返回200
        }//book寫在body
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();//返回404
        }
    }

    @PostMapping("/books")
    public ResponseEntity<Book>createBook(@RequestBody BookRequest bookRequest){//book跟request分開是因為要使用到的variable不同
        Integer BookId =bookService.createBook(bookRequest);
        Book book = bookService.getBookById(BookId);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);//返回201相較200多了有更新的資訊。
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book>updateBook(@PathVariable Integer bookId,@RequestBody BookRequest bookRequest){
        Book book= bookService.getBookById(bookId);
        if(book==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();//返回404
        }
        bookService.updateBook(bookId,bookRequest);
        Book updatedbook= bookService.getBookById(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedbook);//返回200
    }
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Book>deletebook(@PathVariable Integer bookId){
        Book book= bookService.getBookById(bookId);
        if(book==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();//返回404
        }
        bookService.deleteBookById(bookId);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();//返回204
    }

}
