package yixing.dawn.zju.edu.yixing.ui.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.goods.Goods;
import arouter.dawn.zju.edu.module_goods.util.PicassoUrlImageLeader;
import baselib.base.BaseFragment;
import baselib.config.Constants;
import yixing.dawn.zju.edu.yixing.R;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class HomeFragment extends BaseFragment<HomeContract.Presenter> implements HomeContract.View, View.OnClickListener {

    Banner banner;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new HomePresenter();
    }

    @Override
    protected void initView(View view) {
        banner = view.findViewById(R.id.home_banner);

        view.findViewById(R.id.home_scan).setOnClickListener(this);
        view.findViewById(R.id.home_search).setOnClickListener(this);
        view.findViewById(R.id.home_notice).setOnClickListener(this);

        mPresenter.refreshHomeView();

        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.container, (Fragment) ARouter.getInstance().build(Constants.AROUTER_GOODS_RECOMMEND).navigation())
                .commit();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.home_scan) {
            // todo 扫描二维码
        } else if (id == R.id.home_search) {
            ARouter.getInstance().build(Constants.AROUTER_GOODS_SEARCH).navigation();
        } else if (id == R.id.home_notice) {
            ARouter.getInstance().build(Constants.AROUTER_SETTING_NOTICE).navigation();
        }
    }

    @Override
    public void refreshHomeView(final List<Goods> goodsList, List<String> preView) {
        banner.setImageLoader(new PicassoUrlImageLeader());
        banner.setImages(preView);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constants.GOODS_DETAIL_GOODS, goodsList.get(position));
                ARouter.getInstance().build(Constants.AROUTER_GOODS_DETAIL)
                        .withBundle(Constants.GOODS_DETAIL_BUNDLE, bundle).navigation();
            }
        });
        banner.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }
}
