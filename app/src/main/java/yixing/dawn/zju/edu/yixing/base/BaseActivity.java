package yixing.dawn.zju.edu.yixing.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import yixing.dawn.zju.edu.yixing.R;

public abstract class BaseActivity <T extends BaseContract.BasePresenter> extends AppCompatActivity implements BaseContract.BaseView {

    protected T mPresenter;
    protected Toolbar mToolbar;

    private Unbinder mBind;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mBind = ButterKnife.bind(this);
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
        mBind.unbind();
        detachView();
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
        if (mToolbar == null) {
            throw new NullPointerException("toolbar can not be null");
        }
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(showHomeAsUp());
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
