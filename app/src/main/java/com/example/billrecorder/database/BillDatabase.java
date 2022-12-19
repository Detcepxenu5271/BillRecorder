package com.example.billrecorder.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// 表示数据库的类
@Database(entities = {Bill.class}, version = 1, exportSchema = false)
public abstract class BillDatabase extends RoomDatabase {
    //用户只需要操作DAO
    public abstract BillDao get_bill_dao();
    //单例模式
    private static BillDatabase billDatabase;
    public static synchronized BillDatabase getInstance(Context context){
        if(billDatabase == null){
            billDatabase = Room.databaseBuilder(context.getApplicationContext(),BillDatabase.class,"bill")
                    .allowMainThreadQueries() //主线程也能操作数据库 只能测试用 TODO: 禁用主线程操纵数据库，实现异步操作
                    .build();
        }
        return billDatabase;
    }
}
