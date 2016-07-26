package edu.feicui.newsday.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import java.util.List;

import edu.feicui.newsday.R;
import edu.feicui.newsday.entity.NewsInfo;
import edu.feicui.newsday.entity.VolleySingleton;


/**
 * Created by 太上老君 on 2016/7/12.
 */
public class Fragment_hotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<NewsInfo> list;
    Context context;
    LayoutInflater layoutInflater;

    public enum ITEM_TYPE{
        ITEM1,
        ITEM2;
    }
    public Fragment_hotAdapter(Context context, List<NewsInfo> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    public List<NewsInfo> getList() {
        return list;
    }

    public void setList(List<NewsInfo> list) {
        this.list = list;
    }

    /**
     * 控件初始化
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE.ITEM1.ordinal()){
            View view = LayoutInflater.from(context).inflate(R.layout.fra_item,parent,false);
            MyViewHolder myViewHolder1 = new MyViewHolder(view);
            return myViewHolder1;
        }else if(viewType == ITEM_TYPE.ITEM2.ordinal()){
            View view2 = LayoutInflater.from(context).inflate(R.layout.fra_hot_firstitem,parent,false);
            MyViewHolder2 myViewHolder2 = new MyViewHolder2(view2);
            return myViewHolder2;
        }
        return null;
    }

    /**
     * 绑数据源
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder)holder).title.setText(list.get(position).getTitle());
            ((MyViewHolder)holder).name.setText(list.get(position).getName());
            ((MyViewHolder)holder).desc.setText(list.get(position).getDesc());
            ImageRequest request = new ImageRequest(list.get(position).getPic(),
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            ((MyViewHolder)holder).iv.setImageBitmap(bitmap);
                        }
                    }, 0, 0, Bitmap.Config.RGB_565,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            ((MyViewHolder)holder).iv.setImageResource(R.mipmap.ic_launcher);//加载失败
                        }
                    });
            VolleySingleton.getVolleySingleton(context.getApplicationContext()).addToRequestQueue(request);
        } else if (holder instanceof MyViewHolder2) {
            ((MyViewHolder2)holder).firsttitle.setText(list.get(position).getTitle());
            ImageRequest request = new ImageRequest(list.get(position).getPic(),
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            ((MyViewHolder2)holder).firstiv.setImageBitmap(bitmap);
                        }
                    }, 0, 0, Bitmap.Config.RGB_565,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            ((MyViewHolder2)holder).firstiv.setImageResource(R.mipmap.ic_launcher);//加载失败
                        }
                    });
            VolleySingleton.getVolleySingleton(context.getApplicationContext()).addToRequestQueue(request);
        }
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

    @Override
    public int getItemViewType(int position) {
        //Enum类提供了一个ordinal()方法，返回枚举类型的序数，这里ITEM_TYPE.ITEM1.ordinal()代表0， ITEM_TYPE.ITEM2.ordinal()代表1
        return position  == 0 ? ITEM_TYPE.ITEM2.ordinal() : ITEM_TYPE.ITEM1.ordinal();
    }

    /**
     * 获得控件
     */

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title,name,desc;
        ImageView iv;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.hot_item_title_tv);
            name = (TextView) itemView.findViewById(R.id.hot_item_name_tv);
            desc = (TextView) itemView.findViewById(R.id.hot_item_date_tv);
            iv = (ImageView) itemView.findViewById(R.id.hot_item_iv);
        }
    }
    public static class MyViewHolder2 extends RecyclerView.ViewHolder{
        TextView firsttitle;
        ImageView firstiv;
        public MyViewHolder2(View itemView) {
            super(itemView);
            firsttitle = (TextView) itemView.findViewById(R.id.fra_hot_firstitem_tv);
            firstiv = (ImageView) itemView.findViewById(R.id.fra_hot_firstitem_iv);
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
