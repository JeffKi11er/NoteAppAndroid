package com.t3h.myprojectnoteupdate.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.t3h.myprojectnoteupdate.R;
import com.t3h.myprojectnoteupdate.adapter.SongAdapter;
import com.t3h.myprojectnoteupdate.data.SystemDataSong;
import com.t3h.myprojectnoteupdate.databinding.ActivitySoundToolBinding;
import com.t3h.myprojectnoteupdate.item.ItemSong;

import java.util.ArrayList;


public class RingtoneActivity extends AppCompatActivity implements SongAdapter.ItemClickListener, View.OnClickListener {
    private TextView titleToolBar,btnDone;
    private ImageView imgbtclose;
    private RecyclerView lvSong;
    private final String TAG = "RingtoneActivity";
    private final String[] PERMISSION_LIST = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    private SystemDataSong systemDataSong;
    private ArrayList<ItemSong>songArrayList;
    //private ActivitySoundToolBinding binding;
    private SongAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG,"onCreate");
        super.onCreate(savedInstanceState);
//        binding = DataBindingUtil.setContentView(this,
//                R.layout.activity_sound_tool);
        setContentView(R.layout.music_layout);
        adapter = new SongAdapter(this);
        adapter.setListener(this);
        lvSong = findViewById(R.id.lv_music);
        lvSong.setAdapter(adapter);
        imgbtclose = findViewById(R.id.btn_close);
        titleToolBar = findViewById(R.id.title);
        btnDone = findViewById(R.id.btn_done);
        btnDone.setText("");
        titleToolBar.setText(R.string.title_ringtones);
        imgbtclose.setImageResource(R.drawable.ic_arrowback);
        imgbtclose.setTag(R.drawable.ic_arrowback);
        imgbtclose.setOnClickListener(this);
        if (checkPermission()){
            readData();
        }
    }

    private void readData() {
        systemDataSong = new SystemDataSong(this);
        songArrayList = systemDataSong.getData();
        adapter.setData(songArrayList);
    }

    //    @Override
//    protected void initAct() {
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        Intent intent = getIntent();
//        String actionbartitle = intent.getStringExtra("actionBarTitle");
//        String contentx = intent.getStringExtra("contentitem");
//        actionBar.setTitle(actionbartitle);
//        biding.textView.setText(contentx);
//    }
    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String p : PERMISSION_LIST) {
                if (checkSelfPermission(p) !=
                        PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(PERMISSION_LIST, 0);
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (checkPermission()){
            readData();
        }else {
            finish();
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
    public void onItemClicked(int position) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_close:
                finish();
                overridePendingTransition(R.anim.stay,R.anim.slide_down);
                break;
        }
    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_test;
//    }
}
