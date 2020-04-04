package com.t3h.myprojectnoteupdate.utils;

import android.app.Activity;
import android.content.Intent;

import com.t3h.myprojectnoteupdate.AppConstants;
import com.t3h.myprojectnoteupdate.activity.EditNewActivity;
import com.t3h.myprojectnoteupdate.activity.MenuSettingActivity;
import com.t3h.myprojectnoteupdate.item.Item;

public class NavigatorUtils implements AppConstants {
    ///CalendarNeeds
    public static void redirectToEditTaskScreen(Activity activity,
                                                Item item){
        Intent intent = new Intent(activity, EditNewActivity.class);
        intent.putExtra(INTENT_TASK,item);
        activity.startActivityForResult(intent,ACTIVITY_REQUEST_CODE);
    }
    public static void redirectToViewNoteScreen(Activity activity,
                                                Item item){
        Intent intent = new Intent(activity,EditNewActivity.class);
        intent.putExtra(INTENT_TASK,item);
        intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        activity.startActivity(intent);
        activity.finish();
    }
    public static void redirectToMenuSettingNote(Activity activity,Item item){
        Intent intent = new Intent(activity, MenuSettingActivity.class);
        intent.putExtra(INTENT_TASK,item);
        intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        activity.startActivity(intent);
        activity.finish();
    }
}
