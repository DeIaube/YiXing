package baselib.base2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.Objects;

import baselib.util.Utils;

import static baselib.util.MainLooperUtil.runOnUiThread;

public class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment implements IView{

    protected ProgressDialog progressDialog;
    protected T binding;
    protected V viewModel;
    protected ViewModelProvider.Factory factory;

    @Nullable
    @Override
    @CallSuper
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            ARouter.getInstance().inject(this);
            binding = Utils.injectDataBinding(this, getTypeClass(), 0, null, false);

            if (binding != null) {
                binding.setLifecycleOwner(this);
                return binding.getRoot();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void init() {

    }

    protected void bindEvent() {

    }

    public Class<?> getTypeClass() {
        return BaseFragment.class;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            if (binding != null) {
                factory = new AppViewModelFactory(Objects.requireNonNull(getActivity()).getApplication(), this);
                viewModel = ViewModelProviders.of(getActivity(), factory).get(Objects.requireNonNull(Utils.getGenericClass(this, getTypeClass(), 1)));
                Utils.injectDataBindingValue(binding, viewModel);
                Utils.injectDataBindingValue(binding, this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        init();
        bindEvent();
    }


    @Override
    public void showLoading(String tips) {
        runOnUiThread(() -> {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(getContext());
            }
            if (progressDialog.isShowing()) {
                return;
            }
            progressDialog.show();
        });
    }

    @Override
    public void hideLoading() {
        runOnUiThread(() -> {
            if (progressDialog == null || !progressDialog.isShowing()) {
                return;
            }
            progressDialog.hide();
        });
    }

    @Override
    public void showErrorView(String errorMsg) {
        makeToast(errorMsg);
    }

    @Override
    public void makeToast(String tips) {
        runOnUiThread(() -> Toast.makeText(getContext(), tips, Toast.LENGTH_SHORT).show());
    }

    @Override
    public void finishView() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    @Override
    public LifecycleOwner getLifecycleOwner() {
        return this;
    }
}
