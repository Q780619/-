package edu.feicui.newsday.entity;

import java.util.List;

/**
 * Created by 太上老君 on 2016/7/21.
 */
public class MyCollect {
    private List<MyCollectInfo> list;

    private MyCollect() {

    }
    private static MyCollect myCollect;
    public static MyCollect getInstance(){
        if(myCollect == null){
            myCollect = new MyCollect();
        }
        return myCollect;
    }

    public List<MyCollectInfo> getList() {
        return list;
    }

    public void setList(List<MyCollectInfo> list) {
        this.list = list;
    }
}
