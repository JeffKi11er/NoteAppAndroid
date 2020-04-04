package com.t3h.myprojectnoteupdate.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.t3h.myprojectnoteupdate.R;
import com.t3h.myprojectnoteupdate.item.Item;
import com.t3h.myprojectnoteupdate.item.ItemColor;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<ItemColor> data;
    private ColorListener listener;
    public ColorAdapter(Context context, ArrayList<ItemColor>data){
        this.data = data;
        inflater = LayoutInflater.from(context);
    }
    public void setListener(ColorListener listener){
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = inflater.inflate(R.layout.item_color,viewGroup,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
        ItemColor color = data.get(i);
        viewHolder.bindData(color);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data==null ? 0 : data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView colorPut;
        private TextView tvColor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            colorPut = itemView.findViewById(R.id.color_put);
            tvColor = itemView.findViewById(R.id.tv_color);
        }
        public void bindData(ItemColor itemColor){
            colorPut.setImageResource(itemColor.getColor());
            tvColor.setText(itemColor.getColorName());
        }
    }
    public interface ColorListener{
        void onClick(int position);
    }
}
