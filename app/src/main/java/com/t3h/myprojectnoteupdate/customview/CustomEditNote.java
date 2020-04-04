package com.t3h.myprojectnoteupdate.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.t3h.myprojectnoteupdate.R;
import com.t3h.myprojectnoteupdate.adapter.AdapterNewEvent;
import com.t3h.myprojectnoteupdate.adapter.AdapterNewEvent;

import java.util.Arrays;
import java.util.List;

public class CustomEditNote extends ConstraintLayout {
    Context context;
    RecyclerView recyclerView;
    List<String>list = Arrays.asList("   ","   ","   ","   ","   ","   "
            ,"   ","   ","   ","   ","   ","   ");
    AdapterNewEvent adapterNewEvent;
    boolean band = true;
    public CustomEditNote(Context context) {
        super(context);
    }

    public CustomEditNote(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.add_newevent_list,this);
        recyclerView = view.findViewById(R.id.rcl_event_add);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(llm);

        adapterNewEvent = new AdapterNewEvent(list);
        recyclerView.setAdapter(adapterNewEvent);

    }
    public void taptap(View view){
    }


    public CustomEditNote(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    
}
