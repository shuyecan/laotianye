package com.laotian.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class Permission extends Activity {//权限默认请求个数
    public static final int PERMISSION_SIZE = 0;

    //权限默认请求个数
    public static final int PERMISSION_REQUEST_CODE = 321;

    //上下文
    private Activity activity;

    //权限集合
    private String[] permissions;

    //静态参数
    public static Permission mInstance;

    /**
     * 有参构造方法
     * @param activity
     * @param permissions
     */
    public Permission(Activity activity, String[] permissions){
        Log.i(activity + "", "permission");
        this.activity = activity;
        this.permissions = permissions;
    }

    /**
     * 实例化当前对象
     * @return
     */
    public static Permission getmInstance(Activity activity, String[] permissions){
        //提升效率。防止重复创建
        if(mInstance == null){
            //加锁
            synchronized (Permission.class){
                //实例化对象
                if(mInstance == null)
                    mInstance = new Permission(activity, permissions);
            }
        }
        return mInstance;
    }

    /**
     * checkpermission
     * true==尚未获取
     * false==已获取
     * @param activity
     * @param permissions
     */
    public static boolean check_permission(Activity activity, String[] permissions){
        if(permissions.length == PERMISSION_SIZE)
            return false;
        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for(int i = 0; i < permissions.length; i++){
                // 检查该权限是否已经获取
                int j = ContextCompat.checkSelfPermission(activity, permissions[i]);
                // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
                if (j != PackageManager.PERMISSION_GRANTED) {
                    start_permission(activity, permissions);
                }else{
                    start_permission(activity, permissions);
                }
            }
        }
        return false;
    }

    /**
     * requestpermission
     * @param activity
     * @param permissions
     */
    public static void start_permission(Activity activity, String[] permissions){
        if(permissions.length == PERMISSION_SIZE)
            return;
        //请求权限
        ActivityCompat.requestPermissions(activity, permissions, PERMISSION_REQUEST_CODE);
    }

}
