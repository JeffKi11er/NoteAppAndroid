package com.t3h.myprojectnoteupdate.respository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import com.t3h.myprojectnoteupdate.appdatabase.Appdatabase;
import com.t3h.myprojectnoteupdate.item.Item;
import com.t3h.myprojectnoteupdate.utils.AppUtils;
import com.t3h.myprojectnoteupdate.utils.TimestampConverter;

import java.util.Date;
import java.util.List;
///CalendarNeeds
public class NoteRespository {
    private String DATABASE_NAME = "db_task";
    private Appdatabase appdatabase;

    public NoteRespository(Context context) {
        appdatabase = Room.databaseBuilder(context, Appdatabase.class,DATABASE_NAME).build();
    }
    public void insertTask(String title,String description,
                           String date,String draw){
        Item item = new Item();
        item.setTitle(title);
        item.setDescription(description);
        item.setDraw(draw);
        item.setCreatedAt(AppUtils.ConvertStringToDate(date));
        item.setModifiedAt(AppUtils.ConvertStringToDate(date));
        item.setDay(AppUtils.GetCalendarDay(AppUtils.ConvertStringToDate(date)));
        item.setMonth(AppUtils.GetCalendarMonth(AppUtils.ConvertStringToDate(date)));
        item.setYear(AppUtils.GetCalendarYear(AppUtils.ConvertStringToDate(date)));
        item.setHour(AppUtils.GetCalendarHours(AppUtils.ConvertStringToDate(date)));
        item.setMinus(AppUtils.GetCalendarMinus(AppUtils.ConvertStringToDate(date)));
        insertTask(item);
    }
    public void insertTask(String title,
                           String description
                           ){
        Item item = new Item();
        item.setTitle(title);
        item.setDescription(description);
        item.setCreatedAt(AppUtils.getCurrentDateTime());
        item.setModifiedAt(AppUtils.getCurrentDateTime());
        insertTask(title,description,null,null);
    }
    public void insertTask(final Item item){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                appdatabase.getItemDao().insert(item);
                return null;
            }
        }.execute();
    }
    public void updateTask(final Item item){
        item.setModifiedAt(AppUtils.getCurrentDateTime());
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                appdatabase.getItemDao().update(item);
                return null;
            }
        }.execute();
    }
    public void deleteTask(final int id){
        final LiveData<Item> task  = getTask(id);
        if(task != null){
            new AsyncTask<Void, Void, Void >(){
                @Override
                protected Void doInBackground(Void... voids) {
                    appdatabase.getItemDao().delete(task.getValue());
                    return null;
                }

            }.execute();
        }
    }
    public void deleteTask(final Item item){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                appdatabase.getItemDao().delete(item);
                return null;
            }
        }.execute();
    }
    public LiveData<Item>getTask(int id){
        return appdatabase.getItemDao().getTask(id);
    }
    public LiveData<List<Item>>getTasks(){
        return appdatabase.getItemDao().getAll();
    }
    public LiveData<List<Item>>getTime(int month, int year){
        return appdatabase.getItemDao().getAllMonthandYear(month,year);
    }
}
