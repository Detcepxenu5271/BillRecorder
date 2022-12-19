package com.example.billrecorder.Layout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.billrecorder.R;
import com.example.billrecorder.database.Bill;

import java.util.Date;

public class BillInfoLayout extends ConstraintLayout {

    private TextView date_text;
    private TextView time_text;
    private TextView amount_text;
    private TextView note_text;

    public BillInfoLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.bill_info,BillInfoLayout.this);

        date_text = (TextView) this.findViewById(R.id.date);
        time_text = (TextView) this.findViewById(R.id.time);
        amount_text = (TextView) this.findViewById(R.id.amount);
        note_text = (TextView) this.findViewById(R.id.note);
    }

    @SuppressLint("DefaultLocale")
    public void setInfo(Bill bill) {
        Date d = new Date(bill.getDate());
        date_text.setText(String.format("%tF", d));
        time_text.setText(String.format("%tT", d));
        amount_text.setText(String.valueOf(bill.getAmount()));
        note_text.setText(bill.getNote());
    }
}
