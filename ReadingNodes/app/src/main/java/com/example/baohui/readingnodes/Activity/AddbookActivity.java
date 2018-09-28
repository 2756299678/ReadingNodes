package com.example.baohui.readingnodes.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baohui.readingnodes.Bean.Book;
import com.example.baohui.readingnodes.Dao.BookDao;
import com.example.baohui.readingnodes.Dao.CategoryDao;
import com.example.baohui.readingnodes.R;

public class AddbookActivity extends AppCompatActivity {

    private Book book;
    private BookDao bookdao;

    private EditText book_name;
    private EditText book_author;
    private EditText book_publish;
    private EditText book_isbn;
    private EditText book_category;
    private Button add_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbook);

        book_name=(EditText) findViewById(R.id.addbook_edittext_name);
        book_author=(EditText)findViewById(R.id.addbook_edittext_author);
        book_publish=(EditText)findViewById(R.id.addbook_edittext_publish);
        book_isbn=(EditText)findViewById(R.id.addbook_edittext_isbn);
        book_category=(EditText)findViewById(R.id.addbook_edittext_category);



        add_book=(Button)findViewById(R.id.addbook_button_add);
        add_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //执行添加算法
                String name=book_name.getText().toString();
                String author=book_author.getText().toString();
                String publish=book_publish.getText().toString();
                String isbn=book_isbn.getText().toString();
                String category=book_category.getText().toString();

                book=new Book(name,author,publish,isbn);

                bookdao = new BookDao();
                try{
                    //检验分类是否存在
                    //存在查询id再进行赋值
                    int i=-1;
                    CategoryDao categoryDao = new CategoryDao();
                    if(categoryDao.checkcategory(category,getApplicationContext()))
                    {
                        i=categoryDao.retrun_categoryid(category,getApplicationContext());
                        book.setCategory_id(i);
                        //执行添加算法
                        BookDao bookDao=new BookDao();
                        bookDao.addbook(book,getApplicationContext());
                        //执行添加算法，返回Mainactivity界面
                        Intent intent = new Intent();
                        intent.setClass(AddbookActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "类别不存在", Toast.LENGTH_SHORT).show();
                    }

                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), "添加失败", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
