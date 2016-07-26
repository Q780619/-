package edu.feicui.newsday.Utils;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 太上老君 on 2016/7/17.
 */
public class GetState {
    static SaveMessage saveMessage;
    static List<String> tablist;
    public static List<String> getstate(Context context){
        saveMessage = new SaveMessage(context);
        tablist = new ArrayList<String>();
        List<String> liststate = saveMessage.addActivityload();
        for(int i = 0;i<liststate.size();i++){
            if(liststate.get(i).equals("true")) {
                Log.i("mmssss",i+" qqqqq");
                switch (i) {
                    case 0:
                        tablist.add("社会");
                        break;
                    case 1:
                        tablist.add("娱乐");
                        break;
                    case 2:
                        tablist.add("国内");
                        break;
                    case 3:
                        tablist.add("时尚");
                        break;
                    case 4:
                        tablist.add("体育");
                        break;
                    case 5:
                        tablist.add("军事");
                        break;
                }
            }
        }
        return tablist;
    }
}
