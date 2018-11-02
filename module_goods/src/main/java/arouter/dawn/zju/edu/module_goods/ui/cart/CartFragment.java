package arouter.dawn.zju.edu.module_goods.ui.cart;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_nearby.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_GOODS_CART)
public class CartActivity extends BaseActivity<CartContract.Presenter> implements CartContract.View {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cart;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
