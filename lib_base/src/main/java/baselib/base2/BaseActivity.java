package baselib.base2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.Objects;

import baselib.util.Utils;

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity implements IView {

    protected ProgressDialog progressDialog;
    protected T binding;
    protected V viewModel;
    protected ViewModelProvider.Factory factory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            ARouter.getInstance().inject(this);
            binding = Utils.injectDataBinding(this, BaseActivity.class, 0, null, false);
            binding.setLifecycleOwner(this);
            if (binding != null) {
                setContentView(binding.getRoot());
                factory = new AppViewModelFactory(getApplication(), this);
                viewModel = ViewModelProviders.of(this, factory).get(Objects.requireNonNull(Utils.getGenericClass(this, getTypeClass(), 1)));
                Utils.injectDataBindingValue(binding, viewModel);
                Utils.injectDataBindingValue(binding, this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        init();
    }

    protected void init() {

    }

    public Class<?> getTypeClass() {
        return BaseActivity.class;
    }

    @Override
    public void showLoading(String tips) {
        runOnUiThread(() -> {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(BaseActivity.this);
                progressDialog.setTitle(TextUtils.isEmpty(tips) ? "正在加载..." : tips);
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
        runOnUiThread(() -> Toast.makeText(BaseActivity.this, tips, Toast.LENGTH_SHORT).show());
    }

    @Override
    public void finishView() {
        finish();
    }

    @Override
    public LifecycleOwner getLifecycleOwner() {
        return this;
    }
}
