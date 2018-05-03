package com.zhangqie.customcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zhangqie.customcontrol.demo1.Demo1Activity;
import com.zhangqie.customcontrol.demo2.Demo2Activity;
import com.zhangqie.customcontrol.demo3.Demo3Activity;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private String[] strings = new String[]{
            "自定义控件","自定义控件---属性篇","自定义控件--组合控件","自定义控件-ViewGroup实现标签云"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        listView = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strings);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showIntent(i);
            }
        });
    }


    private void showIntent(int index){
        switch (index){
            case 0:
                startActivity(new Intent(MainActivity.this, Demo1Activity.class));
                break;
            case 1:
                startActivity(new Intent(MainActivity.this, Demo2Activity.class));
                break;
            case 2:
                startActivity(new Intent(MainActivity.this, Demo3Activity.class));
                break;
            case 3:
                startActivity(new Intent(MainActivity.this, Demo1Activity.class));
                break;
            case 4:
                startActivity(new Intent(MainActivity.this, Demo1Activity.class));
                break;
        }
    }
}
