package arouter.dawn.zju.edu.module_goods.ui.nearby;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_nearby.R;
import baselib.base.BaseActivity;
import baselib.constants.RouteConstants;

@Route(path = RouteConstants.AROUTER_GOODS_NEARBY_ACTIVITY)
public class GoodsNearbyActivity extends BaseActivity {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_nearby;
    }

    @Override
    protected void bindPresenter() {

    }

}
