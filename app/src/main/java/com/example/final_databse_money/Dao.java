package com.example.final_databse_money;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PojoOfJsonArray pojoOfJsonArray);

    @Update
    void update(PojoOfJsonArray pojoOfJsonArray);

    @Delete
    void delete(PojoOfJsonArray pojoOfJsonArray);

    @Query("SELECT* FROM income_details ORDER BY date ASC")
    LiveData<List<PojoOfJsonArray>> getAllData();
}
