package edu.feicui.newsday.entity;

import java.util.List;

/**
 * Created by 太上老君 on 2016/7/19.
 */
public class GetFragmentTitle {
    private List<String> title_list;

    private GetFragmentTitle() {
    }
    private static GetFragmentTitle getFragmentTitle;
    public static GetFragmentTitle getInstance(){
        if(getFragmentTitle==null){
            getFragmentTitle = new GetFragmentTitle();
        }
        return getFragmentTitle;
    }

    public List<String> getTitle_list() {
        return title_list;
    }

    public void setTitle_list(List<String> title_list) {
        this.title_list = title_list;
    }
}
