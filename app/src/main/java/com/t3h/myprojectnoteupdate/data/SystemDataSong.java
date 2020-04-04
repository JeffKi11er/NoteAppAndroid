package com.t3h.myprojectnoteupdate.data;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.t3h.myprojectnoteupdate.item.ItemSong;

import java.util.ArrayList;

public class SystemDataSong {
    private ContentResolver resolver;

    public SystemDataSong(Context context) {
        resolver = context.getContentResolver();
    }
    public ArrayList<ItemSong>getData(){
        ArrayList<ItemSong>arr = new ArrayList<>();
        Cursor cursor = resolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,null,null,null);
        cursor.moveToFirst();
        int indexData = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
        int indexTitle = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
        while(cursor.isAfterLast()==false){
            String data = cursor.getString(indexData);
            String title = cursor.getString(indexTitle);
            ItemSong itemSong = new ItemSong(data,title);
            arr.add(itemSong);
            cursor.moveToNext();
        }
        return arr;
    }
}
