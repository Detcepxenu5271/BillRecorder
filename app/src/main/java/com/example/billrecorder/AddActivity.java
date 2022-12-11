package com.example.billrecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // 获取 addBill 图片
        ImageView addBill = (ImageView) this.findViewById(R.id.addBill);
        ConstraintLayout addBillManual = (ConstraintLayout) this.findViewById(R.id.addBillManual);

        // addBill 的长按事件：语音记账
        addBill.setOnLongClickListener(view -> {
            return false;
        });

        // addBill 的点击事件：手动记账
        addBill.setOnClickListener(view -> {
            addBill.setVisibility(View.GONE); // 首先将 addBill 设为隐藏
            addBillManual.setVisibility(View.VISIBLE); // 并且将 addBillManual 设为可见，交换底部的组件
        });

        // 获取 showBill 布局
        LinearLayout showBill = (LinearLayout) this.findViewById(R.id.showBill);

        // showBill 的长按事件：暂无
        showBill.setOnLongClickListener(view -> {
            return false;
        });

        // showBill 的点击事件：跳转到 ManageActivity
        showBill.setOnClickListener(view -> {
            // 创建 Intent，进入 ManageActivity
            Intent intent = new Intent(AddActivity.this, ManageActivity.class);
            startActivity(intent);
            // AddActivity.this.finish();
        });
    }
}