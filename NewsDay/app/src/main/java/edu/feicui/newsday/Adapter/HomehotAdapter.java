package edu.feicui.newsday.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import java.util.List;
import java.util.Random;

import edu.feicui.newsday.R;
import edu.feicui.newsday.entity.HotNewsInfo;
import edu.feicui.newsday.entity.VolleySingleton;


/**
 * Created by 太上老君 on 2016/7/13.
 */
public class HomehotAdapter extends RecyclerView.Adapter<HomehotAdapter.MyViewHolder> {
    Context context;
    List<HotNewsInfo> list;

    public HomehotAdapter(Context context, List<HotNewsInfo> list) {
        this.context = context;
        this.list = list;
    }

    public List<HotNewsInfo> getList() {
        return list;
    }

    public void setList(List<HotNewsInfo> list) {
        this.list = list;
    }

    /**
     * 控件初始化
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_hot_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    /**
     * 绑数据源
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Random random = new Random();
        int width = random.nextInt(10)+30;
        int height = random.nextInt(70)+30;
        holder.relativeLayout.setMinimumHeight(height);
        holder.relativeLayout.setMinimumWidth(width);
        holder.textView.setText(list.get(position).getTitle());
        Log.i("mmmmm", list.size() + "aa22");

        ImageRequest request = new ImageRequest(list.get(position).getPic1(),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        holder.imageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        holder.imageView.setImageResource(R.mipmap.ic_launcher);//加载失败
                    }
                });
        VolleySingleton.getVolleySingleton(context.getApplicationContext()).addToRequestQueue(request);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 获得控件
     */
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        RelativeLayout relativeLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.homefra_hot_tv);
            imageView = (ImageView) itemView.findViewById(R.id.homefra_hot_iv);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.homefra_hot_rl);
        }
    }

}
