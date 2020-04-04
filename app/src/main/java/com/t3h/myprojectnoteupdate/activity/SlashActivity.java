package com.t3h.myprojectnoteupdate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.github.ybq.android.spinkit.SpinKitView;
import com.t3h.myprojectnoteupdate.R;

public class SlashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash);
        final SpinKitView spinKitView = findViewById(R.id.spin_kit);
        final RelativeLayout relativeLayout = findViewById(R.id.rl_logo);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(SlashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);
    }
}
