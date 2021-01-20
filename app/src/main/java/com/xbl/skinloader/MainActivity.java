package com.xbl.skinloader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xbl.skinlib.base.BaseFragmentActivity;

public class MainActivity extends BaseFragmentActivity {
    Button detail;
    Button setting;
    Button fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        detail = findViewById(R.id.detail);
        setting = findViewById(R.id.setting);
        fragment = findViewById(R.id.fragment);
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(i);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(ii);
            }
        });
        fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iii = new Intent(MainActivity.this, FragmentTestActivity.class);
                startActivity(iii);
            }
        });
    }

}