package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
@Component
public class BookDaoImpl implements BookDao{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public Book getBookById(Integer bookId){
        String sql="SELECT book_id, title, author,image_url, price, published_date, created_date,last_modified_date "+
                "FROM book WHERE book_id = :bookId";
        Map<String,Object> map = new HashMap<>();
        map.put("bookId",bookId);
        List<Book> bookList=namedParameterJdbcTemplate.query(sql,map,new BookRowMapper());
        if (bookList.size() > 0) {
            return bookList.get(0);
        } else {
            return null;
        }

    }
    @Override
    public  Integer createBook(BookRequest bookRequest){
        String sql = "INSERT INTO book(title, author, image_url, price, published_date, created_date, last_modified_date) " +
                "VALUES (:title, :author, :imageUrl, :price, :publishedDate, :createdDate, :lastModifiedDate)";


        Map<String,Object> map=new HashMap<>();
        map.put("title",bookRequest.getTitle());
        map.put("author",bookRequest.getAuthor());
        map.put("imageUrl",bookRequest.getImageUrl());
        map.put("price",bookRequest.getPrice());
        map.put("publishedDate",bookRequest.getPublishedDate());

        Date now=new Date();
        map.put("createdDate",now);
        map.put("lastModifiedDate",now);
        KeyHolder keyHolder=new GeneratedKeyHolder();//用來接key的因為我們用auto increment所以我們會不知道id是多少
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);//map多套了一個ftn是為了把map的資訊傳到database

        int bookId=keyHolder.getKey().intValue();
        return bookId;
    }
    @Override
    public void updateBook(Integer bookId,BookRequest bookRequest){
        String sql = "UPDATE book SET title = :title, author = :author, image_url = :imageUrl, " +
                "price = :price, published_date = :publishedDate, last_modified_date = :lastModifiedDate" +
                " WHERE book_id = :bookId ";

        Map<String,Object> map= new HashMap<>();
        map.put("title",bookRequest.getTitle());
        map.put("author",bookRequest.getAuthor());
        map.put("imageUrl",bookRequest.getImageUrl());
        map.put("price",bookRequest.getPrice());
        map.put("publishedDate",bookRequest.getPublishedDate());
        map.put("bookId",bookId);
        Date date=new Date();
        map.put("lastModifiedDate",date);
        namedParameterJdbcTemplate.update(sql,map);

    }

    @Override
    public void deleteBookById(Integer bookId){
        String sql="DELETE FROM book WHERE book_id=:bookId";//mysql 的=就是==
        Map<String,Object>map=new HashMap<>();

        map.put("bookId",bookId);
        namedParameterJdbcTemplate.update(sql,map);

    }
}
