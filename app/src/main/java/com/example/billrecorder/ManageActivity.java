package com.example.billrecorder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ManageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        // Intent intent = getIntent();

        // -------- 返回按钮 --------

        // 获取返回按钮
        Button returnButton = findViewById(R.id.ManageReturn);

        // returnButton 的点击事件：返回 AddActivity
        returnButton.setOnClickListener(view -> {
            if (view.getId() == R.id.ManageReturn) {
                // 创建 Intent，进入 AddActivity
                Intent intent = new Intent(ManageActivity.this, AddActivity.class);
                startActivity(intent);
                // AddActivity.this.finish();
            }
        });

        // -------- manageButton 部分的三个跳转按钮（账户、统计、查询） --------

        // 获取 account，statistics，search 图片
        ImageView account    = (ImageView) this.findViewById(R.id.account);
        ImageView statistics = (ImageView) this.findViewById(R.id.statistics);
        ImageView search     = (ImageView) this.findViewById(R.id.search);

        account.setOnClickListener(view -> {
            // 创建 Intent，进入 ManageActivity
//            Intent intent = new Intent(ManageActivity.this, TODO.class);
//            startActivity(intent);
            // AddActivity.this.finish();
        });

        statistics.setOnClickListener(view -> {
            // 创建 Intent，进入 ManageActivity
//            Intent intent = new Intent(ManageActivity.this, TODO.class);
//            startActivity(intent);
            // AddActivity.this.finish();
        });

        search.setOnClickListener(view -> {
            // 创建 Intent，进入 ManageActivity
//            Intent intent = new Intent(ManageActivity.this, TODO.class);
//            startActivity(intent);
            // AddActivity.this.finish();
        });
    }
}