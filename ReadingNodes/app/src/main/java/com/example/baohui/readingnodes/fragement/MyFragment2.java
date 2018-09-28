package com.example.baohui.readingnodes.fragement;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baohui.readingnodes.Activity.AddcategoryActivity;
import com.example.baohui.readingnodes.Dao.CategoryDao;
import com.example.baohui.readingnodes.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment2 extends ListFragment {

    private ListView listView;
    private SimpleAdapter simpleAdapter;
    public MyFragment2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f2_content,container,false);
       // TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        //txt_content.setText("第二个Fragment");
        Log.e("baohui", "第二个Fragment");
        Button addcategory_button=(Button) view.findViewById(R.id.f2_book_add_button);
        addcategory_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), AddcategoryActivity.class);
                startActivity(intent);
            }
        });
        //列表视图
        listView=(ListView) view.findViewById(android.R.id.list);
        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CategoryDao categoryDao = new CategoryDao();
        List<Map<String, Object>> list=categoryDao.getData(getActivity());
        //列表项组
        String[] category={"bid","kind"};
        //列表项id
        int[] to={R.id.f2_category_id,R.id.f2_category_name};
        //适配器
        simpleAdapter= new SimpleAdapter(getActivity(),list,R.layout.f2_list_content,category,to);
        setListAdapter(simpleAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //为listview添加监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                final long temp = arg3;
                LinearLayout layout = (LinearLayout) listView.getChildAt((int) temp);
                TextView f1_book_id = (TextView) layout.findViewById(R.id.f2_category_id);
                String id = f1_book_id.getText().toString();
                final int item_id = Integer.parseInt(id);
                System.out.println("id:" + id);
                builder.setMessage("是否进行删除？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        CategoryDao categoryDao = new CategoryDao();
                        //删除数据
                        categoryDao.del_category(item_id, getActivity());
                        //重新加载数据
                        List<Map<String, Object>> list=categoryDao.getData(getActivity());
                        //列表项组
                        String[] category={"bid","kind"};
                        //列表项id
                        int[] to={R.id.f2_category_id,R.id.f2_category_name};
                        //适配器
                        simpleAdapter= new SimpleAdapter(getActivity(),list,R.layout.f2_list_content,category,to);
                        setListAdapter(simpleAdapter);
                        Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                //创建提示对话框
                AlertDialog ad = builder.create();
                //显示对话框
                ad.show();
            }
        });
    }
}
