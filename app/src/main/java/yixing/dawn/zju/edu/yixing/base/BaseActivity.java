package yixing.dawn.zju.edu.yixing.base;

import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity <T extends BaseContract.BasePresenter> extends AppCompatActivity implements BaseContract.BaseView {

}
