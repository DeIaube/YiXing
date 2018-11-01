package arouter.dawn.zju.edu.module_mine;


import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import baselib.base.BaseActivity;
import baselib.callback.GoodsListRefreshCallback;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_SETTING_COLLECTION)
public class CollectionActivity extends BaseActivity {

    GoodsListRefreshCallback refreshCallback;

    @Override
    protected void initView() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(Constants.AROUTER_NEARBY_GOODS_LIST).navigation();
        refreshCallback = (GoodsListRefreshCallback) fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    protected void bindPresenter() {

    }

}
