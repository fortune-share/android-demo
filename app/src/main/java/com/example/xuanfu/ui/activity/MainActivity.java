package com.example.xuanfu.ui.activity;

import android.os.Bundle;

import com.example.xuanfu.BR;
import com.example.xuanfu.R;
import com.example.xuanfu.databinding.ActivityMainBinding;
import com.example.xuanfu.ui.model.MainViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
