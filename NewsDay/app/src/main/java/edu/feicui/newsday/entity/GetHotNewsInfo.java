package edu.feicui.newsday.entity;

import java.util.List;

/**
 * Created by 太上老君 on 2016/7/19.
 */
public class GetHotNewsInfo {
    private List<HotNewsInfo> Hotlist;

    private GetHotNewsInfo() {
    }
    private static GetHotNewsInfo getHotNewsInfo;
    public static GetHotNewsInfo getInstance(){
        if(getHotNewsInfo==null){
            getHotNewsInfo = new GetHotNewsInfo();
        }
        return getHotNewsInfo;
    }

    public List<HotNewsInfo> getHotlist() {
        return Hotlist;
    }

    public void setHotlist(List<HotNewsInfo> hotlist) {
        Hotlist = hotlist;
    }
}
