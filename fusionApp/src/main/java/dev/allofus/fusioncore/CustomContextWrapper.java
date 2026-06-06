package dev.allofus.fusioncore;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.Display;


import org.jetbrains.annotations.Nullable;

import java.io.File;

public class CustomContextWrapper extends ContextWrapper {
    Context fusionContext;

    public CustomContextWrapper(Context gameContext, Context fusionContext, Context appContext) {
        super(gameContext);
        this.fusionContext = fusionContext;
        this.getApplicationInfo().dataDir = fusionContext.getApplicationInfo().dataDir;
        // this prevents the game from resolving its own libraries
        // that way we can override them properly with our own versions
        this.getApplicationInfo().nativeLibraryDir = "";
    }

//    @Override
//    public Resources getResources() {
//        return this.appContext.getResources();
//    }


//    @Override
//    public ApplicationInfo getApplicationInfo() {
//        return super.getApplicationInfo();
//    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        return this.fusionContext.getSharedPreferences(name, mode);
    }

    public boolean deleteSharedPreferences(String name) {
        return this.fusionContext.deleteSharedPreferences(name);
    }

    public boolean moveSharedPreferencesFrom(Context sourceContext, String name) {
        return this.fusionContext.moveSharedPreferencesFrom(sourceContext, name);
    }

    @Override
    public File getFilesDir() {
        return this.fusionContext.getFilesDir();
    }

    @Override
    public File getCacheDir() {
        return this.fusionContext.getCacheDir();
    }

    @Nullable
    @Override
    public File getExternalCacheDir() {
        return super.getExternalCacheDir();
    }


    @Override
    public File[] getExternalCacheDirs() {
        return super.getExternalCacheDirs();
    }

    @Override
    public File getExternalFilesDir(String type) {
        return super.getExternalFilesDir(type);
    }

    @Override
    public Display getDisplay() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return this.fusionContext.getDisplay();
        }
        return null;
    }

    @Override
    public Object getSystemService(String name) {
        return this.fusionContext.getSystemService(name);
    }

    @Override
    public Context getBaseContext() {
        return super.getBaseContext();
    }

    @Override
    public Context getApplicationContext() {
        return this;
    }

    @Override
    public File getObbDir() {
        return super.getObbDir();
    }

    @Override
    public File[] getObbDirs() {
        return super.getObbDirs();
    }
}
