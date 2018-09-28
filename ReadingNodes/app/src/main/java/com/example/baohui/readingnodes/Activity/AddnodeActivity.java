package com.example.baohui.readingnodes.Activity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baohui.readingnodes.Bean.Node;
import com.example.baohui.readingnodes.Dao.BookDao;
import com.example.baohui.readingnodes.Dao.NodeDao;
import com.example.baohui.readingnodes.Dao.UserDao;
import com.example.baohui.readingnodes.R;

import java.util.Date;

public class AddnodeActivity extends AppCompatActivity {

    private EditText bookTextView;
    private EditText userTextView;
    private EditText contextTextView;
    private Button addnodeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnode);

        bookTextView=(EditText) findViewById(R.id.addnode_edittext_book_name);
        userTextView=(EditText) findViewById(R.id.addnode_edittext_user_name);
        contextTextView=(EditText)findViewById(R.id.addnode_edittext_node_name);
        addnodeButton=(Button) findViewById(R.id.addnode_button_add);

        addnodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=userTextView.getText().toString();
                String bookname=bookTextView.getText().toString();
                String content=contextTextView.getText().toString();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// HH:mm:ss
                //获取当前时间
                Date date = new Date(System.currentTimeMillis());
                String datetime=simpleDateFormat.format(date);


                //检验用户名是否存在
                UserDao userDao = new UserDao();
                BookDao bookDao = new BookDao();
                Node node=new Node();

                System.out.println(userDao.checkUser_exit(username,getApplicationContext()));
                //用户名存在
                if(userDao.checkUser_exit(username,getApplicationContext()))
                {

                    int userid=userDao.retrun_user_id(username,getApplicationContext());
                    //书籍存在
                    if(bookDao.exitbook(bookname,getApplicationContext()))
                    {
                        int bookid=bookDao.retrun_book_id(bookname,getApplicationContext());
                        node.setBook_id(bookid);
                        node.setUser_id(userid);
                        node.setCreate_date(datetime);
                        node.setContext(content);
                        NodeDao nodeDao = new NodeDao();
                        nodeDao.addNode(node,getApplicationContext());
                        //执行添加算法，返回Mainactivity界面
                        Intent intent = new Intent();
                        intent.setClass(AddnodeActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "书籍不存在", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "用户名不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
