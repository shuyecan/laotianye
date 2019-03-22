package com.laotian.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.laotian.R;
import com.laotian.contract.MainContract;
import com.laotian.presenter.MainPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;
import interfaces.heweather.com.interfacesmodule.bean.basic.Basic;
import interfaces.heweather.com.interfacesmodule.bean.basic.Update;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.NowBase;

public class MainFragment extends Fragment implements MainContract.View {

    @BindView(R.id.main_txt_address)
    TextView mainTxtAddress;
    Unbinder unbinder;
    @BindView(R.id.main_txt_time)
    TextView mainTxtTime;
    @BindView(R.id.mian_img_city)
    ImageView mianImgCity;
    @BindView(R.id.mian_img_more)
    ImageView mianImgMore;

    public static MainFragment newInstance(String location) {
        MainFragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("location", location);
        fragment.setArguments(bundle);
        return fragment;
    }

    private View view;
    private MainContract.Presenter mpresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_mainfragment, container, false);
            init();
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (null != parent) {
                parent.removeView(view);
            }
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    private void init() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String location = bundle.getString("location");
            mpresenter.getWeather(getActivity(), location, Lang.CHINESE_SIMPLIFIED, Unit.METRIC);
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MainPresenter(MainFragment.this);
    }

    @Override
    public void showWeather(List<Now> data) {
        Basic base = data.get(0).getBasic();
        NowBase now = data.get(0).getNow();
        Update update = data.get(0).getUpdate();
        mainTxtAddress.setText(base.getLocation());
        mpresenter.getTime(update.getLoc());
    }

    @Override
    public void showtime(String time) {
        mainTxtTime.setText(time);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mpresenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.mian_img_city, R.id.mian_img_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mian_img_city:
                break;
            case R.id.mian_img_more:
                PopupMenu pop=new PopupMenu (getActivity(),mianImgMore);
                MenuInflater inflater = pop.getMenuInflater();
                inflater.inflate(R.menu.main_menu, pop.getMenu());
                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                });
                pop.show();
                break;
        }
    }
}
