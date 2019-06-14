package baselib.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.alibaba.android.arouter.launcher.ARouter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Objects;

import arouter.dawn.zju.edu.lib_res.R;
import baselib.bus.BusEvent;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public abstract class BaseActivity <T extends BaseContract.BasePresenter> extends AppCompatActivity implements BaseContract.BaseView {

    protected T mPresenter;
    protected Toolbar mToolbar;

    private ProgressDialog mLoadingView;

    /**
     * 显示进度条
     */
    @Override
    public void showLoading() {
        if (mLoadingView == null) {
            mLoadingView = new ProgressDialog(this);
            mLoadingView.setCancelable(false);
        }
        mLoadingView.show();
    }

    /**
     * 隐藏进度条
     */
    @Override
    public void hideLoading() {
        if (mLoadingView != null) {
            mLoadingView.hide();
        }
    }

    @Override
    public void showNetworkError() {
        showMessage(getString(R.string.no_network_connection));
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    protected abstract void initView();

    protected abstract int getLayoutId();

    protected abstract void bindPresenter();

    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ARouter.getInstance().inject(this);
        if (useEventBus()) {
            EventBus.getDefault().register(this);
        }
        initToolBar();
        bindPresenter();
        attachView();
        initView();
    }

    /**
     * 销毁Activity进行资源解绑
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachView();
        if (mLoadingView != null) {
            mLoadingView.dismiss();
        }
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    protected void handleEvent(BusEvent event) {

    }

    /**
     * 设置Toolbar的title
     */
    protected void setToolbarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    /**
     * @return 是否显示返回键
     */
    protected boolean showHomeAsUp() {
        return false;
    }

    /**
     * 初始化Toolbar
     */
    private void initToolBar() {
        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(showHomeAsUp());
        }
    }

    /**
     * 绑定View
     */
    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 解除绑定View
     */
    private void detachView() {
        if (mPresenter != null) {
            mPresenter.attachView(null);
        }
    }
}
