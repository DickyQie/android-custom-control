package com.zhangqie.customcontrol.demo4;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhangqie on 2018/5/3.
 */

public class FlowLayouta extends ViewGroup {


    public FlowLayouta(Context context) {
        super(context);
    }

    public FlowLayouta(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayouta(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 父容器生成 子view 的布局LayoutParams;
     * 一句话道出LayoutParams的本质：LayoutParams是Layout提供给其中的Children使用的。
     * 如果要自定义ViewGroup支持子控件的layout_margin参数
     * ，则自定义的ViewGroup类必须重载generateLayoutParams()函数，
     * 并且在该函数中返回一个ViewGroup.MarginLayoutParams派生类对象，这样才能使用margin参数。
     */
    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        // 每行的宽度，每行的高度
        int lineWidth = 0;
        int lineHeight = 0;
        // 初始化容器的高度，宽地
        int viewGroupHeight = 0;
        int viewGroupWidth = 0;
        // 获取子view的数量
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            // 获取每一个子view
            View child = getChildAt(i);
            // 测量子view
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            // 如果忘记重写generateLayoutParams，则hild.getLayoutParams()将不是MarginLayoutParams的实例
            // 在强制转换时就会出错，此时我们把左右间距设置为0，但由于在计算布局宽高时没有加上间距值，就是计算出的宽高要比实际小，所以是onLayout时就会出错
            MarginLayoutParams lp = (MarginLayoutParams) child
                    .getLayoutParams();
            // 每个子view的宽、高
            int childWidth = child.getMeasuredWidth() + lp.leftMargin
                    + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin
                    + lp.bottomMargin;

            /**
             * 换行后：当在显示下一个子view的时候，之前累加 的宽度+下个子view的宽度>测量的viewGroup的宽度，就需要换行显示
             */
            if (lineWidth + childWidth > measureWidth) {
                // 将容器的高度，进行累加，换行后
                viewGroupHeight += lineHeight;
                // 因为由于盛不下当前控件，而将此控件调到下一行，所以将此控件的高度和宽度初始化给lineHeight、lineWidth
                lineHeight = childHeight;
                lineWidth = childWidth;
            } else {
                // 每行的高度，需要在当前行的所有view取最大值
                lineHeight = Math.max(lineHeight, childHeight);
                // 拿第一行来说，在没有换行的情况下，每行的宽度，需要不断累加每个子view的宽度
                lineWidth += childWidth;
            }

            // 最后一行是不会超出width范围的，所以要单独处理
            if (i == count - 1) {
                viewGroupHeight += lineHeight;
                viewGroupWidth = Math.max(viewGroupWidth, lineWidth);
            }

        }
        // 当属性是MeasureSpec.EXACTLY时，那么它的高度就是确定的，
        // 只有当是wrap_content时，根据内部控件的大小来确定它的大小时，大小是不确定的，属性是AT_MOST,此时，就需要我们自己计算它的应当的大小，并设置进去
        setMeasuredDimension(
                (measureWidthMode == MeasureSpec.EXACTLY) ? measureWidth
                        : viewGroupWidth,
                (measureHeightMode == MeasureSpec.EXACTLY) ? measureHeight
                        : viewGroupHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int lineWidth = 0;
        int lineHeight = 0;
        int top = 0, left = 0;

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child
                    .getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin
                    + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin
                    + lp.bottomMargin;

            if (childWidth + lineWidth > getMeasuredWidth()) {
                // 如果换行,当前控件将跑到下一行，从最左边开始，所以left就是0，而top则需要加上上一行的行高，才是这个控件的top点;
                top += lineHeight;
                left = 0;
                // 同样，重新初始化lineHeight和lineWidth
                lineHeight = childHeight;
                lineWidth = childWidth;
            } else {
                lineHeight = Math.max(lineHeight, childHeight);
                lineWidth += childWidth;
            }
            // 计算childView的left,top,right,bottom
            int lc = left + lp.leftMargin;
            int tc = top + lp.topMargin;
            int rc = lc + child.getMeasuredWidth();
            int bc = tc + child.getMeasuredHeight();
            child.layout(lc, tc, rc, bc);
            // 将left置为下一子控件的起始点
            left += childWidth;
        }

    }
}
