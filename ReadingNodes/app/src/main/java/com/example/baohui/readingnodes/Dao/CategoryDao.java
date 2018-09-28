package com.example.baohui.readingnodes.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.baohui.readingnodes.database.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BaoHui on 2018/6/20.
 */

public class CategoryDao {
    /**
     * 添加类别
     * @param name
     * @param context
     */
    public void addcategory(String name, Context context)
    {
        DBHelper dbheaper = new DBHelper(context);
        try{
            dbheaper.executeSQL(String.format("insert into category(kind) values('%s')", name));
            System.out.println("类别添加成功！");
        }catch(Exception e)
        {
            System.out.println("书籍类别添加失败！");
        }
        finally {
            dbheaper.close();
        }
    }

    /**
     * 检查类别是否存在
     * @param name
     * @return
     * return false 不存在
     * return true  存在
     */
    public boolean checkcategory(String name,Context context)
    {
        boolean f=false;
        DBHelper dbheaper = new DBHelper(context);
        try{
            Cursor cursor = dbheaper.query("category");
            while(cursor.moveToNext()){
                String str=cursor.getString(cursor.getColumnIndex("kind"));
                System.out.println(str);
                if(str.equals(name))
                {
                    f=true;
                    System.out.println("找到类别名");
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("验证类名出错");
        }
        finally {
            //dbheaper.close();
        }
        return f;
    }

    /**
     * 通过kind找id
     * @param name
     * @param context
     * @return
     */
    public int retrun_categoryid(String name,Context context)
    {
        int i = -1;
        boolean f=false;
        DBHelper dbheaper = new DBHelper(context);
        try{
            i=dbheaper.getIntegerof(String.format("select bid from category where kind='%s'", name));
        } catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("验证用户名出错");
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
            str=dbheaper.getStringOf(String.format("select kind from category where bid='%s'", id));
        } catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("验证用户名出错");
        }
        finally {
            //dbheaper.close();
        }
        return str;
    }

    public List<Map<String, Object>> getData(Context context){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        DBHelper dbheaper = new DBHelper(context);
        Cursor cursor = dbheaper.query("category");
        while(cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndex("bid"));
            String name=cursor.getString(cursor.getColumnIndex("kind"));
            //输出
            System.out.println(id+"  "+name);
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("bid", id);
            map.put("kind",name );
            list.add(map);
        }
        return list;
    }

    public void del_category(int id,Context context)
    {
        DBHelper dbheaper = new DBHelper(context);
        dbheaper.del(id,"category","bid");
    }
}
