package com.wyw.xiaowuweather.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wyw.xiaowuweather.R;

/**
 * 项目名称：XiaoWuWeather
 * 类描述： 通用的头部标题栏
 * 创建人：伍跃武
 * 创建时间：2016/11/30 15:05
 */
public class TitleBar extends FrameLayout {

    /**
     * 回退图标按钮
     */
    private ImageView ivBack;
    /**
     * 回退图标旁边描述文字
     */
    private TextView tvdescr;
    /**
     * 标题
     */
    private TextView tvMiddleTitle;
    /**
     * 右边的第一个图标
     */
    private ImageView ivRight;
    /**
     * 最靠右边的图标
     */
    private ImageView ivRightest;

    private LinearLayout ll_titlebar;


    private Activity _activity;


    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }

    protected
    @LayoutRes
    int getLayout() {
        return R.layout.common_titlebar;
    }


    /**
     * 初始化布局视图
     *
     * @param context
     * @param attrs
     * @return
     */
    private TitleBar initView(Context context, AttributeSet attrs) {

        LayoutInflater.from(context).inflate(getLayout(), this);
        if (context instanceof Activity) {
            _activity = (Activity) context;
        }
        instanceObjects(this);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
            int leftIcon = typedArray.getResourceId(R.styleable.TitleBar_leftIcon, R.mipmap.add_city);
            String feltDecrs = typedArray.getString(R.styleable.TitleBar_leftDecrs);
            String middleTitle = typedArray.getString(R.styleable.TitleBar_middleText);
            boolean isTransparent = typedArray.getBoolean(R.styleable.TitleBar_isTransparent, false);
            int right = typedArray.getResourceId(R.styleable.TitleBar_rigtIcon, R.mipmap.share_icon);
            int rightest = typedArray.getResourceId(R.styleable.TitleBar_rigtestIcon, R.mipmap.setting);

            setMiddleText(middleTitle);
            setRightText(feltDecrs);
            isBackgroundTransparent(isTransparent);
            setLeftBack(leftIcon);
            setRightIcon(right);
            setRightEastIcon(rightest);


        }
        return this;
    }

    /**
     * 设置标题栏透明
     *
     * @return
     */
    public TitleBar isBackgroundTransparent(boolean flag) {
        return flag ? setTitleBackgroundColor(getResources().getColor(android.R.color.transparent)) : this;
    }


    /**
     * 实例化对象
     *
     * @param titleBar
     */
    private void instanceObjects(TitleBar titleBar) {
        ll_titlebar = (LinearLayout) titleBar.findViewById(R.id.ll_titlebar);
        ivBack = (ImageView) titleBar.findViewById(R.id.iv_titlebar_left_back);
        tvdescr = (TextView) titleBar.findViewById(R.id.tv_titlebar_left_desc);
        tvMiddleTitle = (TextView) titleBar.findViewById(R.id.tv_titlebar_left_middletitle);
        ivRight = (ImageView) titleBar.findViewById(R.id.iv_titlebar_right);
        ivRightest = (ImageView) titleBar.findViewById(R.id.iv_titlebar_rightest);
    }

    /**
     * 设置标题
     *
     * @param middleText
     */
    public TitleBar setMiddleText(String middleText) {
        this.tvMiddleTitle.setVisibility(TextUtils.isEmpty(middleText) ? INVISIBLE : VISIBLE);
        this.tvMiddleTitle.setText(middleText);
        return this;
    }

    public TitleBar setRightText(String rightText) {
        this.tvdescr.setVisibility(TextUtils.isEmpty(rightText) ? INVISIBLE : VISIBLE);
        this.tvdescr.setText(rightText);
        return this;
    }

    public TitleBar setTitleBackgroundColor(int titleBackgroundColor) {
        ll_titlebar.setBackgroundColor(titleBackgroundColor);
        return this;
    }

    public TitleBar setLeftBack(int leftBack) {
        ivBack.setVisibility(leftBack <= 0 ? GONE : VISIBLE);
        ivBack.setBackgroundResource(leftBack);
        return this;
    }

    public TitleBar setRightIcon(int right) {
        ivRight.setVisibility(right <= 0 ? GONE : VISIBLE);
        ivRight.setBackgroundResource(right);
        return this;
    }

    public TitleBar setRightEastIcon(int leftBack) {
        ivBack.setVisibility(leftBack <= 0 ? GONE : VISIBLE);
        ivBack.setBackgroundResource(leftBack);
        return this;
    }
}
