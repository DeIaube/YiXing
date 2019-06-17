package yixing.dawn.zju.edu.yixing.ui.home;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;

import arouter.dawn.zju.edu.module_goods.util.PicassoUrlImageLeader;
import baselib.base2.BaseFragment;
import baselib.constants.RouteConstants;
import yixing.dawn.zju.edu.yixing.R;
import yixing.dawn.zju.edu.yixing.databinding.FragmentHomeBinding;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel>{

    @Override
    protected void init() {
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.container, (Fragment) ARouter.getInstance().build(RouteConstants.AROUTER_GOODS_RECOMMEND).navigation())
                .commit();
        viewModel.goodsListData.observe(getLifecycleOwner(), goods -> binding.homeBanner.setOnBannerListener(position -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable(RouteConstants.GOODS_DETAIL_GOODS, goods.get(position));
            ARouter.getInstance().build(RouteConstants.AROUTER_GOODS_DETAIL)
                    .withBundle(RouteConstants.GOODS_DETAIL_BUNDLE, bundle).navigation();
        }));
        viewModel.preViewListData.observe(getLifecycleOwner(), strings -> {
            binding.homeBanner.setImageLoader(new PicassoUrlImageLeader());
            binding.homeBanner.setImages(strings);
            binding.homeBanner.start();
        });
        viewModel.setData();
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.homeBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        binding.homeBanner.stopAutoPlay();
    }
}
