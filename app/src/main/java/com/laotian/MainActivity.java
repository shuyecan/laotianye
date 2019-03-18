package com.laotian;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.laotian.contract.MainContract;
import com.laotian.presenter.MainPresenter;
import com.laotian.util.Permission;

import butterknife.BindView;
import butterknife.ButterKnife;
import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    @BindView(R.id.tv_dizhi)
    TextView tvDizhi;
    private MainContract.Presenter mpresenter;
    private String[] permissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new MainPresenter(this);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mpresenter.getWeather(MainActivity.this,"",Lang.CHINESE_SIMPLIFIED,Unit.METRIC);
        Permission.getmInstance(MainActivity.this,permissions).check_permission(MainActivity.this,permissions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == Permission.PERMISSION_REQUEST_CODE){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i]==PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                    }else if(grantResults[i]==PackageManager.PERMISSION_DENIED){
                        boolean b = shouldShowRequestPermissionRationale(permissions[i]);
                        if(!b){
                            Toast.makeText(this, "拒绝且不再提示", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(this, "下次提示", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void showWeather(String data) {
        tvDizhi.setText(data);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mpresenter = presenter;
    }

}
