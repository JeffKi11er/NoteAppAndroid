package com.t3h.myprojectnoteupdate.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.t3h.myprojectnoteupdate.AppConstants;
import com.t3h.myprojectnoteupdate.R;
import com.t3h.myprojectnoteupdate.check.ValidatorUtils;
import com.t3h.myprojectnoteupdate.customview.CustomViewNoteOriginal;
import com.t3h.myprojectnoteupdate.item.Item;
import com.t3h.myprojectnoteupdate.utils.AppUtils;

import java.util.Calendar;
import java.util.Date;

public class EditNewActivity extends AppCompatActivity implements AppConstants,View.OnClickListener {
    public static final int REQUEST_BACK =1;
    public static final String TITLE = "title.backstack";
    public static final String CONTENT = "content.backstack";
    private final String TAG = "EditNewActivity";
    private ImageView imgclose;
    private TextView tvtitle;
    private TextView btndone;
    private EditText edtTitle;
    private TextView tvdate;
    private ImageView imgDetail;
    private String draw;
    Date dateConvert;
    Calendar calendar;
//    private Date dateConvert;
    private CustomViewNoteOriginal edtCustomContent;
//    public static final String UPDATE_EXTRA_DATE = "extra.update.date";
//    public static final String YES_NO = "yes.no";
//    List<String>listString= Arrays.asList("   ","   ","   ","   ","   ","   "
//            ,"   ","   ","   ","   ","   ","   ");
    //AdapterNewEvent adapterNewEvent;
    //RecyclerView recyclerView;
//    boolean band = true;
    private Item item;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_events_layout);
        init();
    }

    private void init() {
        draw = "Red";
        calendar = Calendar.getInstance();
        tvtitle = findViewById(R.id.title);
        edtCustomContent = (CustomViewNoteOriginal) findViewById(R.id.edt_descrp);
        edtTitle = findViewById(R.id.edtTitle);
        tvdate = findViewById(R.id.tv_time_edtnote);

        imgclose = findViewById(R.id.btn_close);
        imgclose.setOnClickListener(this
//                new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if ((Integer)imgclose.getTag()==R.drawable.btn_done){
//                    setResult(Activity.RESULT_CANCELED);
//                }else {
//                    Intent intent = getIntent();
//                    intent.putExtra(INTENT_DELETE,true);
//                    intent.putExtra(INTENT_TASK,item);
//                    setResult(Activity.RESULT_OK,intent);
//                }
//                finish();
//                overridePendingTransition(R.anim.stay, R.anim.slide_down);
//            }
//        }
        );

        btndone = findViewById(R.id.btn_done);
        btndone.setOnClickListener(this
//                new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(ValidatorUtils.isInputEmpty(edtCustomContent,edtTitle)){
//                    return;
//                }
//                Intent intent = getIntent();
//                if (item!=null){
//                    item.setTitle(edtTitle.getText().toString());
//                    item.setDescription(edtCustomContent.getText().toString());
//                    item.setCreatedAt(calendar.getTime());
//                    item.setDraw(draw);
//                    intent.putExtra(INTENT_TASK,item);
//                }else {
//                    intent.putExtra(INTENT_TITLE,edtTitle.getText().toString());
//                    intent.putExtra(INTENT_DESCRIPT,edtCustomContent.getText().toString());
////                    String []dayT = AppUtils.getFormattedDayString(AppUtils.getCurrentDateTime()).split(" ",3);
//                    String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
//                    String month = Integer.toString(calendar.get(Calendar.MONTH));
//                    String year  =Integer.toString(calendar.get(Calendar.YEAR));
//                    String hours = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
//                    String minus = Integer.toString(calendar.get(Calendar.MINUTE));
//                    //String time = dateConvert.getHours()+":"+dateConvert.getMinutes();
//                    intent.putExtra(INTENT_DAY,day);
//                    intent.putExtra(INTENT_MONTH,month);
//                    intent.putExtra(INTENT_YEAR,year);
//                    intent.putExtra(INTENT_HOURS_OF_DAY,hours);
//                    intent.putExtra(INTENT_MINUS,minus);
//                    intent.putExtra(INTENT_DRAW,draw);
//                    //intent.putExtra(INTENT_TIME,time);
//                }
////            NavigatorUtils.redirectToMenuSettingNote(this,item);
//                setResult(Activity.RESULT_OK,intent);
//                finish();
//                overridePendingTransition(R.anim.stay,R.anim.slide_down);
//            }
//        }
        );

        imgDetail = findViewById(R.id.img_detail);
        imgDetail.setOnClickListener(this
//                new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(v.getContext(), MenuSettingActivity.class);
//                Bundle bundle = new Bundle();
//                String title = edtTitle.getText().toString();
//                String content = edtCustomContent.getText().toString();
//                String date = AppUtils.getFormattedTimeStringMenu(calendar.getTime());
//                String draw2 =  draw;
////                intent1.putExtra("title",title);
////                intent1.putExtra("content",content);
////                intent1.putExtra("date",date);
////                intent1.putExtra("d11e",content);
//                bundle.putString("title",title);
//                bundle.putString("content",content);
//                bundle.putString("date",date);
//                bundle.putString("draw",draw2);
//                intent1.putExtra("note",bundle);
//                startActivityForResult(intent1,1);
//            }
//        }
        );

        item = (Item) getIntent().getSerializableExtra(INTENT_TASK);
        if (item == null) {
            dateConvert = AppUtils.getCurrentDateTime();
            calendar.setTime(dateConvert);
            tvtitle.setText(getString(R.string.title_edit_note));
            imgclose.setImageResource(R.drawable.btn_done);
            imgclose.setTag(R.drawable.btn_done);
            tvdate.setText(AppUtils.getFormattedTimeStringMenu(calendar.getTime()));
        } else {
            Date date = item.getCreatedAt();
            calendar.setTime(date);
            tvtitle.setText(getString(R.string.title_edit_note_edit));
            imgclose.setImageResource(R.drawable.ic_delete2);
            imgclose.setTag(R.drawable.ic_delete2);
            if (item.getTitle() != null && !item.getTitle().isEmpty()) {
                edtTitle.setText(item.getTitle());
                edtTitle.setSelection(edtTitle.getText().length());
            }
            if (item.getDescription() != null && !item.getDescription().isEmpty()) {
                edtCustomContent.setText(item.getDescription());
                edtCustomContent.setSelection(edtCustomContent.getText().length());
            }
            if (item.getCreatedAt() != null) {
                tvdate.setText(AppUtils.getFormattedTimeStringMenu(calendar.getTime()));
            }
            if (item.getDraw()!=null){
                draw = item.getDraw();
            }
        }
        AppUtils.openKeyboard(getApplicationContext());
    }

    //    @Override
//    protected void initAct() {
//        Log.v(TAG,"onCreate");
//        ActionBar actionBar3 = getSupportActionBar();
//        actionBar3.setTitle("New Note");///co thay doi khi click vao item
//        actionBar3.setDisplayHomeAsUpEnabled(true);
        //LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        //recyclerView.setLayoutManager(llm);
//        Intent intent = getIntent();
//        intent.putExtra(CurrentEventFragment.CURRENT_EXTRA_DATE,UPDATE_EXTRA_DATE);
        //adapterNewEvent = new AdapterNewEvent(listString);
        //recyclerView.setAdapter(adapterNewEvent);
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_add,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getItemId()==R.id.check_add){
//            startActivitySetting();
//        }
//        return super.onOptionsItemSelected(item);
//    }

//    private void startActivitySetting() {
////        String[]escrit = adapterNewEvent.getEscri();
////        for (int i = 0; i < escrit.length; i++) {
////            if (escrit[i]==null){
////                band = false;
////                return;
////            }else {
////                if (escrit[i].equals("")) {
////                    band = false;
////                    return;
////                }
////            }
////        }
////        if (band){
////            for (int i = 0;i<escrit.length;i++){
////
////            }
////        }
//        if(ValidatorUtils.isInputEmpty(biding.edtDescrp,biding.edtTitle)){
//            return;
//        }
//        String title = biding.edtTitle.getText().toString();
//        String descrip=biding.edtDescrp.getText().toString();
//        String []times = UPDATE_EXTRA_DATE.split("T",2);
//        String date = times[0];
//        String time = times[1];
//        boolean isInsert = false;
//
////        if(item==null){
////            item = new Item();
////        }
////        item.setDate(date);
////        item.setDescriptZZZion(descrip);
////        item.setTime(time);
////        item.setTitle(title);
////        if (isInsert){
////            Appdatabase.getInstance(this).getItemDao().insert(item);
////        }else {
////            Appdatabase.getInstance(this).getItemDao().update(item);
////        }
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra(title,MainActivity.REQUEST_TITLE);
//        intent.putExtra(descrip,MainActivity.REQUEST_DESCRIPT);
//        intent.putExtra(date,MainActivity.REQUEST_DATE);
//        intent.putExtra(time,MainActivity.REQUEST_TIME);
//        startActivity(intent);
////        intent.putExtra("1",MainActivity.REQUEST_TRUE);
//    }


//    @Override
//    public void onClick(View v) {
//        AppUtils.hideKeyboard(this);
//        if (v == imgclose){
//            if ((Integer)imgclose.getTag()==R.drawable.btn_done){
//                setResult(Activity.RESULT_CANCELED);
//            }else {
//                Intent intent = getIntent();
//                intent.putExtra(INTENT_DELETE,true);
//                intent.putExtra(INTENT_TASK,item);
//                setResult(Activity.RESULT_OK,intent);
//            }
//            finish();
//            overridePendingTransition(R.anim.stay, R.anim.slide_down);
//        }else if (v == btndone){
//            if(ValidatorUtils.isInputEmpty(edtCustomContent,edtTitle)){
//            return;
//       }
//            Intent intent = getIntent();
//            if (item!=null){
//                item.setTitle(edtTitle.getText().toString());
//                item.setDescription(edtCustomContent.getText().toString());
//                item.setModifiedAt(AppUtils.getCurrentDateTime());
//                intent.putExtra(INTENT_TASK,item);
//            }else {
//                intent.putExtra(INTENT_TITLE,edtTitle.getText().toString());
//                intent.putExtra(INTENT_DESCRIPT,edtCustomContent.getText().toString());
//                String []dayT = AppUtils.getFormattedDayString(AppUtils.getCurrentDateTime()).split(" ",3);
//                intent.putExtra(INTENT_DAY,dayT[0]);
//                intent.putExtra(INTENT_MONTH,dayT[1]);
//                intent.putExtra(INTENT_YEAR,dayT[2]);
//                intent.putExtra(INTENT_TIME,AppUtils.getFormattedTimeString(AppUtils.getCurrentDateTime()));
//            }
////            NavigatorUtils.redirectToMenuSettingNote(this,item);
//            setResult(Activity.RESULT_OK,intent);
//            finish();
//            overridePendingTransition(R.anim.stay,R.anim.slide_down);
//        }
//        else if(v==imgDetail){
//            Intent intent1 = new Intent(this, MenuSettingActivity.class);
//            if(item!=null) {
//            item.setTitle(edtTitle.getText().toString());
//            item.setDescription(edtCustomContent.getText().toString());
//            }else {
//            if (item!=null){
//                item.setTitle(edtTitle.getText().toString());
//                item.setDescription(edtCustomContent.getText().toString());
//                intent1.putExtra(INTENT_TASK,item);
//            }else {
//                intent1.putExtra(INTENT_TITLE,edtTitle.getText().toString());
//                intent1.putExtra(INTENT_DESCRIPT,edtCustomContent.getText().toString());
//                String []dayT = AppUtils.getFormattedDayString(AppUtils.getCurrentDateTime()).split(" ",3);
//                intent1.putExtra(INTENT_DAY,dayT[0]);
//                intent1.putExtra(INTENT_MONTH,dayT[1]);
//                intent1.putExtra(INTENT_YEAR,dayT[2]);
//                intent1.putExtra(INTENT_TIME,AppUtils.getFormattedTimeString(AppUtils.getCurrentDateTime()));
//            }
//            intent1.putExtra(INTENT_TASK,item);
//            String title = edtTitle.getText().toString();
//            String content = edtCustomContent.getText().toString();
//            String date = AppUtils.getFormattedDate2String(AppUtils.getCurrentDateTime());
//            intent1.putExtra("title",title);
//            intent1.putExtra("content",content);
//            intent1.putExtra("date",date);
//            startActivityForResult(intent1,1);
//            startActivity(intent1);
//            setResult(Activity.RESULT_OK,intent1);
//            finish();
//            overridePendingTransition(R.anim.stay,R.anim.slide_down);
//        }

//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if (resultCode==RESULT_OK){
                String title = data.getStringExtra("Title");
                String content = data.getStringExtra("Content");
                String date = data.getStringExtra("Date");
                String draw2 = data.getStringExtra("Draw");
                edtTitle.setText(title);
                edtCustomContent.setText(content);
                tvdate.setText(date);
                Date date1 = AppUtils.ConvertStringToDate(date);
                calendar.setTime(date1);
                draw =draw2;
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

    @Override
    public void onClick(View v) {
        AppUtils.hideKeyboard(this);
        switch (v.getId()){
            case R.id.btn_close:
                if ((Integer)imgclose.getTag()==R.drawable.btn_done){
                    setResult(Activity.RESULT_CANCELED);
                }else {
                    Intent intent = getIntent();
                    intent.putExtra(INTENT_DELETE,true);
                    intent.putExtra(INTENT_TASK,item);
                    setResult(Activity.RESULT_OK,intent);
                }
                finish();
                overridePendingTransition(R.anim.stay, R.anim.slide_down);
                break;
            case R.id.btn_done:
                if(ValidatorUtils.isInputEmpty(edtCustomContent,edtTitle)){
                    return;
                }
                Intent intent = getIntent();
                if (item!=null){
                    item.setTitle(edtTitle.getText().toString());
                    item.setDescription(edtCustomContent.getText().toString());
                    item.setCreatedAt(calendar.getTime());
                    item.setDraw(draw);
                    intent.putExtra(INTENT_TASK,item);
                }else {
                    intent.putExtra(INTENT_TITLE,edtTitle.getText().toString());
                    intent.putExtra(INTENT_DESCRIPT,edtCustomContent.getText().toString());
//                    String []dayT = AppUtils.getFormattedDayString(AppUtils.getCurrentDateTime()).split(" ",3);
                    String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
                    String month = Integer.toString(calendar.get(Calendar.MONTH));
                    String year  =Integer.toString(calendar.get(Calendar.YEAR));
                    String hours = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
                    String minus = Integer.toString(calendar.get(Calendar.MINUTE));
                    //String time = dateConvert.getHours()+":"+dateConvert.getMinutes();
                    intent.putExtra(INTENT_DAY,day);
                    intent.putExtra(INTENT_MONTH,month);
                    intent.putExtra(INTENT_YEAR,year);
                    intent.putExtra(INTENT_HOURS_OF_DAY,hours);
                    intent.putExtra(INTENT_MINUS,minus);
                    intent.putExtra(INTENT_DRAW,draw);
                    //intent.putExtra(INTENT_TIME,time);
                }
//            NavigatorUtils.redirectToMenuSettingNote(this,item);
                setResult(Activity.RESULT_OK,intent);
                finish();
                overridePendingTransition(R.anim.stay,R.anim.slide_down);
                break;
            case R.id.img_detail:
                Intent intent1 = new Intent(v.getContext(), MenuSettingActivity.class);
                Bundle bundle = new Bundle();
                String title = edtTitle.getText().toString();
                String content = edtCustomContent.getText().toString();
                String date = AppUtils.getFormattedTimeStringMenu(calendar.getTime());
                String draw2 =  draw;
//                intent1.putExtra("title",title);
//                intent1.putExtra("content",content);
//                intent1.putExtra("date",date);
//                intent1.putExtra("d11e",content);
                bundle.putString("title",title);
                bundle.putString("content",content);
                bundle.putString("date",date);
                bundle.putString("draw",draw2);
                intent1.putExtra("note",bundle);
                startActivityForResult(intent1,1);
                break;
        }
    }

//    @Override
//    protected int getLayoutId() {
//        return R.layout.news_events_layout;
//    }
//    public void setItem(Item item){
//        this.item = item;
//        if (item != null){
//            biding.edtTitle.setText(item.getTitle());
//            biding.edtDescrp.setText(item.getDescription());
//            item.setTime(item.getTime());
//            item.setDate(item.getDate());
//        }else{
//            biding.edtTitle.setText("");
//            biding.edtDescrp.setText("");
//        }
//    }


//
}
