package com.hfkj.redchildsupermarket;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/8 20:15
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/8$
 * @更新描述	${TODO}
 */

import android.app.Application;
import android.os.Environment;
import android.os.Process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Thread.setDefaultUncaughtExceptionHandler(new RedBaby());
    }

    private class RedBaby implements Thread.UncaughtExceptionHandler{

        //当全局有未抓取异常弹出时，该方法就会执行
        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            File errorFile = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), "error14.log");
            PrintStream err;
            try {
                err = new PrintStream(errorFile);
                //2.获取当前的错误信息，并将它保存到应用中，为下一次上传做准备
                ex.printStackTrace(err );
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Process.killProcess(Process.myPid());
        }

    }
}
