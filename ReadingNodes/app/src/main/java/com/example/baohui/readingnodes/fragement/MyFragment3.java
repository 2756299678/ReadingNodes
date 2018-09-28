package com.example.baohui.readingnodes.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.baohui.readingnodes.Activity.AddnodeActivity;
import com.example.baohui.readingnodes.Dao.NodeDao;
import com.example.baohui.readingnodes.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment3 extends ListFragment {

    private ListView listView;
    private SimpleAdapter simpleAdapter;
    public MyFragment3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f3_content,container,false);
        //TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        //txt_content.setText("第三个Fragment");
        Log.e("baohui", "第三个Fragment");
        Button addnode_button=(Button) view.findViewById(R.id.f3_book_node_button);
        addnode_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), AddnodeActivity.class);
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

        NodeDao nodeDao = new NodeDao();
        List<Map<String, Object>> list=nodeDao.getData(getActivity());
        //列表项组
        String[] category={"username","bookname","date"};
        //列表项id
        int[] to={R.id.f3_book_user_name,R.id.f3_book_node_book_name,R.id.f3_book_node_date};
        //适配器
        simpleAdapter= new SimpleAdapter(getActivity(),list,R.layout.f3_list_content,category,to);
        setListAdapter(simpleAdapter);

    }
}
