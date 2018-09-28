package com.example.baohui.readingnodes.Activity;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.baohui.readingnodes.Dao.CategoryDao;
import com.example.baohui.readingnodes.Dao.NodeDao;
import com.example.baohui.readingnodes.R;

import java.util.List;
import java.util.Map;

public class CountUserActivity extends ListActivity {

    private ListView listView;
    private SimpleAdapter simpleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_user);
        //列表视图
        listView=(ListView)findViewById(android.R.id.list);
        NodeDao nodeDao=new NodeDao();
        List<Map<String, Object>> list=nodeDao.count_User_getData(getApplicationContext());
        //列表项组
        String[] category={"username","count"};
        //列表项id
        int[] to={R.id.count_user_list_textView_username,R.id.count_user_list_textView_number};
        //适配器
        simpleAdapter= new SimpleAdapter(getApplicationContext(),list,R.layout.activity_count_user_list,category,to);
        setListAdapter(simpleAdapter);
    }

}
