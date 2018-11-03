package arouter.dawn.zju.edu.module_goods.ui.cart;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.module_goods.adapter.CartGoodsListAdapter;
import arouter.dawn.zju.edu.module_nearby.R;
import baselib.base.BaseFragment;
import baselib.bean.Goods;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_GOODS_CART)
public class CartFragment extends BaseFragment<CartContract.Presenter> implements CartContract.View {

    TextView toolTextTv;
    RecyclerView goodsListRv;
    Button submitBtn;
    AppCompatCheckBox totalSelectCb;
    TextView priceTv;

    private CartGoodsListAdapter mAdapter;

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
        totalSelectCb = view.findViewById(R.id.total_select);
        priceTv = view.findViewById(R.id.price);

        toolTextTv.setText("购物车");

        mAdapter = new CartGoodsListAdapter(getContext(), new ArrayList<Goods>());
        goodsListRv.setLayoutManager(new LinearLayoutManager(getContext()));
        goodsListRv.setAdapter(mAdapter);

        totalSelectCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mAdapter.totalGoodsStatusChange(isChecked);
            }
        });

        mPresenter.refresh();
    }

    @Override
    public void refresh(List<Goods> goodsList) {
        mAdapter.refresh(goodsList);
    }
}
