package com.zhangqie.customcontrol.demo3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.zhangqie.customcontrol.R;

/**
 * Created by zhangqie on 2018/5/3.
 */

public class Demo3Activity extends AppCompatActivity {

    TopTabToolView topTabToolView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo3);
        initView();
    }

    private void initView(){
        topTabToolView = (TopTabToolView) findViewById(R.id.tab1);
        topTabToolView.setTitle("代码设置标题");
        topTabToolView.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        topTabToolView.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Demo3Activity.this,"关闭",Toast.LENGTH_LONG).show();
            }
        });
    }
}
