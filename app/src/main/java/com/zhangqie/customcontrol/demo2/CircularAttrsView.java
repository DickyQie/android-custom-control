package com.zhangqie.customcontrol.demo2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.zhangqie.customcontrol.R;


public class CircularAttrsView extends View {


    /****
     * 有三个参数的构造函数中第三个参数是默认的Style，
     * 这里的默认的Style是指它在当前Application或Activity所用的Theme中的默认Style，且只有在明确调用的时候才会生效，
     */

    private final static String TAG = CircularAttrsView.class.getName();


    private Paint mPaint;
    private int backgroundColor = Color.GRAY;
    private float radius;
    private float width;
    private float height;

    private float centerX = 100;
    private float centerY = 100;
    public static final int LEFT = 0;
    public static final int TOP = 1;
    public static final int CENTER = 2;
    public static final int RIGHT = 3;
    public static final int BOTTOM = 4;


    private int gravity = CENTER;



    public CircularAttrsView(Context context) {
        super(context);
        init();
    }

    public CircularAttrsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams(context,attrs);
    }

    public CircularAttrsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams(context,attrs);
    }


    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    private void initParams(Context context,AttributeSet attrs){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        /***
         * 每一个属性集合编译之后都会对应一个styleable对象，通过styleable对象获取TypedArray typedArray，
         * 然后通过键值对获取属性值，这点有点类似SharedPreference的取法。
         */
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircularAttrsView);
        if (typedArray != null){
            try {
                backgroundColor = typedArray.getColor(R.styleable.CircularAttrsView_circular_background_color,Color.GRAY);
                radius = typedArray.getDimension(R.styleable.CircularAttrsView_circular_circle_radius,0);
                width = typedArray.getDimension(R.styleable.CircularAttrsView_circular_width,0);
                height = typedArray.getDimension(R.styleable.CircularAttrsView_circular_height,0);
                gravity = typedArray.getInt(R.styleable.CircularAttrsView_circular_circle_gravity,CENTER);
                Log.e(TAG,backgroundColor+"--"+radius+"--"+width+"--"+height+"--"+gravity);
                typedArray.recycle();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /****
     * 测量-Measure过程是计算视图大小
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //根据提供的测量值(格式)提取模式(三个模式之一)
        //MeasureSpec有3种模式分别是UNSPECIFIED, EXACTLY和AT_MOST,
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);  //取出宽度的测量模式
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);//获取View的大小(宽度的确切数值)

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        Log.i(TAG,"onMeasure---widthMode--->"+widthMode);
        switch (widthMode){
            case MeasureSpec.EXACTLY:

                break;
            case MeasureSpec.AT_MOST:

                break;
            case MeasureSpec.UNSPECIFIED:

              break;
        }
        Log.i(TAG,"onMeasure--widthSize--->"+ widthSize);
        Log.i(TAG,"onMeasure--heightMode-->"+ heightMode);
        Log.i(TAG,"onMeasure--heightSize-->"+heightSize);
    }


    /***
     * 确定View的大小(这个函数在视图大小发生改变时调用。)
     *
     * 宽度，高度，上一次宽度，上一次高度。
     * 只需关注 宽度(w), 高度(h) 即可，这两个参数就是View最终的大小。
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG,"onSizeChanged");
    }

    /****
     * 布局-Layout过程用于设置视图在屏幕中显示的位置,onLayout一般只会在自定义ViewGroup中才会使用
     *
     * 确定布局的函数是onLayout，它用于确定子View的位置，在自定义ViewGroup中会用到，他调用的是子View的layout函数。
     *
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i(TAG,"onLayout");
    }


    /***
     * 绘制-draw过程主要用于利用前两步得到的参数，将视图显示在屏幕上，到这里也就完成了整个的视图绘制工作
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(backgroundColor);
        // FILL填充, STROKE描边,FILL_AND_STROKE填充和描边
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        canvas.drawRoundRect(centerX,centerY,width,height,radius,radius,mPaint);
    }
}
