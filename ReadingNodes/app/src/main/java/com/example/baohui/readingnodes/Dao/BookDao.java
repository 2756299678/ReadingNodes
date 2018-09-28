package com.example.baohui.readingnodes.Dao;

import android.content.Context;
import android.database.Cursor;

import com.example.baohui.readingnodes.Bean.Book;
import com.example.baohui.readingnodes.database.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BaoHui on 2018/6/20.
 */

public class BookDao {
    /**
     * 添加书籍
     * @param book
     * @param context
     */
    public  void addbook(Book book,Context context)
    {
        // 数据库服务
        DBHelper dbheaper = new DBHelper(context);
        // 插入到book
        try{
            dbheaper.executeSQL(String.format("insert into book(name,author,press,isbn,category_id) values('%s','%s','%s','%s','%s')", book.getName(), book.getAuthor(), book.getPress(),book.getISBN(), book.getCategory_id()));
            System.out.println("书籍添加成功！");
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("书籍添加失败！");
        }
        finally {
            dbheaper.close();
        }

    }

    /**
     * 通过名称检查书籍是否存在
     * @param name
     * @param context
     * @return
     */
    public boolean exitbook(String name,Context context)
    {
        boolean f=false;
        DBHelper dbheaper = new DBHelper(context);
        try{
            Cursor cursor = dbheaper.query("book");
            while(cursor.moveToNext()){
                String str=cursor.getString(cursor.getColumnIndex("name"));
                System.out.println(str);
                if(str.equals(name))
                {
                    f=true;
                    System.out.println("找到书籍");
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("书籍检测出错");
        }
        finally {
            //dbheaper.close();
        }
        return f;
    }

    /**
     * 通过name找id
     * @param name
     * @param context
     * @return
     */
    public int retrun_book_id(String name,Context context)
    {
        int i = -1;
        boolean f=false;
        DBHelper dbheaper = new DBHelper(context);
        try{
            i=dbheaper.getIntegerof(String.format("select bid from book where name='%s'", name));
        } catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("验证book_name_to_bid出错");
        }
        finally {
            //dbheaper.close();
        }
        return i;
    }

    /**
     * 通过id找kond
     * @param id
     * @param context
     * @return
     */
    public String find_kind_byid(int id,Context context)
    {
        String str= "";
        boolean f=false;
        DBHelper dbheaper = new DBHelper(context);
        try{
            str=dbheaper.getStringOf(String.format("select name from book where bid='%s'", id));
        } catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("验证book_id_to_name出错");
        }
        finally {
            //dbheaper.close();
        }
        return str;
    }

    public List<Map<String, Object>> getData(Context context){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        DBHelper dbheaper = new DBHelper(context);
        Cursor cursor = dbheaper.query("book");
        while(cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndex("bid"));
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String author=cursor.getString(cursor.getColumnIndex("author"));
            String press=cursor.getString(cursor.getColumnIndex("press"));
            String isbn=cursor.getString(cursor.getColumnIndex("isbn"));
            int category_id=cursor.getInt(cursor.getColumnIndex("category_id"));
            CategoryDao categoryDao = new CategoryDao();
            String category=categoryDao.find_kind_byid(category_id,context);
            //输出
            System.out.println(id+"  "+name);
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("id", id);
            map.put("name",name );
            map.put("author",author);
            map.put("press", press);
            map.put("isbn", isbn);
            map.put("category", category);
            list.add(map);
        }
        return list;
    }

    public void del_book(int id,Context context)
    {
        DBHelper dbheaper = new DBHelper(context);
        dbheaper.del(id,"book","bid");
    }

    public Book getbook(int id,Context context)
    {
        Book book = null;
        DBHelper dbheaper = new DBHelper(context);
        try
        {
            String name=dbheaper.getStringOf(String.format("select name from book where bid='%s'", id));
            String author=dbheaper.getStringOf(String.format("select author from book where bid='%s'", id));
            String press=dbheaper.getStringOf(String.format("select press from book where bid='%s'", id));
            String isbn=dbheaper.getStringOf(String.format("select isbn from book where bid='%s'", id));
            int category_id=dbheaper.getIntegerof(String.format("select category_id from book where bid='%s'", id));
            String category=dbheaper.getStringOf(String.format("select kind from category where bid='%s'", category_id));
            //System.out.println("category:"+category);
            book=new Book(name,author,press,isbn);
            book.setId(id);
            book.setCategory_id(category_id);
            book.setCategory(category);
        }catch(Exception e)
        {
            e.printStackTrace();
        }finally {

        }
        return book;
    }

    public void changeBook(Book book,Context context)
    {
        DBHelper dbheaper = new DBHelper(context);
        try
        {
            dbheaper.executeSQL(String.format("update book set name='%s',author='%s',press='%s',isbn='%s',category_id='%s' where bid='%s'",book.getName(),book.getAuthor(),book.getPress(),book.getISBN(),book.getCategory_id(),book.getId()));
        }catch (Exception e )
        {
            e.printStackTrace();
        }

    }
}
