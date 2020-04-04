package com.t3h.myprojectnoteupdate.customview;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract.Events;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.t3h.myprojectnoteupdate.R;
import com.t3h.myprojectnoteupdate.activity.EditNewActivity;
import com.t3h.myprojectnoteupdate.adapter.GridCalendarAdapter;
import com.t3h.myprojectnoteupdate.adapter.GridCalendarAdapter;
import com.t3h.myprojectnoteupdate.item.Item;
import com.t3h.myprojectnoteupdate.respository.NoteRespository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarView extends LinearLayout {
    private static final int MAX_CALENDAR_DAYS = 42;
    public static final String REQUEST_EXTRA_DATE = "extra.date";
    Context context;
    LinearLayout calendar_header;
    ImageButton button_prev;
    ImageButton button_next;
    TextView tv_day;
    TextView tv_date;
    TextView tv_year;
    Button button_today;
    GridView calendar_table;
    List<Date>dates = new ArrayList<>();
    List<Item>items = new ArrayList<>();
    GridCalendarAdapter mygridAdapter;
    AlertDialog alertDialog;
    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public CalendarView(Context context) {
        super(context);
    }

    public CalendarView(final Context context, @Nullable AttributeSet atts) {
        super(context,atts);
        this.context=context;
        init();
        setUpCalendar();

        button_prev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH,-1);
                setUpCalendar();
            }
        });
        button_next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH,1);
                setUpCalendar();
            }
        });
//        calendar_table.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(context, EditNewActivity.class);
//                String currentdate = simpleDateFormat.format(calendar.getTime());
//                intent.putExtra(currentdate,REQUEST_EXTRA_DATE);
//                context.startActivity(intent);
//            }
//        });
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calendar_activity,this);
        calendar_header = view.findViewById(R.id.calender_header);
        button_prev = view.findViewById(R.id.calendar_prevbutton);
        button_next = view.findViewById(R.id.calendar_nextbutton);
//        tv_day = findViewById(R.id.jv_day);
        tv_date = view.findViewById(R.id.jv_date);
//        tv_year = findViewById(R.id.jv_year);
//        button_today = findViewById(R.id.jv_display_today);
        calendar_table = view.findViewById(R.id.calender_days);

//        button_today.setOnClickListener(this);

    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy");
    private void setUpCalendar() {
        String currentDate = dateFormat.format(calendar.getTime());
        tv_date.setText(currentDate);
        dates.clear();
        Calendar monthCalendar = (Calendar)calendar.clone();
        ///clone mot dang calendar
        ///set gia tri cua clone la 1
        ///tim vi tri cua ngay bat dau trong thang roi dien vao cac ngay cua cac thang truoc do
        ///sau do dien vao thang hien tai va tuong lai
        monthCalendar.set(Calendar.DAY_OF_MONTH,1);
        int fistDayOfMonth = monthCalendar.get(Calendar.DAY_OF_WEEK)-2;
        monthCalendar.add(Calendar.DAY_OF_MONTH,-fistDayOfMonth);
        while (dates.size()<MAX_CALENDAR_DAYS){
            dates.add(monthCalendar.getTime());
            monthCalendar.add(Calendar.DAY_OF_MONTH,1);
        }
        mygridAdapter = new GridCalendarAdapter(context,dates,calendar,items);
        calendar_table.setAdapter(mygridAdapter);
    }
}
