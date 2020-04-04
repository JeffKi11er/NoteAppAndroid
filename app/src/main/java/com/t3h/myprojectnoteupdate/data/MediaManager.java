package com.t3h.myprojectnoteupdate.data;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.IntDef;

import com.t3h.myprojectnoteupdate.item.ItemSong;

import java.util.ArrayList;

public class MediaManager implements MediaPlayer.OnCompletionListener {
    public static final int NEXT = 1;
    public static final int PREV = -1;
    @IntDef({NEXT,PREV})
    @interface INCREATE{
    }
    private MediaPlayer player;
    private ArrayList<ItemSong>songArrayList;
    private Context context;
    private int currentIndex;

    public MediaManager(ArrayList<ItemSong> songArrayList, Context context) {
        this.songArrayList = songArrayList;
        this.context = context;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }
}
