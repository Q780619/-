package edu.feicui.newsday.Base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by 太上老君 on 2016/7/10.
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout();
        getview();
        setview();
    }

    public abstract void setLayout();
    public abstract void getview();
    public abstract void setview();
}
