package com.t3h.myprojectnoteupdate.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.t3h.myprojectnoteupdate.AppConstants;
import com.t3h.myprojectnoteupdate.R;
import com.t3h.myprojectnoteupdate.item.Item;
import com.t3h.myprojectnoteupdate.utils.AppUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MenuSettingActivity extends AppCompatActivity implements AppConstants {
    private final String TAG = "MenuSettingActivity";
    public static final String TITLE = "title.menu";
    public static final String CONTENT = "content.menu";
    public static final String DATE = "date.menu";
    public static final String DRAW = "Blue";
    private ImageView imgNextMoveColor,imgNextMoveSound,imgNextMoveRepeat,imgNextMoveDate,imgNextMoveTime;
    private TextView tvColor,tvSound,tvRepeat,tvDate,tvTime;
    private TextView imgColor;
    private TextView tvsoundname,tvreapeatminutes,tvdatepick,tvtimepick;
    private TextView tvtitle;
    private TextView btndone;
    private ImageView imgclose;
    private Item item;
    private Date date1;
    private Calendar calendar2;
    private String title;
    private String content;
    private String drawMenu;
    private TextView tvCOLOR,tvSOUND,tvDATE,tvTIME;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);
        init();
    }

    private void init() {
        calendar2 = Calendar.getInstance();
        final Intent intent = getIntent();
        final Bundle bundle = intent.getBundleExtra("note");
        title = bundle.getString("title");
        content = bundle.getString("content");
        final String date = bundle.getString("date");
        final String draw = bundle.getString("draw");
         date1 = AppUtils.ConvertStringToDate(date);
         calendar2.setTime(date1);
         drawMenu = draw;
        tvtitle = findViewById(R.id.title);
        tvtitle.setText(R.string.title_menu_setting);

        imgclose = findViewById(R.id.btn_close);
        imgclose.setImageResource(R.drawable.ic_arrowback);
        imgclose.setTag(R.drawable.ic_arrowback);
        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
                overridePendingTransition(R.anim.stay,R.anim.slide_down);
            }
        });

        btndone = findViewById(R.id.btn_done);
        btndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                calendar2.setTime(date1);
                Intent intent = new Intent();
                intent.putExtra("Title",title);
                intent.putExtra("Content",content);
                Date dl = calendar2.getTime();
                intent.putExtra("Date",AppUtils. getFormattedTimeStringMenu(dl));
                intent.putExtra("Draw",drawMenu);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        imgColor = findViewById(R.id.img_color_show);
        getDString(drawMenu);
        imgNextMoveColor = findViewById(R.id.img_next_color);
        tvCOLOR = findViewById(R.id.tvCOLOR);
        tvCOLOR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(v.getContext(),ColorActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("TITLE",title);
                bundle1.putString("CONTENT",content);
                Date dl = calendar2.getTime();
                bundle1.putString("DATE",AppUtils.getFormattedTimeStringMenu(dl));
                bundle1.putString("DRAW",drawMenu);
                intent1.putExtra("bundle1",bundle1);
                startActivityForResult(intent1,3);
            }
        });

        imgNextMoveDate = findViewById(R.id.img_next_date);
        tvDATE = findViewById(R.id.tvDATE);
        tvDATE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showDateTimePicker();
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(imgNextMoveDate.getContext(),R.style.AppTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        calendar2.set(Calendar.YEAR,year);
                        calendar2.set(Calendar.MONTH,month);
                        calendar2.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        Date date2 = calendar2.getTime();
                        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
                        String time = format.format(date2);
                        tvdatepick.setText(time);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        imgNextMoveTime = findViewById(R.id.img_next_time);
        tvTIME = findViewById(R.id.tvTIME);
        tvTIME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                final int hours = calendar.get(Calendar.HOUR_OF_DAY);
                int minuts = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(imgNextMoveTime.getContext(), R.style.MyDialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        calendar2.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar2.set(Calendar.MINUTE,minute);
                        calendar2.setTimeZone(TimeZone.getDefault());
                        Date date11 = calendar2.getTime();
                        SimpleDateFormat hfomate = new SimpleDateFormat("K:mm a");
                        String eventTime = hfomate.format(date11);
                        tvtimepick.setText(eventTime);

                    }
                },hours,minuts,false);
                timePickerDialog.show();
            }

        });

        imgNextMoveSound = findViewById(R.id.img_next_sound);
        tvSOUND = findViewById(R.id.tvSOUND);
        tvSOUND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(v.getContext(),RingtoneActivity.class);
                //startActivityForResult(intent2,4);
                startActivity(intent2);
            }
        });

        imgNextMoveRepeat = findViewById(R.id.img_next_repeat);
        imgNextMoveRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvsoundname = findViewById(R.id.tv_sound_content);
        tvreapeatminutes = findViewById(R.id.tv_repeat_content);
        tvdatepick = findViewById(R.id.tv_date_content);
        tvtimepick = findViewById(R.id.tv_time_content);

            tvdatepick.setText(calendar2.get(Calendar.DAY_OF_MONTH)+" "+calendar2.get(Calendar.MONTH)+" , "+calendar2.get(Calendar.YEAR));
            tvtimepick.setText(calendar2.get(Calendar.HOUR_OF_DAY)+":"+calendar2.get(Calendar.MINUTE));
    }
    private void getDString(String drawS){
        switch (drawS){
            case "Blue":{
                imgColor.setBackgroundResource(R.drawable.ic_color_blue);
                break;
            }
            case "Red":{
                imgColor.setBackgroundResource(R.drawable.ic_color_red);
                break;
            }
            case "Green":{
                imgColor.setBackgroundResource(R.drawable.ic_color);
                break;
            }
            case "Yellow":{
                imgColor.setBackgroundResource(R.drawable.ic_color_yellow);
                break;
            }
            case "Purple":{
                imgColor.setBackgroundResource(R.drawable.ic_color_puple);
                break;
            }
            case "Teal":{
                imgColor.setBackgroundResource(R.drawable.ic_color_teal);
                break;
            }
            case "Orange":{
                imgColor.setBackgroundResource(R.drawable.ic_color_orange);
                break;
            }
            default:{
                imgColor.setBackgroundResource(R.drawable.ic_color_brown);
                break;
            }
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
//
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==3){
            if (resultCode==RESULT_OK){
                String title1 = data.getStringExtra("tItle");
                String content1 = data.getStringExtra("cOntent");
                String date = data.getStringExtra("dAte");
                String draw2 = data.getStringExtra("dRaw");
                title = title1;
                content =content1;
                date1 = AppUtils.ConvertStringToDate(date);
                calendar2.setTime(date1);
                drawMenu = draw2;
                getDString(drawMenu);
                tvdatepick.setText(calendar2.get(Calendar.DAY_OF_MONTH)+" "+calendar2.get(Calendar.MONTH)+" , "+calendar2.get(Calendar.YEAR));
                tvtimepick.setText(calendar2.get(Calendar.HOUR_OF_DAY)+":"+calendar2.get(Calendar.MINUTE));
            }
        }
    }

    }
