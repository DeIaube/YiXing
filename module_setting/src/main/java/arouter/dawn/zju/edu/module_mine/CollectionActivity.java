package arouter.dawn.zju.edu.module_mine;


import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.List;

import baselib.base.BaseActivity;
import baselib.bean.Goods;
import baselib.callback.GoodsListRefreshCallback;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_SETTING_COLLECTION)
public class CollectionActivity extends BaseActivity<CollectionContract.Presenter> implements CollectionContract.View {

    GoodsListRefreshCallback refreshCallback;

    @Override
    protected void initView() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(Constants.AROUTER_NEARBY_GOODS_LIST).navigation();
        refreshCallback = (GoodsListRefreshCallback) fragment;
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
        mPresenter.refresh();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new CollectionPresenter();
    }

    @Override
    public void refresh(List<Goods> goodsList) {
        refreshCallback.refresh(goodsList);
    }
}
