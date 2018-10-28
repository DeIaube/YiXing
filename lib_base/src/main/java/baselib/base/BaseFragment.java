package baselib.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.Objects;

public abstract class BaseFragment<T extends BaseContract.BasePresenter> extends Fragment implements BaseContract.BaseView{

    protected T mPresenter;

    private View mRootView;

    protected abstract int getLayoutId();

    protected abstract void bindPresenter();

    protected abstract void initView(View view);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        bindPresenter();
        attachView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
        }
        initView(mRootView);
        return mRootView;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        detachView();
    }

    /**
     * 贴上view
     */
    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void showLoading() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity)getActivity()).showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity)getActivity()).hideLoading();
        }
    }

    @Override
    public void showMessage(String msg) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity)getActivity()).showMessage(msg);
        }
    }

    @Override
    public void showNetworkError() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity)getActivity()).showNetworkError();
        }
    }

    /**
     * 分离view
     */
    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void finish() {
        Objects.requireNonNull(getActivity()).onBackPressed();
    }
}
