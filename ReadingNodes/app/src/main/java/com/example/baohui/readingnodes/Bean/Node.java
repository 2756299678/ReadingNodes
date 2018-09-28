package com.example.baohui.readingnodes.Bean;

/**
 * Created by BaoHui on 2018/6/15.
 * 笔记类
 */

public class Node {
    private int user_id;
    private int book_id;

    public Node() {

    }

    private String create_date;
    private String context;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }


}
