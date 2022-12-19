package com.example.billrecorder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.billrecorder.Layout.BillInfoLayout;
import com.example.billrecorder.database.Bill;
import com.example.billrecorder.database.BillDBEngine;

import java.util.ArrayList;
import java.util.List;

public class ManageActivity extends AppCompatActivity {

    // -------- 视图中的组件 --------

    private LinearLayout billListLinearLayout;

    // 将 Bill 数据库中的信息加载到 billList 中
    private void billListLoadBill() {
        // List<List<Bill>> billList = billDBEngine.query_all_bill();
        List<Bill> billList = billDBEngine.query_all_bill();
        for (Bill bill : billList) {
            Log.d("MyManage", "get bill: " + bill.toString());

            // 创建一个新的 BillInfoLayout，设置账单信息，并记录到 List 里
            BillInfoLayout billInfoLayout = new BillInfoLayout(this, null);
            billInfoLayout.setInfo(bill);
            billListLinearLayout.addView(billInfoLayout);
        }
    }

    // -------- 账单信息 --------

    private int account_id = 0; // 当前账户 TODO: 增加账户管理功能

    // -------- 数据库模块 --------

    private BillDBEngine billDBEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        // Intent intent = getIntent();

        // -------- 获取组件 --------

        billListLinearLayout = (LinearLayout) this.findViewById(R.id.billListLinearLayout);

        // -------- manageButton 部分的三个跳转按钮（账户、统计、查询） --------

        // 获取 account，statistics，search 图片
        ImageView account    = (ImageView) this.findViewById(R.id.account);
        ImageView statistics = (ImageView) this.findViewById(R.id.statistics);
        ImageView search     = (ImageView) this.findViewById(R.id.search);

        account.setOnClickListener(clickListener);
        statistics.setOnClickListener(clickListener);
        search.setOnClickListener(clickListener);

        // -------- 初始化数据库 --------

        billDBEngine = new BillDBEngine(this);

        billListLoadBill();
    }

    public View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.account) {
                // 创建 Intent，进入 TODO
//                Intent intent = new Intent(ManageActivity.this, TODO.class);
//                startActivity(intent);
                // AddActivity.this.finish();
            } else
            if (view.getId() == R.id.statistics) {
                // 创建 Intent，进入 TODO
//                Intent intent = new Intent(ManageActivity.this, TODO.class);
//                startActivity(intent);
                // AddActivity.this.finish();
            } else
            if (view.getId() == R.id.search) {
                // 创建 Intent，进入 TODO
//                Intent intent = new Intent(ManageActivity.this, TODO.class);
//                startActivity(intent);
                // AddActivity.this.finish();
            }
        }
    };
}