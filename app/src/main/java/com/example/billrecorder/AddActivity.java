package com.example.billrecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    // -------- 视图中的组件 --------

    private ImageView addBill;

    private ConstraintLayout addBillManual;
    private TextView addBillDate;
    private TextView addBillTime;
    private ImageView addBillType;
    private Button addBillConfirm;

    private LinearLayout showBill;

    // -------- 账单信息 --------

    private Calendar calendar_send = Calendar.getInstance(); // 应该被添加到账单中的时间（在确认添加时）
    private boolean is_outcome; // 当前账单类别是否为支出

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // -------- addBill：整体界面 --------

        // 获取 addBill 图片
        addBill = (ImageView) this.findViewById(R.id.addBill);

        addBill.setOnLongClickListener(longClickListener); // addBill 的长按事件：语音记账
        addBill.setOnClickListener(clickListener); // addBill 的点击事件：手动记账

        // -------- addBillManual：手动添加账单界面 --------

        // 获取 addBillManual 下的组件：日期文本，时间文本，类别图片，确认按钮
        addBillManual = (ConstraintLayout) this.findViewById(R.id.addBillManual);
        addBillDate = (TextView) this.findViewById(R.id.addBillDate);
        addBillTime = (TextView) this.findViewById(R.id.addBillTime);
        addBillType = (ImageView) this.findViewById(R.id.addBillType);
        addBillConfirm = (Button) this.findViewById(R.id.addBillConfirm);

        addBillDate.setOnClickListener(clickListener); // addBillDate 的点击事件：日期选择
        addBillTime.setOnClickListener(clickListener); // addBillTime 的点击事件：时间选择
        addBillType.setOnClickListener(clickListener); // addBillType 的点击事件：时间选择
        addBillConfirm.setOnClickListener(clickListener); // addBillConfirm 的点击事件：确认添加账单

        // 获取 showBill 布局
        showBill = (LinearLayout) this.findViewById(R.id.showBill);
        showBill.setOnLongClickListener(longClickListener); // showBill 的长按事件：暂无
        showBill.setOnClickListener(clickListener); // showBill 的点击事件：跳转到 ManageActivity
    }

    public View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            if (view.getId() == R.id.addBill) {
                addBill.setImageResource(android.R.drawable.presence_audio_online);

                // TODO

                addBill.setImageResource(android.R.drawable.ic_popup_sync);

                // TODO

                addBill.setImageResource(android.R.drawable.ic_menu_add);
            }
            return false;
        }
    };

    public View.OnClickListener clickListener = new View.OnClickListener() {
        @SuppressLint("DefaultLocale")
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.addBill) {
                addBill.setVisibility(View.GONE); // 首先将 addBill 设为隐藏
                addBillManual.setVisibility(View.VISIBLE); // 并且将 addBillManual 设为可见，交换底部的组件

                // 设置账单日期和时间，默认为当前系统时间
                Date date_cur = new Date(System.currentTimeMillis()); // 获取当前系统时间
                addBillDate.setText(String.format("%tF", date_cur)); // 设置 addBillDate 的文本
                addBillTime.setText(String.format("%tT", date_cur)); // 设置 addBillTime 的文本
                calendar_send.setTime(date_cur); // 设置账单日期

                // 初始化账单类型为支出
                is_outcome = true;
                addBillType.setImageResource(R.drawable.outcome);
            } else
            if (view.getId() == R.id.showBill) {
                // 创建 Intent，进入 ManageActivity
                Intent intent = new Intent(AddActivity.this, ManageActivity.class);
                startActivity(intent);
                // AddActivity.this.finish();
            } else
            if (view.getId() == R.id.addBillDate) {
                // 日期设置弹窗
                DatePickerDialog pickerDialog = new DatePickerDialog(AddActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int day) {
                            addBillDate.setText(String.format("%d-%02d-%02d", year, month+1, day));
                            calendar_send.set(year, month, day); // 设置账单日期
                        }
                    // 打开弹窗时，默认显示之前记录的当前日期
                    }, calendar_send.get(Calendar.YEAR), calendar_send.get(Calendar.MONTH), calendar_send.get(Calendar.DAY_OF_MONTH));

                pickerDialog.show(); // 显示 DatePickerDialog 界面
            } else
            if (view.getId() == R.id.addBillTime) {
                // 日期设置弹窗
                TimePickerDialog pickerDialog = new TimePickerDialog(AddActivity.this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int i, int i1) {
                            addBillTime.setText(String.format("%02d:%02d:00", i, i1));
                            calendar_send.set(Calendar.HOUR_OF_DAY, i); // 设置账单日期（小时）
                            calendar_send.set(Calendar.MINUTE, i1); // 设置账单日期（分钟）
                            calendar_send.set(Calendar.SECOND, 0); // 设置账单日期（秒，手动设置时默认为 0）
                        }
                    // 打开弹窗时，默认显示之前记录的当前时间
                    }, calendar_send.get(Calendar.HOUR_OF_DAY), calendar_send.get(Calendar.MINUTE), true);

                pickerDialog.show(); // 显示 TimePickerDialog 界面
            } else
            if (view.getId() == R.id.addBillType) {
                // 切换账单类别（支出和收入）
                if (is_outcome) {
                    addBillType.setImageResource(R.drawable.income);
                    is_outcome = false;
                } else {
                    addBillType.setImageResource(R.drawable.outcome);
                    is_outcome = true;
                }
            } else
            if (view.getId() == R.id.addBillConfirm) {
                addBillManual.setVisibility(View.GONE); // 首先将 addBillManual 设回隐藏
                addBill.setVisibility(View.VISIBLE); // 并且将 addBill 设回可见，交换底部的组件
            }
        }
    };
}