package com.custom.shadowview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/***
 * shadowRadius ：
 *   (1)支持各种颜色阴影,颜色必须带有透明度,否则阴影不准确; eg:"#1a3e4043"
 *   (2)当背景使用,阴影半径会占用原来布局的一部分空间，空间大小等于 shadowRadius
 *      如果 dx,dy为0，上下左右的阴影的高度等于shadowRadius,
 *      如果shadowRadius>dy>0 阴影向下倾斜,那么顶部的阴影高度等于：shadowRadius - dx,如果dy>= shadowRadius,那么上面的阴影高度等于0,下面阴影半径为 shadowRadius + dx;
 *      其他情况同理,方便在写布局的时候计算上下左右边距
 *   (3)支持4个方向是否设置阴影,ALL,LEFT,TOP,RIGHT,BOTTOM
 *   (4)支持圆角
 *
 *   eg:
 *        <com.custom.shadowview.ShadowView
             android:layout_centerInParent="true"
             android:layout_width="200dp"
             android:layout_height="100dp"
             app:shadowSide="all"
             app:shadowColor="#1aff9c17"
             app:roundRadius="4dp"
             app:shadowDx="0dp"
             app:shadowDy="5dp"
             app:shadowRadius="4dp"/>
 *
 *   @author liqiang10
 * **/

public class ShadowView extends View{

    public static final int ALL = 0x1111;

    public static final int LEFT = 0x0001;

    public static final int TOP = 0x0010;

    public static final int RIGHT = 0x0100;

    public static final int BOTTOM = 0x1000;

    /**
     * 阴影显示的边界
     */
    private int mShadowSide = ALL;
    
    private Paint mPaint;
    private RectF mRectF;
    private int mShadowRadius;
    private int mShadowColor;
    private int mShadowDx;
    private int mShadowDy;
    private float mRoundRadius;

    public ShadowView(Context context) {
        this(context,null);
    }

    public ShadowView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ShadowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ShadowLayout);
        if (typedArray != null) {
            mShadowColor = typedArray.getColor(R.styleable.ShadowLayout_shadowColor,
                    getContext().getResources().getColor(android.R.color.black));
            mShadowRadius = (int) typedArray.getDimension(R.styleable.ShadowLayout_shadowRadius, 0);
            mShadowDx = (int) typedArray.getDimension(R.styleable.ShadowLayout_shadowDx, 0);
            mShadowDy = (int) typedArray.getDimension(R.styleable.ShadowLayout_shadowDy, 0);
            mShadowSide = typedArray.getInt(R.styleable.ShadowLayout_shadowSide, ALL);
            mRoundRadius = typedArray.getDimension(R.styleable.ShadowLayout_roundRadius, 0);
            typedArray.recycle();
        }
        
        init();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int leftOffset = mShadowRadius - mShadowDx;
        int topOffset = mShadowRadius - mShadowDy;
        int rightOffset = mShadowRadius + mShadowDx;
        int bottomOffset = mShadowRadius + mShadowDy;

        int width = this.getMeasuredWidth();
        int height = this.getMeasuredHeight();

        if(mShadowSide == LEFT){
            topOffset = 0;
            rightOffset = 0;
            bottomOffset = 0;
        }

        if(mShadowSide  == TOP){
            leftOffset = 0;
            rightOffset = 0;
            bottomOffset = 0;
        }

        if(mShadowSide  == RIGHT){
            topOffset = 0;
            leftOffset = 0;
            bottomOffset = 0;
        }

        if(mShadowSide == BOTTOM){
            topOffset = 0;
            rightOffset = 0;
            leftOffset = 0;
        }

        mRectF.top = topOffset;
        mRectF.left = leftOffset;
        mRectF.right = width - rightOffset;
        mRectF.bottom = height - bottomOffset;
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint.setShadowLayer(mShadowRadius,mShadowDx,mShadowDy,mShadowColor);
        mRectF = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(mRectF,mRoundRadius,mRoundRadius,mPaint);
    }
}
