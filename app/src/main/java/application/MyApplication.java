package application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.umeng.socialize.PlatformConfig;

import org.xutils.x;

import cn.smssdk.SMSSDK;
import utils.ImageLoaderUtils;

/**
 * Created by asus on 2016/11/28.
 */
public class MyApplication extends Application {
    private static Context context;
    private static Handler handler;
    private static int mainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();
        //获取当前应用的上下文
        context = getApplicationContext();
        handler = new Handler();
        //获取主线程的线程号
        mainThreadId= Process.myTid();
        //imageLoader初始化
        ImageLoaderUtils.initConfiguration(this);
        //xutils3初始化配置
        x.Ext.init(this);
        //设置是debug模式
        x.Ext.setDebug(true);
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        SMSSDK.initSDK(this, "18e9e2fd8c888 ", "d1eb6e48dfd77ed6a22793058968ceed");
    }
    public static int getMainThreadId(){
        return mainThreadId;
    }
    public static Handler getHandler() {
        return handler;
    }
    /**
     * 获取主线程
     * @return
     */
    public static Thread getMainThread(){
        return Thread.currentThread();
    }

    /**
     * 获取整个应用的上下文
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }
}
