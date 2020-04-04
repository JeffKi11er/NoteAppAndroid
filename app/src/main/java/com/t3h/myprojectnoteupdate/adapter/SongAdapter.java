package com.t3h.myprojectnoteupdate.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.t3h.myprojectnoteupdate.databinding.ItemSongBinding;
import com.t3h.myprojectnoteupdate.item.ItemSong;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private ArrayList<ItemSong> data;
    private LayoutInflater inflater;
    private ItemClickListener listener;

    public SongAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<ItemSong> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemSongBinding binding = ItemSongBinding.inflate(inflater);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.bindData(data.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onItemClicked(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemSongBinding binding;
        public ViewHolder(@NonNull ItemSongBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bindData(ItemSong song){
            binding.tvSongs.setText(song.getName());
        }
    }
    public interface ItemClickListener{
        void onItemClicked(int position);
    }
}
