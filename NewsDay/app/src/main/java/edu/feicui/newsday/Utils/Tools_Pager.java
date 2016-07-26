package edu.feicui.newsday.Utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.feicui.newsday.R;


/**
 * Created by 太上老君 on 2016/6/30.
 */
public class Tools_Pager {
    static ImageView iv;
    static TextView tv;

    public static void setPager( View view, int select_img,int unselect_img, String str, boolean select){
        iv = (ImageView) view.findViewById(R.id.typebar_iv);

        tv = (TextView) view.findViewById(R.id.typebar_tv);
        tv.setText(str);
        if(select==true){
            iv.setImageResource(select_img);
            tv.setTextColor(view.getResources().getColor(R.color.colortext_select));
        }else{
            iv.setImageResource(unselect_img);
            tv.setTextColor(view.getResources().getColor(R.color.colortext_unselect));
        }

    }
}
