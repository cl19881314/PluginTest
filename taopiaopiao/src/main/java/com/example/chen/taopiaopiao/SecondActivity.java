package com.example.chen.taopiaopiao;

import android.os.Bundle;
import android.util.Log;

/**
 * @author Chenhong
 * @date 2018/11/28.
 * @des
 */
public class SecondActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        if (savedInstanceState != null){
            String key = savedInstanceState.getString("key");
            Log.e("tag","SecondActivity get key : " + key);
        }
    }
}
