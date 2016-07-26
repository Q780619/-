package edu.feicui.GitDroid.Fragment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import edu.feicui.GitDroid.R;

/**
 * Created by 太上老君 on 2016/7/27.
 */
public class Fratwo extends FrameLayout{
    public Fratwo(Context context) {
        this(context,null);
    }

    public Fratwo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Fratwo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_1,this,true);
    }
}
