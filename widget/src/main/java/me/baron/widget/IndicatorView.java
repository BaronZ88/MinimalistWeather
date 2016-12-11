
package me.baron.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/12/11
 */
public class IndicatorView extends LinearLayout {

    private Context context;
    private Paint paint;
    private Bitmap marker = null;

    private int indicatorValue = 0;// 默认AQI值
    private int textSize = 12;// 默认文字大小
    private int intervalValue = 1;// TextView之间的间隔大小，单位dp
    private int textColorId = R.color.indicator_text_color;// 默认文字颜色
    private int textColor;
    private int indicatorStringsResourceId = R.array.indicator_strings;
    private int indicatorColorsResourceId = R.array.indicator_colors;

    private int indicatorViewWidth;// IndicatorView宽度
    private int indicatorViewHeight;// IndicatorView高度

    private int paddingTopInXML;

    private String[] indicatorStrings;
    int[] indicatorColorIds;

    public IndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * 控件初始化，构造函数调用
     */
    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        this.setOrientation(LinearLayout.HORIZONTAL);
        //开启绘图缓存，提高绘图效率
        this.setDrawingCacheEnabled(true);

        initPaint();
        initAttrs(attrs);
        fillViewToParent(context);

        this.setWillNotDraw(false);// 确保onDraw()被调用

        this.paddingTopInXML = this.getPaddingTop();
        this.setPadding(this.getPaddingLeft() + this.marker.getWidth() / 2,
                this.getPaddingTop() + this.marker.getHeight() / 4 * 3,
                this.getPaddingRight() + this.marker.getWidth() / 2,
                this.getPaddingBottom());
    }

    /**
     * 初始化paint
     */
    private void initPaint() {
        this.paint = new Paint();
        // 设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
        this.paint.setAntiAlias(true);
        // 设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        this.paint.setDither(true);
    }

    /**
     * 获取自定义attrs
     */
    private void initAttrs(AttributeSet attrs) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        this.textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, textSize, dm);
        this.intervalValue = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, intervalValue, dm);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IndicatorView);
        int markerId = typedArray.getResourceId(R.styleable.IndicatorView_marker, R.drawable.housekeeper_hotdegree_mark);
        this.marker = BitmapFactory.decodeResource(context.getResources(), markerId);
        this.indicatorValue = typedArray.getInt(R.styleable.IndicatorView_indicatorValue, indicatorValue);
        this.textSize = typedArray.getDimensionPixelSize(R.styleable.IndicatorView_textSize, textSize);
        this.intervalValue = typedArray.getDimensionPixelSize(R.styleable.IndicatorView_intervalSize, intervalValue);
        this.textColor = typedArray.getColor(R.styleable.IndicatorView_textColor, getResources().getColor(textColorId));
        this.indicatorStringsResourceId = typedArray.getInt(R.styleable.IndicatorView_indicatorStrings, indicatorStringsResourceId);
        this.indicatorColorsResourceId = typedArray.getInt(R.styleable.IndicatorView_indicatorColors, indicatorColorsResourceId);
        typedArray.recycle();
    }

    /**
     * 想父容器中填充View
     */
    private void fillViewToParent(Context context) {
        String[] indicatorStrings = context.getResources().getStringArray(indicatorStringsResourceId);
        int[] indicatorColorIds = context.getResources().getIntArray(indicatorColorsResourceId);
        if (indicatorStrings.length != indicatorColorIds.length) {
            throw new IllegalArgumentException("qualities和aqiColors的数组长度不一致！");
        }
        for (int i = 0; i < indicatorStrings.length; i++) {
            addTextView(context, indicatorStrings[i], indicatorColorIds[i]);
            if (i != (indicatorStrings.length - 1)) {
                addBlankView(context);
            }
        }
    }

    /**
     * 向父容器中添加TextView
     *
     * @param text  TextView显示文字
     * @param color TextView的背景颜色，如："#FADBCC"
     */
    private void addTextView(Context context, String text, int color) {
        TextView textView = new TextView(context);
        textView.setBackgroundColor(color);
        textView.setText(text);
        textView.setTextColor(textColor);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        textView.setSingleLine();
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0F));
        this.addView(textView);
    }

    /**
     * 向父容器中添加空白View
     */
    private void addBlankView(Context context) {
        View transparentView = new View(context);
        transparentView.setBackgroundColor(Color.TRANSPARENT);
        transparentView.setLayoutParams(new LayoutParams(intervalValue, LayoutParams.WRAP_CONTENT));
        this.addView(transparentView);
    }

    /**
     * 一个MeasureSpec封装了父布局传递给子布局的布局要求，每个MeasureSpec代表了一组宽度和高度的要求。
     * 一个MeasureSpec由大小和模式组成
     * 它有三种模式：UNSPECIFIED(未指定),父元素不对子元素施加任何束缚，子元素可以得到任意想要的大小;
     * EXACTLY(完全)，父元素决定自元素的确切大小，子元素将被限定在给定的边界里而忽略它本身大小；
     * AT_MOST(至多)，子元素至多达到指定大小的值。
     * <p/>
     * 　　它常用的三个函数：
     * 1.static int getMode(int measureSpec):根据提供的测量值(格式)提取模式(上述三个模式之一)
     * 2.static int getSize(int measureSpec):根据提供的测量值(格式)提取大小值(这个大小也就是我们通常所说的大小)
     * 3.static int makeMeasureSpec(int size,int mode):根据提供的大小值和模式创建一个测量值(格式)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int desiredWidth = this.getChildAt(0).getHeight() + getPaddingLeft() + getPaddingRight();
        int desiredHeight = this.getChildAt(0).getHeight() + getPaddingTop() + getPaddingBottom();

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        this.indicatorViewWidth = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        this.indicatorViewHeight = MeasureSpec.getSize(heightMeasureSpec);

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            indicatorViewWidth = indicatorViewWidth;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            indicatorViewWidth = Math.min(desiredWidth, indicatorViewWidth);
        } else {
            //Be whatever you want
            indicatorViewWidth = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            indicatorViewHeight = indicatorViewHeight;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            indicatorViewHeight = Math.min(desiredHeight, indicatorViewHeight);
        } else {
            //Be whatever you want
            indicatorViewHeight = desiredHeight;
        }

        //Must call this
        setMeasuredDimension(indicatorViewWidth, indicatorViewHeight);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        drawMarkView(canvas);
    }

    /**
     * 用于绘制标示市场热度的图标
     */
    private void drawMarkView(Canvas canvas) {

        int width = (this.indicatorViewWidth - this.getPaddingLeft() - this.getPaddingRight()) / 500;
        int left = this.getPaddingLeft();
        if (indicatorValue >= 0 && indicatorValue < 100) {
            left += indicatorValue * width;
        } else if (indicatorValue >= 100 && indicatorValue < 200) {
            left += indicatorValue * width + intervalValue;
        } else if (indicatorValue >= 200 && indicatorValue < 300) {
            left += indicatorValue * width + intervalValue * 2;
        } else if (indicatorValue >= 300 && indicatorValue < 400) {
            left += indicatorValue * width + intervalValue * 3;
        } else if (indicatorValue >= 400 && indicatorValue < 500) {
            left += indicatorValue * width + intervalValue * 4;
        } else if (indicatorValue >= 500) {
            left += width;
        }
        canvas.drawBitmap(marker, left - marker.getWidth() / 2 - 2, this.paddingTopInXML, paint);
    }

    private IndicatorValueChangeListener indicatorValueChangeListener;

    public void setIndicatorValueChangeListener(IndicatorValueChangeListener indicatorValueChangeListener) {
        this.indicatorValueChangeListener = indicatorValueChangeListener;
    }

    public void setIndicatorValue(int indicatorValue) {

        if (indicatorValue < 0)
            throw new IllegalStateException("参数indicatorValue必须大于0");

        this.indicatorValue = indicatorValue;
        if (indicatorValueChangeListener != null) {
            String desc;
            if (indicatorValue < 100) {
                desc = indicatorStrings[0];
            } else if (indicatorValue >= 100 && indicatorValue < 200) {
                desc = indicatorStrings[1];
            } else if (indicatorValue >= 200 && indicatorValue < 300) {
                desc = indicatorStrings[2];
            } else if (indicatorValue >= 300 && indicatorValue < 400) {
                desc = indicatorStrings[3];
            } else {
                desc = indicatorStrings[4];
            }
            indicatorValueChangeListener.onChange(this.indicatorValue, desc);
        }
        invalidate();
    }
}
