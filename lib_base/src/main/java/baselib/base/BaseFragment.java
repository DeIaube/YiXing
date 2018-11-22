package baselib.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Objects;

import baselib.bus.BusEvent;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public abstract class BaseFragment<T extends BaseContract.BasePresenter> extends Fragment implements BaseContract.BaseView{

    protected T mPresenter;

    private View mRootView;

    protected boolean isCreated = false;

    protected abstract int getLayoutId();

    protected abstract void bindPresenter();

    protected abstract void initView(View view);

    protected boolean useEventBus() {
        return false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        if (useEventBus()) {
            EventBus.getDefault().register(this);
        }
        bindPresenter();
        attachView();
        isCreated = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isCreated) {
            return;
        }
        if (isVisibleToUser) {
            multipleFreshView();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        multipleFreshView();
    }

    protected void multipleFreshView() {

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
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    protected void handleEventinMainThread(BusEvent event) {

    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    protected void handleEventinAsync(BusEvent event) {

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
