package com.t3h.myprojectnoteupdate.activity;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.t3h.myprojectnoteupdate.AppConstants;
import com.t3h.myprojectnoteupdate.R;
import com.t3h.myprojectnoteupdate.adapter.AdapterEvent;
import com.t3h.myprojectnoteupdate.adapter.GridCalendarAdapter;
import com.t3h.myprojectnoteupdate.customview.CalendarView;
import com.t3h.myprojectnoteupdate.databinding.CalendarActivityLayoutBinding;
import com.t3h.myprojectnoteupdate.item.Item;
import com.t3h.myprojectnoteupdate.respository.NoteRespository;
import com.t3h.myprojectnoteupdate.utils.NavigatorUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener, AppConstants {
    private static final int MAX_CALENDAR_DAYS = 42;
    private final String TAG = "CalendarActivity";
    private ImageView imgclose;
    private TextView tvtitlebar,tvdone;
    //private CalendarView calendarView;
    private FloatingActionButton btnadd;
    private Item item;
    private LinearLayout calendar_header;
    private ImageButton button_prev;
    private ImageButton button_next;
    private TextView tv_date;
    private AdapterEvent adapterEvent;
    private NoteRespository noteRespository;
    private GridView calendar_table;
    private Calendar calendar = Calendar.getInstance();
    private List<Date> dates = new ArrayList<>();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private GridCalendarAdapter gridAdapter;
    private List<Item>itemsC;
    private List<Item>listitem;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity_layout);
        //itemsC=new ArrayList<>();
        listitem = new ArrayList<>();
        noteRespository = new NoteRespository(getApplicationContext());
        //convertdataforCalendar(itemsC);
        init();
        setUpCalendar();
        button_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH,-1);
                setUpCalendar();
            }
        });
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH,1);
                setUpCalendar();
            }
        });

    }
    private void setUpCalendar(){
//        convertdataforCalendar(itemsC);
        String currentDate = dateFormat.format(calendar.getTime());
        tv_date.setText(currentDate);
        dates.clear();
        Calendar monthCalendar = (Calendar)calendar.clone();
        monthCalendar.set(Calendar.DAY_OF_MONTH,1);
        int fistDayOfMonth = monthCalendar.get(Calendar.DAY_OF_WEEK)-2;
        monthCalendar.add(Calendar.DAY_OF_MONTH,-fistDayOfMonth);
//        ColectEventPermonth(itemsC,calendar.get(Calendar.MONTH),calendar.get(Calendar.YEAR));
        while (dates.size()<MAX_CALENDAR_DAYS){
            dates.add(monthCalendar.getTime());
            monthCalendar.add(Calendar.DAY_OF_MONTH,1);
        }
        gridAdapter = new GridCalendarAdapter(this,dates,calendar,listitem);
        calendar_table.setAdapter(gridAdapter);
//        convertdataforCalendar(this,dates,calendar,calendar.get(Calendar.MONTH),calendar.get(Calendar.YEAR));
    }
    private void init() {
        //calendarView = (CalendarView)findViewById(R.id.carlendar_custom_calender);
        tvtitlebar = findViewById(R.id.title);

        btnadd = findViewById(R.id.btn_add_calendar_event);
        btnadd.setOnClickListener(this);

        tvdone = findViewById(R.id.btn_done);

        imgclose = findViewById(R.id.btn_close);
        imgclose.setOnClickListener(this);
        //item = (Item)getIntent().getSerializableExtra(INTENT_TASK);
        tvtitlebar.setText(R.string.title_calendar);
        imgclose.setImageResource(R.drawable.ic_arrowback);
        imgclose.setTag(R.drawable.ic_arrowback);
        tvdone.setText("");
        calendar_header = findViewById(R.id.calender_header);
        button_next = findViewById(R.id.calendar_nextbutton);
        button_prev = findViewById(R.id.calendar_prevbutton);
        tv_date = findViewById(R.id.jv_date);
        calendar_table =findViewById(R.id.calender_days);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_close:
                setResult(Activity.RESULT_CANCELED);
                finish();
                overridePendingTransition(R.anim.stay,R.anim.slide_down);
                break;
            case R.id.btn_add_calendar_event:
                //NavigatorUtils.redirectToViewNoteScreen(this,item);
                Intent intent = new Intent(this,EditNewActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
//                intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
//                startActivityForResult(intent,2);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.stay,R.anim.slide_down);
                break;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }
//    @Override
//    protected int getLayoutId() {
//        return R.layout.calendar_activity_layout;
//    }
//    private void CollectEventPerMonth(int month, int year){
//        noteRespository.getTime(month,year).observe(this, new Observer<List<Item>>() {
//            @Override
//            public void onChanged(@Nullable List<Item> items) {
//                if (adapterEvent==null){
//                    adapterEvent = new AdapterEvent(items);
//                }
//            }
//        });
//    }
    private void convertdataforCalendar(final Context context, final List<Date>dates, final Calendar calendar, final int month, final int year){
        noteRespository.getTasks().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                List<Item>itemsList = new ArrayList<>();
                for (Item item:items){
                    if (item.getMonth()==month&&item.getYear()==year){
                        itemsList.add(item);
                    }
                }
                if (gridAdapter==null){
                    gridAdapter = new GridCalendarAdapter(context,dates,calendar,itemsList);
                    calendar_table.setAdapter(gridAdapter);
                }
                else {
                    gridAdapter.addTask(itemsList);
                }
            }
        });
    }
//    private void ColectEventPermonth(List<Item>items,int month,int year){
//        listitem.clear();
//        for (Item item : items){
//            if (item.getMonth()==month && item.getYear()==year){
//                listitem.add(item);
//            }
//        }
//    }
}
