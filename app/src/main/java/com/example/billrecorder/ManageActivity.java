package com.example.billrecorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ManageActivity extends AppCompatActivity {

    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        // Intent intent = getIntent();

        // -------- 返回按钮 --------

        // 获取返回按钮
        returnButton = findViewById(R.id.ManageReturn);

        // returnButton 的点击事件：返回 AddActivity
        returnButton.setOnClickListener(clickListener);

        // -------- manageButton 部分的三个跳转按钮（账户、统计、查询） --------

        // 获取 account，statistics，search 图片
        ImageView account    = (ImageView) this.findViewById(R.id.account);
        ImageView statistics = (ImageView) this.findViewById(R.id.statistics);
        ImageView search     = (ImageView) this.findViewById(R.id.search);

        account.setOnClickListener(clickListener);
        statistics.setOnClickListener(clickListener);
        search.setOnClickListener(clickListener);
    }

    public View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.ManageReturn) {
                // 创建 Intent，进入 AddActivity
                Intent intent = new Intent(ManageActivity.this, AddActivity.class);
                startActivity(intent);
                // AddActivity.this.finish();
            } else
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