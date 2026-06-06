package dev.allofus.fusioncore;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.view.Display;


import org.jetbrains.annotations.Nullable;

import java.io.File;

public class CustomContextWrapper extends ContextWrapper {
    private final Context fusionContext;
    private final ApplicationInfo applicationInfo;

    public CustomContextWrapper(Context gameContext, Context fusionContext) {
        super(gameContext);
        this.fusionContext = fusionContext;

        this.applicationInfo = new ApplicationInfo(gameContext.getApplicationInfo());
        this.applicationInfo.dataDir = fusionContext.getApplicationInfo().dataDir;
        // this prevents the game from resolving its own libraries
        // that way we can override them properly with our own versions
        this.applicationInfo.nativeLibraryDir = "";
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        return applicationInfo;
    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        return this.fusionContext.getSharedPreferences(name, mode);
    }

    @Override
    public boolean deleteSharedPreferences(String name) {
        return this.fusionContext.deleteSharedPreferences(name);
    }

    @Override
    public boolean moveSharedPreferencesFrom(Context sourceContext, String name) {
        return this.fusionContext.moveSharedPreferencesFrom(sourceContext, name);
    }

    @Override
    public File getDataDir() {
        return this.fusionContext.getDataDir();
    }

    @Override
    public File getFilesDir() {
        return this.fusionContext.getFilesDir();
    }

    @Override
    public File getNoBackupFilesDir() {
        return this.fusionContext.getNoBackupFilesDir();
    }

    @Override
    public File getCacheDir() {
        return this.fusionContext.getCacheDir();
    }

    @Override
    public File getCodeCacheDir() {
        return this.fusionContext.getCodeCacheDir();
    }

    @Override
    public File getDir(String name, int mode) {
        return this.fusionContext.getDir(name, mode);
    }

    @Nullable
    @Override
    public File getExternalCacheDir() {
        return this.fusionContext.getExternalCacheDir();
    }


    @Override
    public File[] getExternalCacheDirs() {
        return this.fusionContext.getExternalCacheDirs();
    }

    @Override
    public File getExternalFilesDir(String type) {
        return this.fusionContext.getExternalFilesDir(type);
    }

    @Override
    public File[] getExternalFilesDirs(String type) {
        return this.fusionContext.getExternalFilesDirs(type);
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
    public Context getApplicationContext() {
        Context applicationContext = fusionContext.getApplicationContext();
        return applicationContext != null ? applicationContext : fusionContext;
    }

    @Override
    public File getObbDir() {
        return this.fusionContext.getObbDir();
    }

    @Override
    public File[] getObbDirs() {
        return this.fusionContext.getObbDirs();
    }
}
