package com.example.chen.alipaystander;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * @author Chenhong
 * @date 2018/11/28.
 * @des
 */
public interface AlipayInterface {
    void onCreate(Bundle savedInstanceState);
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
    void onSaveInstanceState(Bundle outState);
    boolean onTouchEvent(MotionEvent event);
    void onBackPressed();

    /**
     * 需要支付宝注入给淘票票上下文
     */
    void attach(Activity activity);
}