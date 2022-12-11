package com.example.billrecorder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ManageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        // Intent intent = getIntent();

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
    }
}