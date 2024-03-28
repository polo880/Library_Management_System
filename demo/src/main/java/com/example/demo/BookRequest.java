package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BookRequest {
    private String title;
    private String author;
    private String imageUrl;
    private Integer price;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//其實也可以在設定擋那邊設定
    private Date publishedDate;

    public String getTitle(){
        return  title;
    }
    public void setTitle(String title){
        this.title=title;
    }

    public void setAuthor(String author){
        this.author=author;
    }
    public String getAuthor(){
        return author;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl=imageUrl;
    }
    public Integer getPrice(){
        return price;
    }
    public  void setPrice(Integer price){
        this.price=price;
    }
    public Date getPublishedDate(){
        return publishedDate;
    }
    public void setPublishedDate(Date publishedDate){
        this.publishedDate=publishedDate;
    }
}
