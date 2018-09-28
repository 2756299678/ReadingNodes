package com.example.baohui.readingnodes.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.baohui.readingnodes.Bean.User;
import com.example.baohui.readingnodes.database.DBHelper;

/**
 * Created by BaoHui on 2018/6/20.
 */

public class UserDao {
    /**
     * 添加联系人
     * @param user
     * @param context
     */
    public  void adduser(User user, Context context)
    {
        // 数据库服务
        DBHelper dbheaper = new DBHelper(context);
        // 插入到book
        try{
            dbheaper.executeSQL(String.format("insert into user(username,password) values('%s','%s')", user.getName(),user.getPassword()));
            System.out.println("用户添加成功！");
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("用户添加失败！");
        }
        finally {
            //dbheaper.close();
        }

    }

    /**
     *  验证算法验证用户名是否存在
     * @param username
     * @param context
     * @return
     */
    public boolean checkUser_exit(String username,Context context)
    {
        boolean  f= false;
        // 数据库服务
        DBHelper dbheaper = new DBHelper(context);
        System.out.println("username:"+username);
        //SQLiteDatabase db=dbheaper.getDb();
       // String sql="select username from user";
        try{
            Cursor cursor = dbheaper.query("user");
            while(cursor.moveToNext()){
                String str=cursor.getString(cursor.getColumnIndex("username"));
                System.out.println("str"+str);
                if(str.equals(username))
                {
                    f=true;
                    System.out.println("用户名已注册");
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("验证用户名出错");
        }
        finally {
            //dbheaper.close();
        }
        return f;
    }

    /**
     * //登陆验证
     * @param username
     * @param password
     * @param context
     * @return
     */
    public int checkLogin(String username,String password,Context context)
    {
        int i=2;
        DBHelper dbheaper = new DBHelper(context);
        //SQLiteDatabase db=dbheaper.getDb();
        //String sql="select * from user";
        try{
            Cursor cursor = dbheaper.query("user");

            while(cursor.moveToNext()){
                String struser=cursor.getString(cursor.getColumnIndex("username"));
                //验证用户名
                if(struser.equals(username))
                {
                    String strpass=cursor.getString(cursor.getColumnIndex("password"));
                    //验证密码
                    if(strpass.equals(password))
                    {
                        i =0;
                    }
                    else
                    {
                        i=1;
                        System.out.print("密码错误");
                    }
                }
                else
                {
                    i=2;
                    System.out.println("用户名不存在");
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("登陆验证出错");
        }
        finally {
           // dbheaper.close();
        }
        return i;
    }


    /**
     * 通过name找id
     * @param name
     * @param context
     * @return
     */
    public int retrun_user_id(String name,Context context)
    {
        int i = -1;
        boolean f=false;
        DBHelper dbheaper = new DBHelper(context);
        try{
            i=dbheaper.getIntegerof(String.format("select uid from user where username='%s'", name));
        } catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("验证user_name_to_bid出错");
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
    public String find_user_byid(int id,Context context)
    {
        String str= "";
        boolean f=false;
        DBHelper dbheaper = new DBHelper(context);
        try{
            str=dbheaper.getStringOf(String.format("select username from user where uid='%s'", id));
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
}
