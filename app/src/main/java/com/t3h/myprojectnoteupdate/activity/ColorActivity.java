package com.t3h.myprojectnoteupdate.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.t3h.myprojectnoteupdate.R;
import com.t3h.myprojectnoteupdate.adapter.ColorAdapter;
import com.t3h.myprojectnoteupdate.item.ItemColor;
import com.t3h.myprojectnoteupdate.utils.AppUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ColorActivity extends AppCompatActivity implements View.OnClickListener, ColorAdapter.ColorListener{
    private final String TAG = "ColorActivity";
    private ImageView imageclose;
    String date;
    String title;
    String content;
    String drawStr;
    private ArrayList<ItemColor>data;
    private ColorAdapter adapter;
    private RecyclerView lvColor;
    private TextView tvtitleBar, tvDone,tvRedtext,tvRedC,tvBluetext,tvBlueC,tvYellowtext,tvYellowC,tvOrangetext,tvOrangeC,
            tvBrowntext,tvBrownC,tvGreentext,tvGreenC,tvPurpletext,tvPurpleC,tvTealtext,tvTealC;
    private CheckBox checkBoxRed,checkBoxBlue,checkBoxYellow,checkBoxOrange,checkBoxBrown,checkBoxGreen,checkBoxPuple,
                        checkBoxTeal;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_tool);
        initData();
        init();
    }
    private void init() {
        final Intent intent = getIntent();
        final Bundle bundle = intent.getBundleExtra("bundle1");
        title = bundle.getString("TITLE");
        content = bundle.getString("CONTENT");
        date = bundle.getString("DATE");
        final String drawname = bundle.getString("DRAW");
//        final String title1 = intent.getStringExtra(MenuSettingActivity.TITLE);
//        final String content1 = intent.getStringExtra(MenuSettingActivity.CONTENT);
//        final String date1 = intent.getStringExtra(MenuSettingActivity.DATE);
//        final String drawname = intent.getStringExtra(MenuSettingActivity.DRAW);
        drawStr = drawname;
        tvtitleBar = findViewById(R.id.title);
        tvDone = findViewById(R.id.btn_done);
        imageclose = findViewById(R.id.btn_close);
        imageclose.setOnClickListener(this);
        tvtitleBar.setText(R.string.title_color_setting);
        imageclose.setImageResource(R.drawable.ic_arrowback);
        imageclose.setTag(R.drawable.ic_arrowback);
        tvDone.setText("");
        
        lvColor = findViewById(R.id.rcl_color1);
        adapter = new ColorAdapter(this,data);
        lvColor.setAdapter(adapter);
        adapter.setListener(this);
//        tvRedC =findViewById(R.id.tv_CR);
//        tvRedtext = findViewById(R.id.tvCR_text);
//        checkBoxRed = findViewById(R.id.check_red);
//
//        tvBlueC =findViewById(R.id.tv_CBl);
//        tvBluetext = findViewById(R.id.tvCBl_text);
//        checkBoxBlue = findViewById(R.id.check_blue);
//
//        tvGreenC =findViewById(R.id.tv_CG);
//        tvGreentext = findViewById(R.id.tvCG_text);
//        checkBoxGreen = findViewById(R.id.check_green);
//
//        tvBrownC =findViewById(R.id.tv_CBr);
//        tvBrowntext = findViewById(R.id.tvCBr_text);
//        checkBoxBrown = findViewById(R.id.check_brown);
//
//        tvOrangeC =findViewById(R.id.tv_CO);
//        tvOrangetext = findViewById(R.id.tvCO_text);
//        checkBoxOrange = findViewById(R.id.check_orange);
//
//        tvYellowC =findViewById(R.id.tv_CY);
//        tvYellowtext = findViewById(R.id.tvCY_text);
//        checkBoxYellow = findViewById(R.id.check_yellow);
//
//        tvPurpleC =findViewById(R.id.tv_CP);
//        tvPurpletext = findViewById(R.id.tvCP_text);
//        checkBoxPuple = findViewById(R.id.check_purple);
//
//        tvTealC =findViewById(R.id.tv_CT);
//        tvTealtext = findViewById(R.id.tvCT_text);
//        checkBoxTeal = findViewById(R.id.check_teal);
    }
    private void initData() {
        data = new ArrayList<>();
        data.add(new ItemColor(R.drawable.ic_color_red,"Red"));
        data.add(new ItemColor(R.drawable.ic_color_blue,"Blue"));
        data.add(new ItemColor(R.drawable.ic_color_brown,"Brown"));
        data.add(new ItemColor(R.drawable.ic_color_orange,"Orange"));
        data.add(new ItemColor(R.drawable.ic_color_puple,"Purple"));
        data.add(new ItemColor(R.drawable.ic_color_teal,"Teal"));
        data.add(new ItemColor(R.drawable.ic_color_yellow,"Yellow"));
        data.add(new ItemColor(R.drawable.ic_color,"Green"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_close:
//                intent.putExtra(MenuSettingActivity.TITLE,title);
//                intent.putExtra(MenuSettingActivity.CONTENT,content);
//                intent.putExtra(MenuSettingActivity.DATE,date);
//                setResult(Activity.RESULT_OK);
                finish();
                overridePendingTransition(R.anim.stay,R.anim.slide_down);
                break;
        }

    }

    @Override
    public void onClick(int position) {
//        Toast.makeText(this,data.get(position).getColorName(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
//        Bundle bundle = new Bundle();
        intent.putExtra("tItle",title);
        intent.putExtra("cOntent",content);
        intent.putExtra("dAte",date);
        drawStr = data.get(position).getColorName();
        intent.putExtra("dRaw",drawStr);
        //intent.putExtra("colorsend",bundle);
        setResult(RESULT_OK,intent);
        finish();
        overridePendingTransition(R.anim.stay,R.anim.slide_down);
    }
}
