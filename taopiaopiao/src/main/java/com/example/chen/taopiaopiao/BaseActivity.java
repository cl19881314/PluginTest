package com.example.chen.taopiaopiao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.chen.alipaystander.AlipayInterface;

/**
 * @author Chenhong
 * @date 2018/11/28.
 * @des
 */
public class BaseActivity extends AppCompatActivity implements AlipayInterface {
    protected Activity that;

    @Override
    public void setContentView(int layoutResID) {
        if (that == null) {
            super.setContentView(layoutResID);
        } else {
            that.setContentView(layoutResID);
        }
    }

    @Override
    public <T extends View> T findViewById(int id) {
        if (that == null) {
            return super.findViewById(id);
        } else {
            return that.findViewById(id);
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        if (that == null) {
            return super.getClassLoader();
        } else {
            return that.getClassLoader();
        }
    }


    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        if (that == null) {
            return super.getLayoutInflater();
        } else {
            return that.getLayoutInflater();
        }
    }

    @Override
    public Window getWindow() {
        if (that == null) {
            return super.getWindow();
        } else {
            return that.getWindow();
        }
    }

    @Override
    public WindowManager getWindowManager() {
        if (that == null) {
            return super.getWindowManager();
        } else {
            return that.getWindowManager();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (that == null){
            super.onCreate(savedInstanceState);
        } else {
            String userMsg = savedInstanceState.getString("userMsg");
            Log.e("tag", "get msg : " + userMsg);
        }
    }

    @Override
    public void onStart() {
        if (that == null){
            super.onStart();
        }
    }

    @Override
    public void onResume() {
        if (that == null){
            super.onResume();
        }
        Log.d("tag", "----onIResume-----");
    }

    @Override
    public void onPause() {
        if (that == null){
            super.onPause();
        }
        Log.d("tag", "----onIPause-----");
    }

    @Override
    public void onStop() {
        if (that == null){
            super.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if (that == null){
            super.onDestroy();
        }
        Log.d("tag", "----onDestroy-----");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (that == null){
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void onBackPressed() {
        if (that == null) {
            super.onBackPressed();
        }
    }

    @Override
    public void attach(Activity activity) {
        that = activity;
    }

    @Override
    public void startActivity(Intent intent){
        if (that == null){
            super.startActivity(intent);
        } else {
            Intent newIntent = new Intent();
            newIntent.putExtra("className", intent.getComponent().getClassName());
            newIntent.putExtra("data",intent.getBundleExtra("data"));
            that.startActivity(newIntent);
        }
    }
}
