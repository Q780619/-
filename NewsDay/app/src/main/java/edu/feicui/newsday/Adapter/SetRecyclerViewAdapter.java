package edu.feicui.newsday.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.feicui.newsday.R;

/**
 * Created by 太上老君 on 2016/7/24.
 */
public class SetRecyclerViewAdapter extends RecyclerView.Adapter<SetRecyclerViewAdapter.MyViewHolder> {
    List<Integer> list;
    Context context;

    public SetRecyclerViewAdapter(List<Integer> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.setitem, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.textView.setBackgroundResource(list.get(position));
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

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.setitem_tv);
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
