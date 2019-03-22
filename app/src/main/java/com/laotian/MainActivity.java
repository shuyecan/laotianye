package com.laotian;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.laotian.activity.MainFragment;
import com.laotian.adapter.MainfagmentAdapter;
import com.laotian.contract.MainContract;
import com.laotian.presenter.MainPresenter;
import com.laotian.util.Permission;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.view_main)
    ViewPager viewMain;
    private String[] permissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private List<Fragment> fragmentList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Permission.getmInstance(MainActivity.this, permissions).check_permission(MainActivity.this, permissions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Permission.PERMISSION_REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        MainFragment mainFragment = MainFragment.newInstance("auto_ip");
                        fragmentList.add(mainFragment);
                        MainFragment mainFragment2 = MainFragment.newInstance("沅陵");
                        fragmentList.add(mainFragment2);
                        viewMain.setAdapter(new MainfagmentAdapter(getSupportFragmentManager(), fragmentList));
                    } else if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean b = shouldShowRequestPermissionRationale(permissions[i]);
                        if (!b) {
                            Toast.makeText(this, "拒绝且不再提示", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "下次提示", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }

}
