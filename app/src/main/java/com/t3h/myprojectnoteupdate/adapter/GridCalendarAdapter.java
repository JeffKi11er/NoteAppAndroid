package com.t3h.myprojectnoteupdate.adapter;

import android.content.Context;
import android.graphics.Color;
import android.provider.CalendarContract.Events;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.ListUpdateCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.t3h.myprojectnoteupdate.R;
import com.t3h.myprojectnoteupdate.item.Item;
import com.t3h.myprojectnoteupdate.utils.NoteDistinc;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GridCalendarAdapter extends ArrayAdapter {

    List<Date>dates;
    Calendar currentDate;
    List<Item>events;
    LayoutInflater inflater;

    public GridCalendarAdapter(@NonNull Context context, List<Date>dates,Calendar currentDate, List<Item>events) {
        super(context, R.layout.cell_calendar_layout);
        this.dates = dates;
        this.currentDate = currentDate;
        this.events = events;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return dates.indexOf(item);
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return dates.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Date monthDate = dates.get(position);
        Calendar datecalendar = Calendar.getInstance();
        datecalendar.setTime(monthDate);
        int DayNumber = datecalendar.get(Calendar.DAY_OF_MONTH);
        int displaymonth = datecalendar.get(Calendar.MONTH)+1;
        int currentmonth = currentDate.get(Calendar.MONTH)+1;
        int currentyear = currentDate.get(Calendar.YEAR);
        int displayyear = datecalendar.get(Calendar.YEAR);
        if (view==null){
            view = inflater.inflate(R.layout.cell_calendar_layout,parent,false);
        }
        TextView numberOfDay = view.findViewById(R.id.calendar_day);
        numberOfDay.setText(String.valueOf(DayNumber));
        if (displaymonth==currentmonth && displayyear == currentyear){
            numberOfDay.setTextColor(getContext().getResources().getColor(R.color.white));
            //view.setBackgroundColor(getContext().getResources().getColor(R.color.white));
        }
        else{
            numberOfDay.setTextColor(Color.parseColor("#ffab91"));
           // view.setBackgroundColor(Color.parseColor("#cccccc"));
        }


        return view;
    }
    public void addTask(List<Item>newitems){
        NoteDistinc noteDistinc = new NoteDistinc(events,newitems);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(noteDistinc);
        events.clear();
        events.addAll(newitems);
        diffResult.dispatchUpdatesTo((ListUpdateCallback) this);
    }

}
