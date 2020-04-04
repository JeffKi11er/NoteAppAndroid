package com.t3h.myprojectnoteupdate.appdatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.t3h.myprojectnoteupdate.data.DaoDate;
import com.t3h.myprojectnoteupdate.item.Item;

@Database(entities = {Item.class}, version = 1,exportSchema = false)
public abstract class Appdatabase extends RoomDatabase {
//    private static Appdatabase instance = null;
//    public static Appdatabase getInstance(Context context){
//        if(instance==null) {
//            instance = Room.databaseBuilder(context,
//                    Appdatabase.class, "student_manager")
//                    .allowMainThreadQueries()
//                    .build();
//        }
//        return instance;
//    }
    public abstract DaoDate getItemDao();
}
