package com.t3h.myprojectnoteupdate.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.t3h.myprojectnoteupdate.AppConstants;
import com.t3h.myprojectnoteupdate.R;
import com.t3h.myprojectnoteupdate.adapter.AdapterEvent;
import com.t3h.myprojectnoteupdate.databinding.ActivityMainBinding;
import com.t3h.myprojectnoteupdate.item.Item;
import com.t3h.myprojectnoteupdate.receiver.MyAlarm;
import com.t3h.myprojectnoteupdate.respository.NoteRespository;
import com.t3h.myprojectnoteupdate.utils.AppUtils;
import com.t3h.myprojectnoteupdate.utils.NavigatorUtils;
import com.t3h.myprojectnoteupdate.utils.RecycleItemClickListener;
import com.t3h.myprojectnoteupdate.utils.TimestampConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,RecycleItemClickListener.OnRecycleViewItemClickListener, AppConstants {
    private final String TAG = "MainActivity";
//    public static final String REQUEST_DATE = "item.date";
//    public static final String REQUEST_TIME = "item.time";
//    public static final String REQUEST_TRUE = "string.boolean";
//    public static final String REQUEST_TITLE = "item.title";
//    public static final String REQUEST_DESCRIPT = "item.description";
//    private CurrentEventFragment currentEventFragment = new CurrentEventFragment();
    boolean right = true;
    private Item item;
    private EditText editSearch;
    private ImageView btnCalendar;
    private RecyclerView recylerlist;
    private AdapterEvent adapterEvent;
    private FloatingActionButton actionButton;
    private NoteRespository noteRespository;
    private List<Item>itemsC;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG,"MainActivity");
        setContentView(R.layout.activity_main);
        noteRespository = new NoteRespository(getApplicationContext());
        recylerlist = findViewById(R.id.lv_event_current);
        recylerlist.addOnItemTouchListener(new RecycleItemClickListener(this, this));
        actionButton = findViewById(R.id.btn_addeventcurrent);
        actionButton.setOnClickListener(this);
        editSearch = findViewById(R.id.edt_search);
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
        btnCalendar = findViewById(R.id.btn_calendar);
        btnCalendar.setOnClickListener(this);
        itemsC = new ArrayList<>();
        
        updateTaskList();
    }

    private void filter(final String text) {
        noteRespository.getTasks().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                ArrayList<Item>filteredList = new ArrayList<>();
                for (Item item: items){
                    if (item.getTitle().toLowerCase().contains(text.toLowerCase())){
                        filteredList.add(item);
                    }
                }
                adapterEvent.filterList(filteredList);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addeventcurrent:
                Intent intent = new Intent(this,EditNewActivity.class);
                startActivityForResult(intent,ACTIVITY_REQUEST_CODE);
            break;
            case R.id.btn_calendar:
                Intent intent1 = new Intent(this,CalendarActivity.class);
//                intent1.putExtra(INTENT_TASK,item);
                startActivityForResult(intent1,ACTIVITY_REQUEST_CODE);
                break;
        }
    }

    @Override
    public void onItemClick(View parentView, View childView, int position) {
        Item item = adapterEvent.getItem(position);
        NavigatorUtils.redirectToEditTaskScreen(this,item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==ACTIVITY_REQUEST_CODE&&resultCode== Activity.RESULT_OK){
            if (data.hasExtra(INTENT_TASK)){
                if (data.hasExtra(INTENT_DELETE)){
                    noteRespository.deleteTask((Item)data.getSerializableExtra(INTENT_TASK));
                }else {
                    noteRespository.updateTask((Item)data.getSerializableExtra(INTENT_TASK));
                }

            }
            else {
                String title = data.getStringExtra(INTENT_TITLE);
                String desc = data.getStringExtra(INTENT_DESCRIPT);
                String date = data.getStringExtra(INTENT_DAY);
                String month = data.getStringExtra(INTENT_MONTH);
                String year = data.getStringExtra(INTENT_YEAR);
                String hour = data.getStringExtra(INTENT_HOURS_OF_DAY);
                String minus = data.getStringExtra(INTENT_MINUS);
                String draw = data.getStringExtra(INTENT_DRAW);
//                String time = data.getStringExtra(INTENT_TIME);
//                Date date1 = AppUtils.getSettingDateTime(year,month,date);
//                SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
//                String dayElement = formatter.format(date1);
//                String[] timeC = data.getStringExtra(INTENT_TIME).split(":",2);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(date),Integer.parseInt(hour),Integer.parseInt(minus));
//                date1.setHours(Integer.parseInt(timeC[0]));
//                date1.setMinutes(Integer.parseInt(timeC[1]));
                Date date1 = calendar.getTime();
                String time = AppUtils.getFormattedTimeStringMenu(date1);
//                String hour = time[0];
//                String min = time[1];
                noteRespository.insertTask(title,desc,time,draw);
                setAlarm(date1.getTime());
            }
            updateTaskList();
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
    private void updateTaskList() {
        noteRespository.getTasks().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                if(adapterEvent==null){
                    adapterEvent = new AdapterEvent(items);
                    recylerlist.setAdapter(adapterEvent);
                }else adapterEvent.addTask(items);
            }
        });
    }
//    private void convertdataforCalendar(){
//        noteRespository.getTasks().observe(this, new Observer<List<Item>>() {
//            @Override
//            public void onChanged(@Nullable List<Item> items) {
//                itemsC = items;
//            }
//        });
//    }
    private void setAlarm(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MyAlarm.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
        alarmManager.setRepeating(AlarmManager.RTC,timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent);
        Toast.makeText(this,"It's set",Toast.LENGTH_SHORT).show();
    }
}
