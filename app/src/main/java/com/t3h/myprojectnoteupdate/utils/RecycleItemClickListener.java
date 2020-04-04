package com.t3h.myprojectnoteupdate.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

public class RecycleItemClickListener implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener nlistener;
    private OnRecycleViewItemClickListener nrecyclerlistener;

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public interface OnRecycleViewItemClickListener{
        void onItemClick(View parentView, View childView, int position);
    }
    GestureDetector gestureDetector;

    public RecycleItemClickListener(Context context, OnItemClickListener listener) {
        nlistener = listener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    public RecycleItemClickListener(Context context, OnRecycleViewItemClickListener listener) {
        nrecyclerlistener = listener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }
    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        View childView = recyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());
        if (childView!=null&&nlistener!=null&&gestureDetector.onTouchEvent(motionEvent)){
            nlistener.onItemClick(childView,recyclerView.getChildPosition(childView));
        }else if(childView!=null&&nrecyclerlistener!=null&&gestureDetector.onTouchEvent(motionEvent)){
            nrecyclerlistener.onItemClick(recyclerView,childView,recyclerView.getChildPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
