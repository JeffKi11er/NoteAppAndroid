package com.t3h.myprojectnoteupdate.data;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.t3h.myprojectnoteupdate.item.Item;

import java.util.List;

@Dao
public interface DaoDate {
    @Insert
    Long insert(Item item);
    @Query("SELECT * FROM Item ORDER BY created_at desc ")
    LiveData<List<Item>> getAll();
    @Query("SELECT * FROM Item WHERE id =:taskID")
    LiveData<Item>getTask(int taskID);
    @Query("SELECT * FROM Item WHERE monthOfYear=:monthChosen AND year=:yearChosen")
    LiveData<List<Item>>getAllMonthandYear(int monthChosen,int yearChosen);
    @Update
    void update(Item item);
    @Delete
    void delete(Item item);
}
