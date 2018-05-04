package com.zhangqie.customcontrol.demo4;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhangqie.customcontrol.R;

import java.util.Random;

import static com.zhangqie.customcontrol.demo4.Util.dip2px;

/**
 * Created by zhangqie on 2018/5/3.
 */

public class Demo4_2Activity extends AppCompatActivity {



    private FlowLayoutb flowLayout;
    private String[] data={"Android","Android ViewGroup流式布局","Android 编程","zq","我是ViewGroup",
            "Android 自定义控件 之 ViewGroup流式布局","Android自定义控件","华为","我的小宝贝",
            "切切歆语","自定义控件博客","自定义控件","Android View","小米","vivo","textView可以被点击"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo4_2);
        initView();
    }

    private void initView(){
        LinearLayout root = (LinearLayout) findViewById(R.id.root);
        flowLayout = new FlowLayoutb(this);
        int padding=dip2px(13,this);
        flowLayout.setPadding(padding, padding, padding, padding);
        int backColor = 0xffcecece;
        Drawable pressedDrawable = createShape(backColor);// 按下显示的图片


        for (int i = 0; i < data.length; i++) {

            TextView textView = new TextView(this);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            final String str = data[i];
            textView.setText(str);
            Random random = new Random();   //创建随机
            int red = random.nextInt(200) + 22;
            int green = random.nextInt(200) + 22;
            int blue = random.nextInt(200) + 22;
            int color = Color.rgb(red, green, blue);//随机颜色
            GradientDrawable createShape = createShape(color); // 默认显示的图片
            StateListDrawable createSelectorDrawable = createSelectorDrawable(pressedDrawable, createShape);// 创建状态选择器
            textView.setBackgroundDrawable(createSelectorDrawable);
            textView.setTextColor(Color.WHITE);
            int textPaddingV = dip2px(4, this);
            int textPaddingH = dip2px(7, this);
            textView.setPadding(textPaddingH, textPaddingV, textPaddingH, textPaddingV); //设置padding
            textView.setClickable(true);//设置textView可以被点击
            textView.setOnClickListener(new View.OnClickListener() {  // 设置点击事件

                @Override
                public void onClick(View v) {
                    Toast.makeText(Demo4_2Activity.this, str, Toast.LENGTH_SHORT).show();
                }
            });
            flowLayout.addView(textView,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, -2));// -2 包裹内容
        }
        root.addView(flowLayout);
    }


    public GradientDrawable createShape(int color) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(dip2px(5, this));//设置4个角的弧度
        drawable.setColor(color);// 设置颜色
        return drawable;


    }

    public StateListDrawable createSelectorDrawable(Drawable pressedDrawable, Drawable normalDrawable) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);// 按下显示的图片
        stateListDrawable.addState(new int[]{}, normalDrawable);// 抬起显示的图片
        return stateListDrawable;

    }

}
