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

public class ChangebookActivity extends AppCompatActivity {

    private Book newbook;
    private BookDao bookdao;

    private EditText name_EditText;
    private EditText author_EditText;
    private EditText press_EditText;
    private EditText isbn_EditText;
    private EditText category_EditText;
    private Button change_Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changebook);
        //获取对象
        Book book = (Book) getIntent().getSerializableExtra("book_data");
        //获取控件
        name_EditText=(EditText) findViewById(R.id.changebook_edittext_name);
        author_EditText=(EditText) findViewById(R.id.changebook_edittext_author);
        press_EditText=(EditText) findViewById(R.id.changebook_edittext_publish);
        isbn_EditText=(EditText) findViewById(R.id.changebook_edittext_isbn);
       category_EditText=(EditText) findViewById(R.id.changebook_edittext_category);

        //赋值
        name_EditText.setText(book.getName());
        author_EditText.setText(book.getAuthor());
        press_EditText.setText(book.getPress());
        isbn_EditText.setText(book.getISBN());
        System.out.print("changebook -category:"+book.getCategory());
        category_EditText.setText(book.getCategory());

        final int id=book.getId();

        //获取按钮，执行修改操作
        change_Button=(Button) findViewById(R.id.changebook_button_change);
        change_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //执行添加算法
                String name=name_EditText.getText().toString();
                String author=author_EditText.getText().toString();
                String publish=press_EditText.getText().toString();
                String isbn=isbn_EditText.getText().toString();
                String category=category_EditText.getText().toString();

                newbook=new Book(name,author,publish,isbn);
                newbook.setId(id);

                bookdao = new BookDao();
                try{
                    //检验分类是否存在
                    //存在查询id再进行赋值
                    int i=-1;
                    CategoryDao categoryDao = new CategoryDao();
                    if(categoryDao.checkcategory(category,getApplicationContext()))
                    {
                        i=categoryDao.retrun_categoryid(category,getApplicationContext());
                        newbook.setCategory_id(i);
                        //执行添加算法
                        BookDao bookDao=new BookDao();
                        bookDao.changeBook(newbook,getApplicationContext());
                        //执行添加算法，返回Mainactivity界面
                        Intent intent = new Intent();
                        intent.setClass(ChangebookActivity.this, MainActivity.class);
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
                    Toast.makeText(getApplicationContext(), "修改失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
