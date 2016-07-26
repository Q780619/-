package edu.feicui.newsday.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
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

import edu.feicui.newsday.R;
import edu.feicui.newsday.entity.MyCollectInfo;
import edu.feicui.newsday.entity.VolleySingleton;


/**
 * Created by 太上老君 on 2016/7/21.
 */
public class MyCollectAdapter extends RecyclerView.Adapter<MyCollectAdapter.MyViewHolder>{
    Context context;
    List<MyCollectInfo> list;

    public MyCollectAdapter(Context context, List<MyCollectInfo> list) {
        this.context = context;
        this.list = list;
    }

    public List<MyCollectInfo> getList() {
        return list;
    }

    public void setList(List<MyCollectInfo> list) {
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
        holder.textView.setText(list.get(position).getTitle());
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
        if(recycleViewClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    recycleViewClickListener.OnItemClickListener(holder.itemView,pos );
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    recycleViewClickListener.OnItemLongClickListener(holder.itemView, pos);
                    return true;
                }
            });
        }
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
    private RecycleViewClickListener recycleViewClickListener = null;

    public RecycleViewClickListener getRecycleViewClickListener() {
        return recycleViewClickListener;
    }

    public void setRecycleViewClickListener(RecycleViewClickListener recycleViewClickListener) {
        this.recycleViewClickListener = recycleViewClickListener;
    }

    public interface RecycleViewClickListener{
        void OnItemClickListener(View view, int position);
        void OnItemLongClickListener(View view, int position);
    }
}
