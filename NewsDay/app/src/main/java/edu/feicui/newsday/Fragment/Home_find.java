package edu.feicui.newsday.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.newsday.Activity.NewsActivity;
import edu.feicui.newsday.Adapter.HomeFindAdapter;
import edu.feicui.newsday.R;
import edu.feicui.newsday.Utils.MyExpress;
import edu.feicui.newsday.entity.MyCollectInfo;


/**
 * Created by 太上老君 on 2016/7/10.
 */
public class Home_find extends Fragment {
    View view;
    EditText editText;
    TextView textView;
    List<MyCollectInfo> list;
    MyExpress me;
    HomeFindAdapter fa;
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homefragment_find,null);
        getview();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setview();
    }
    private void getview(){
        list = new ArrayList<MyCollectInfo>();
        me = new MyExpress(getActivity());
        editText = (EditText) view.findViewById(R.id.find_et);
        listView = (ListView) view.findViewById(R.id.home_find_lv);
    }
    private void setview(){
        editText.addTextChangedListener(tw);

    }
    private TextWatcher tw=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(s.length()>0){//判断长度大于0，则转换
                String str=s.toString();//转换输入框为String 类型
                list=me.getData(str);//获取查询结果
                handler.sendEmptyMessage(0);//发送
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    fa = new HomeFindAdapter(list,getActivity());
                    listView.setAdapter(fa);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            MyCollectInfo myCollectInfo = list.get(position);
                            Intent intent = new Intent(getActivity(), NewsActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("skip", myCollectInfo);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };
}
