package yixing.dawn.zju.edu.yixing.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;

import baselib.base2.BaseViewModel;

public class MainViewModel extends BaseViewModel<MainActivity, MainRepository> {
    public MainViewModel(@NonNull Application application, MainActivity view, MainRepository repository) {
        super(application, view, repository);
    }
}
