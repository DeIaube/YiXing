package arouter.dawn.zju.edu.module_goods.ui.cart;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_nearby.R;
import baselib.base.BaseFragment;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_GOODS_CART)
public class CartFragment extends BaseFragment<CartContract.Presenter> implements CartContract.View {

    TextView toolTextTv;
    RecyclerView goodsListRv;
    Button submitBtn;
    AppCompatCheckBox allSelectCb;
    TextView priceTv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cart;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new CartPresenter();
    }

    @Override
    protected void initView(View view) {
        toolTextTv = view.findViewById(R.id.tool_text);
        goodsListRv = view.findViewById(R.id.goods_list);
        submitBtn = view.findViewById(R.id.submit);
        allSelectCb = view.findViewById(R.id.all_select);
        priceTv = view.findViewById(R.id.price);

        toolTextTv.setText("购物车");
    }

}
