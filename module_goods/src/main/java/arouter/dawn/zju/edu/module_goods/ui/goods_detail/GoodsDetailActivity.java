package arouter.dawn.zju.edu.module_goods.ui.goods_detail;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.lib_net.bean.Goods;
import arouter.dawn.zju.edu.module_nearby.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_GOODS_DETAIL)
public class GoodsDetailActivity extends BaseActivity<GoodsDetailContract.Presenter> implements GoodsDetailContract.View {

    @Autowired(name = Constants.GOODS_DETAIL_BUNDLE)
    Bundle bundle;
    Goods goods;

    @Override
    protected void initView() {
        goods = bundle.getParcelable(Constants.GOODS_DETAIL_GOODS);
        setToolbarTitle(goods.getTitle());
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new GoodsDetailPresenter();
    }

}
