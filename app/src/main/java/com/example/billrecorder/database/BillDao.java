package com.example.billrecorder.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// 表示数据访问对象（Dao）的接口类，实现增删改查
@Dao
public interface BillDao {
    //增
    @Insert
    void insert_bill(Bill ... bills);

    //改
    @Update
    void update_bill(Bill ... bills);

    //删
    @Delete
    void delete_bill(Bill ... bills);

    //删
    @Query("DELETE FROM Bill")
    void delete_all_bill();

    //查
    @Query("SELECT * FROM Bill ORDER BY date DESC")
    List<Bill> get_all_bill();
    // LiveData<List<Bill>> get_all_bill();

    /*@Query("SELECT * FROM Bill WHERE id = :id")
    Bill get_bill_by_id(int id);*/
}
