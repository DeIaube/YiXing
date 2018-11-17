package arouter.dawn.zju.edu.module_goods.ui.goods_detail;

import arouter.dawn.zju.edu.module_nearby.R;
import baselib.base.BaseActivity;

public class GoodsDetailActivity extends BaseActivity<GoodsDetailContract.Presenter> implements GoodsDetailContract.View {

    @Override
    protected void initView() {

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
