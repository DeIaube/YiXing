package arouter.dawn.zju.edu.module_goods.ui.cart;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_nearby.R;
import baselib.base.BaseFragment;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_GOODS_CART)
public class CartFragment extends BaseFragment<CartContract.Presenter> implements CartContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cart;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected void initView(View view) {

    }

}
