package com.example.chen.plugtest;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.chen.alipaystander.AlipayInterface;

import java.lang.reflect.Constructor;

/**
 * @author Chenhong
 * @date 2018/11/28.
 * @des
 */
public class ProxyActivity extends AppCompatActivity {
    //要跳转到淘票票的Activity
    private String className;
    AlipayInterface mAlipayInterface;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        className = getIntent().getStringExtra("className");
        Bundle data = getIntent().getBundleExtra("data");
        try {
            Class activitClass = getClassLoader().loadClass(className);
            Constructor constructor = activitClass.getConstructor(new Class[]{});
            Object instance = constructor.newInstance(new Object[]{});
            //定义标准
            //传递生命周期
            mAlipayInterface = (AlipayInterface) instance;
            mAlipayInterface.attach(this);
            Bundle bundle;
            if (data == null) {
                bundle = new Bundle();
            } else  {
                bundle = data;
            }
            bundle.putString("userMsg","user msg");
            //可以传递信息
            mAlipayInterface.onCreate(bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAlipayInterface.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAlipayInterface.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAlipayInterface.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAlipayInterface.onStop();
    }

    @Override
    protected void onDestroy() {
        mAlipayInterface.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mAlipayInterface.onBackPressed();
    }


    /**
     * class文件 资源文件
     * @return
     */
    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getClassLoader();
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }

    @Override
    public void startActivity(Intent intent) {
        String className = intent.getStringExtra("className");
        Intent newIntent = new Intent(this, ProxyActivity.class);
        newIntent.putExtra("className", className);
        newIntent.putExtra("data", intent.getBundleExtra("data"));
        super.startActivity(newIntent);
    }
}
