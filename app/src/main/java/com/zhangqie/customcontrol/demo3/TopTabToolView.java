package com.zhangqie.customcontrol.demo3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhangqie.customcontrol.R;

/**
 * Created by zhangqie on 2018/5/3.
 *
 * 自定义一个View根据需求继承不同的ViewGroup子类，
 * 比如：RelativeLayout、LinearLayout等，我们这里继承RelativeLayout。
 *
 */

public class TopTabToolView extends RelativeLayout {

    private ImageView titleBarLeftImg;
    private Button titleBarRightBtn;
    private TextView titleBarTitle;

    public TopTabToolView(Context context) {
        super(context);
    }

    public TopTabToolView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.tab_tool_layout,this,true);
        titleBarLeftImg = (ImageView)findViewById(R.id.title_tab_left);
        titleBarTitle = (TextView)findViewById(R.id.title_tab_title);
        titleBarRightBtn = (Button)findViewById(R.id.title_tab_right);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.TopTabToolView);
        if (typedArray != null){

            //背景设置
            int titleBarBackGround = typedArray.getColor(R.styleable.TopTabToolView_tab_background_color, Color.WHITE);
            setBackgroundColor(titleBarBackGround);

            //-------------------------标题栏左边----------------------
            boolean leftImgVisible = typedArray.getBoolean(R.styleable.TopTabToolView_left_tab_visible,true);
            if (leftImgVisible){
                titleBarLeftImg.setVisibility(VISIBLE);
            }else {
                titleBarLeftImg.setVisibility(GONE);
            }
            //设置图标
            int leftTabDrawble = typedArray.getResourceId(R.styleable.TopTabToolView_left_tab_drawable,-1);
            if (leftTabDrawble != -1){
                titleBarLeftImg.setImageResource(leftTabDrawble);
            }


            //--------------------------中间标题-----------------------
            String titleText = typedArray.getString(R.styleable.TopTabToolView_title_text);
            if (!TextUtils.isEmpty(titleText)){
                titleBarTitle.setText(titleText);
                //设置字体颜色
                int titleTextColor = typedArray.getColor(R.styleable.TopTabToolView_title_color,Color.WHITE);
                titleBarTitle.setTextColor(titleTextColor);
            }



            //------------------------标题栏右边-------------------------
            boolean rightButtonVisible = typedArray.getBoolean(R.styleable.TopTabToolView_right_tab_visible,true);
            if (rightButtonVisible){
                titleBarRightBtn.setVisibility(VISIBLE);
            }else {
                titleBarRightBtn.setVisibility(INVISIBLE);
            }

            //设置文字
            String rightBtnText = typedArray.getString(R.styleable.TopTabToolView_right_tab_text);
            if (!TextUtils.isEmpty(rightBtnText)){
                titleBarRightBtn.setText(rightBtnText);
                int rightBtnTextColor = typedArray.getColor(R.styleable.TopTabToolView_right_tab_text_color,Color.WHITE);
                titleBarRightBtn.setTextColor(rightBtnTextColor);
            }

            //设置图标
            int rightBtnDrawable = typedArray.getResourceId(R.styleable.TopTabToolView_right_tab_drawable,-1);
            if (rightBtnDrawable != -1){
                titleBarRightBtn.setCompoundDrawablesWithIntrinsicBounds(0,0,rightBtnDrawable,0);
            }

            typedArray.recycle();
        }
    }

    public TopTabToolView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置标题
     * @param title
     */
    public void setTitle(String title){
        if (!TextUtils.isEmpty(title)){
            titleBarTitle.setText(title);
        }
    }

    /***
     * 左边点击
     * @param onClickListener
     */
    public void setLeftOnClickListener(OnClickListener onClickListener){
        if (onClickListener != null){
            titleBarLeftImg.setOnClickListener(onClickListener);
        }
    }


    /***
     * 右边点击
     * @param onClickListener
     */
    public void setRightOnClickListener(OnClickListener onClickListener){
        if (onClickListener != null){
            titleBarRightBtn.setOnClickListener(onClickListener);
        }
    }



}
