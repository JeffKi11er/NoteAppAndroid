package com.t3h.myprojectnoteupdate.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.t3h.myprojectnoteupdate.R;

import java.util.List;

public class AdapterNewEvent extends
        RecyclerView.Adapter<AdapterNewEvent.AddViewHolder> {
    List<String>list;
    String[]escri;

    public AdapterNewEvent(List<String> list) {
        this.list = list;
        escri = new String[list.size()];
    }

    @Override
    public AddViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.add_item,viewGroup,false);
        return new AddViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AddViewHolder addViewHolder, int i) {
        String product = list.get(i);
        addViewHolder.bindProduct(product);
    }

    public String[] getEscri() {
        return escri;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AddViewHolder extends RecyclerView.ViewHolder {
        EditText editText;
        TextView textView;
        public AddViewHolder(@NonNull View itemView) {
            super(itemView);
            editText = (EditText)itemView.findViewById(R.id.edt_element_row);
            textView = (TextView)itemView.findViewById(R.id.tv_element);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    escri[getAdapterPosition()] = s.toString();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        public void bindProduct(String product) {
            textView.setText(product);
        }
    }
}
