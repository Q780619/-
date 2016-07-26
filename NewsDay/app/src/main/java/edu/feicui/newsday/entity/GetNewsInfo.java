package edu.feicui.newsday.entity;

import java.util.List;

/**
 * Created by 太上老君 on 2016/7/15.
 */
public class GetNewsInfo {
    private List<NewsInfo> list;

    private GetNewsInfo() {

    }
    private static GetNewsInfo getNewsInfo;
    public static GetNewsInfo getInstance(){
        if(getNewsInfo == null){
            getNewsInfo = new GetNewsInfo();
        }
        return getNewsInfo;
    }

    public List<NewsInfo> getList() {
        return list;
    }

    public void setList(List<NewsInfo> list) {
        this.list = list;
    }
}
