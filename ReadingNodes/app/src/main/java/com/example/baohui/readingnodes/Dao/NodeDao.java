package com.example.baohui.readingnodes.Dao;

import android.content.Context;
import android.database.Cursor;

import com.example.baohui.readingnodes.Bean.Node;
import com.example.baohui.readingnodes.database.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BaoHui on 2018/6/21.
 */

public class NodeDao {
    /**
     * 添加笔记
     * @param node
     * @param context
     */
    public void addNode(Node node, Context context)
    {
        DBHelper dbheaper = new DBHelper(context);
        try{
            dbheaper.executeSQL(String.format("insert into note(uid,bid,date,content) values('%s','%s','%s','%s')",node.getUser_id(),node.getBook_id(),node.getCreate_date(),node.getContext() ));
            System.out.println("笔记添加成功！");
        }catch(Exception e)
        {
            System.out.println("笔记添加失败！");
        }
        finally {
            dbheaper.close();
        }
    }

    public List<Map<String, Object>> getData(Context context){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        DBHelper dbheaper = new DBHelper(context);
        Cursor cursor = dbheaper.query("note");
        System.out.println("执行查询笔记");
        while(cursor.moveToNext()){
            int uid=cursor.getInt(cursor.getColumnIndex("uid"));
            int bid=cursor.getInt(cursor.getColumnIndex("bid"));
            String date=cursor.getString(cursor.getColumnIndex("date"));
            String content=cursor.getString(cursor.getColumnIndex("content"));

            UserDao userDao=new UserDao();
            String username=userDao.find_user_byid(uid,context);
            BookDao bookDao=new BookDao();
            String bookname=bookDao.find_kind_byid(bid,context);
            //输出
            System.out.println(username +"  "+ bookname);
            Map<String, Object> map=new HashMap<String, Object>();

            map.put("username",username );
            map.put("bookname",bookname);
            map.put("date", date);

            list.add(map);
        }
        return list;
    }

    public List<Map<String, Object>> count_User_getData(Context context){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        DBHelper dbheaper = new DBHelper(context);
        Cursor cursor = dbheaper.query("user");
        System.out.println("执行通过用户统计笔记");
        while(cursor.moveToNext())
        {
            int userid=cursor.getInt(cursor.getColumnIndex("uid"));
            String username=cursor.getString(cursor.getColumnIndex("username"));
            int count=0;
            DBHelper dbheaper2 = new DBHelper(context);
            Cursor cursor2 = dbheaper2.query("note");
            while(cursor2.moveToNext())
            {
                int node_user_id=cursor2.getInt(cursor2.getColumnIndex("uid"));
                if(userid==node_user_id)
                {
                    count++;
                }
            }
            //输出
            System.out.println(username +"  "+ count);
            Map<String, Object> map=new HashMap<String, Object>();

            map.put("username",username );
            map.put("count",count);
            list.add(map);

        }
        return list;
    }

    public List<Map<String, Object>> count_Book_getData(Context context){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        DBHelper dbheaper = new DBHelper(context);
        Cursor cursor = dbheaper.query("book");
        System.out.println("执行通过用户统计笔记");
        while(cursor.moveToNext())
        {
            int userid=cursor.getInt(cursor.getColumnIndex("bid"));
            String username=cursor.getString(cursor.getColumnIndex("name"));
            int count=0;
            DBHelper dbheaper2 = new DBHelper(context);
            Cursor cursor2 = dbheaper2.query("note");
            while(cursor2.moveToNext())
            {
                int node_user_id=cursor2.getInt(cursor2.getColumnIndex("uid"));
                int node_book_id=cursor2.getInt(cursor2.getColumnIndex("bid"));
                System.out.print(node_book_id +""+node_user_id+"  ");
                if(userid==node_book_id)
                {
                    count++;
                }
            }
            //输出
            System.out.println(username +"  "+ count+"  "+userid);
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("bookname",username );
            map.put("count",count);
            list.add(map);

        }
        return list;
    }


}
