package com.example.chen.plugtest;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * @author Chenhong
 * @date 2018/11/28.
 * @des
 */
public class PluginManager {
    private static PluginManager ourInstance = null;
    private DexClassLoader mDexClassLoader;
    private Resources mResources;
    private Context mContext;
    private String mEntryActivityName;

    public static PluginManager getInstance() {
        if (ourInstance == null) {
            synchronized (PluginManager.class) {
                if (ourInstance == null) {
                    ourInstance = new PluginManager();
                }
            }
        }
        return ourInstance;
    }

    public void loadPath(String path) {

        File dexOutFile = mContext.getDir("dex", Context.MODE_PRIVATE);
        mDexClassLoader = new DexClassLoader(path, dexOutFile.getAbsolutePath(), null, mContext.getClassLoader());

        PackageManager packageManager = mContext.getPackageManager();
        PackageInfo info = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        mEntryActivityName = info.activities[0].name;

        //实例化Resources
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            //assetManager.addAssetPath("")
            Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, path);
            mResources = new Resources(assetManager, mContext.getResources().getDisplayMetrics(), mContext.getResources().getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Resources getResources() {
        return mResources;
    }

    public ClassLoader getClassLoader() {
        return mDexClassLoader;
    }

    public String getEntryActivityName() {
        return mEntryActivityName;
    }

    public void setContext(Context context) {
        mContext = context;
    }
}
