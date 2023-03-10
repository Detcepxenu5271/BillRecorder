package com.example.billrecorder.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;

// 表示“表”的类
@Entity
public class Bill {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int account_id;
    private long date;
    private double amount;
    private String note;

    public Bill(int account_id, long date, double amount, String note) {
        this.account_id = account_id;
        this.date = date;
        this.amount = amount;
        this.note = note;
    }

    // -------- Getter 和 Setter --------

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAccount_id() { return account_id; }
    public void setAccount_id(int account_id) { this.account_id = account_id; }

    public long getDate() { return date; }
    public void setDate(long date) { this.date = date; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    @Override
    public String toString() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(date));
        return calendar.get(Calendar.YEAR) + "\t" +
               calendar.get(Calendar.MONTH) + "\t" +
               calendar.get(Calendar.DAY_OF_MONTH) + "\t" +
               calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + "\t" +
               amount + "\t" +
               "null" + "\t" + // tag，自用格式
               note;
    }
}
