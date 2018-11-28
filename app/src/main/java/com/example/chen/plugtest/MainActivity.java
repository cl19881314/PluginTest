package com.example.chen.plugtest;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManager.getInstance().setContext(this);
    }

    public void onLoade(View v){
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "plug.apk");
        PluginManager.getInstance().loadPath(file.getPath());
    }

    public void onStart(View v){
        Intent intent = new Intent(this, ProxyActivity.class);
        intent.putExtra("className", PluginManager.getInstance().getEntryActivityName());
        startActivity(intent);
    }
}
