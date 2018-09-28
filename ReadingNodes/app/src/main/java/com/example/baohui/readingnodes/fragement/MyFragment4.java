package com.example.baohui.readingnodes.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.baohui.readingnodes.Activity.AboutusActivity;
import com.example.baohui.readingnodes.Activity.CountBookActivity;
import com.example.baohui.readingnodes.Activity.CountUserActivity;
import com.example.baohui.readingnodes.Activity.LoginActivity;
import com.example.baohui.readingnodes.R;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment4 extends Fragment {

    public MyFragment4() {
    }
    private ImageView link_userImageview;
    private ImageView link_mangerImageview;
    private ImageView link_aboutusImageview;
    private LinearLayout link_userLayout;
    private LinearLayout link_managerLayout;
    private LinearLayout link_aboutsLayout;
    private Button exitButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f4_content,container,false);
        //TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        //txt_content.setText("第四个Fragment");
        Log.e("baohui", "第四个Fragment");
       //用户笔记管理
        link_userLayout=(LinearLayout) view.findViewById(R.id.f4_linerlayout_user);
        link_userLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), CountUserActivity.class);
                startActivity(intent);
            }
        });
        //用户笔记统计
        link_managerLayout=(LinearLayout)view.findViewById(R.id.f4_linerlayout_message);
        link_managerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), CountBookActivity.class);
                startActivity(intent);
            }
        });
        //关于我们
        link_aboutsLayout =(LinearLayout) view.findViewById(R.id.f4_linerlayuot_aboutus);
        link_aboutsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //执行添加算法，返回Mainactivity界面
                Intent intent = new Intent();
                intent.setClass(getActivity(), AboutusActivity.class);
                startActivity(intent);
            }
        });

        //退出登录
        exitButton = (Button) view.findViewById(R.id.f4_button_exit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //执行添加算法，返回Mainactivity界面
                Intent intent = new Intent();
                intent.setClass(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
