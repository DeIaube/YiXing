package arouter.dawn.zju.edu.module_mine.ui.feedback;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_mine.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_SETTING_FEEDBACK)
public class FeedbackActivity extends BaseActivity {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void onBackPressed() {

    }
}
