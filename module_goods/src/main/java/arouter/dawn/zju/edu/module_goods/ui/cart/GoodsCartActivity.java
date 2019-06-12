package arouter.dawn.zju.edu.module_goods.ui.cart;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.module_goods.adapter.CartGoodsListAdapter;
import arouter.dawn.zju.edu.module_nearby.R;
import baselib.base.BaseActivity;
import arouter.dawn.zju.edu.lib_net.bean.goods.Goods;
import baselib.constants.RouteConstants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 购物车页面
 */
@Route(path = RouteConstants.AROUTER_GOODS_CART)
public class GoodsCartActivity extends BaseActivity<GoodsCartContract.Presenter> implements GoodsCartContract.View, CartGoodsListAdapter.CartStatusChangeListener {

    RecyclerView goodsListRv;
    Button submitBtn;
    AppCompatCheckBox totalSelectCb;
    TextView priceTv;

    private CartGoodsListAdapter mAdapter;

    @Override
    protected void initView() {
        goodsListRv = findViewById(R.id.goods_list);
        submitBtn = findViewById(R.id.submit);
        totalSelectCb = findViewById(R.id.total_select);
        priceTv = findViewById(R.id.price);

        mAdapter = new CartGoodsListAdapter(this, new ArrayList<Goods>());
        mAdapter.setCartStatusChangeListener(this);
        goodsListRv.setLayoutManager(new LinearLayoutManager(this));
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
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_cart;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new GoodsCartPresenter();
    }

    @Override
    public void refresh(List<Goods> goodsList) {
        mAdapter.refresh(goodsList);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void totalPriceChange(double price) {
        priceTv.setText(String.format("￥%.2f", price));
    }
    
}
