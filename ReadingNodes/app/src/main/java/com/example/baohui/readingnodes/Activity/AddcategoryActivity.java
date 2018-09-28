package com.example.baohui.readingnodes.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baohui.readingnodes.Dao.CategoryDao;
import com.example.baohui.readingnodes.R;

public class AddcategoryActivity extends AppCompatActivity {

    private EditText categoryEdittext;
    private Button categorybutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcategory);
        categoryEdittext=(EditText) findViewById(R.id.addcategory_edittext_name);
        categorybutton=(Button) findViewById(R.id.addcategory_button_add);

        categorybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryname=categoryEdittext.getText().toString();
                try{

                    CategoryDao categoryDao = new CategoryDao();
                    if(categoryDao.checkcategory(categoryname,getApplicationContext()))
                    {
                        Toast.makeText(getApplicationContext(), "类别已存在", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        categoryDao.addcategory(categoryname,getApplicationContext());
                        //执行添加算法，返回Mainactivity界面
                        Intent intent = new Intent();
                        intent.setClass(AddcategoryActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "添加失败", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
