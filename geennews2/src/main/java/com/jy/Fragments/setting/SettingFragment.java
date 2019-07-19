package com.jy.Fragments.setting;

import android.view.View;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.geennews2.R;
import com.jy.Base.BaseFragment;
import com.jy.utils.Constants;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingFragment extends BaseFragment {
    @BindView(R.id.img_checkbox)
    CheckBox imgCheckbox;
    @BindView(R.id.night_checkbox)
    CheckBox nightCheckbox;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        if (Constants.isNoIMg){
            imgCheckbox.setChecked(true);
        }else{
            imgCheckbox.setChecked(false);
        }
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected int getLayout() {
        return R.layout.setting_fragment;
    }

    @OnClick({R.id.img_checkbox, R.id.night_checkbox})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_checkbox:
                //如果是无图模式
                if (Constants.isNoIMg){
                    Constants.isNoIMg=false;
                }else{
                    Constants.isNoIMg=true;

                }
                break;
            case R.id.night_checkbox:
                if (!Constants.isNightMode){
                    Constants.isNightMode=true;
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else{
                    Constants.isNightMode=false;
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                }
                getActivity().recreate();
                break;
        }
    }
}
