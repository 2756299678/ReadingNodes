package com.example.baohui.readingnodes.Bean;

import java.io.Serializable;

/**
 * Created by BaoHui on 2018/6/15.
 * 书籍类
 */

public class Book implements Serializable {


    private int id;
    private String name;
    private String author;
    private String press;
    private String ISBN;
    private String category;
    private int category_id;

    public Book(){

    }
    public Book(String  name,String author,String press,String ISBN)
    {
        this.name=name;
        this.author=author;
        this.press=press;
        this.ISBN=ISBN;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void id(int book_id) {
        this.id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }



}
