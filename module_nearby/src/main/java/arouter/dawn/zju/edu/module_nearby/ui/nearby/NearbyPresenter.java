package arouter.dawn.zju.edu.module_nearby.ui.nearby;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import arouter.dawn.zju.edu.lib_net.Api;
import arouter.dawn.zju.edu.lib_net.ApiRequest;
import arouter.dawn.zju.edu.lib_net.bean.GoodsBean;
import arouter.dawn.zju.edu.lib_net.bean.network.SearchGoodsRespense;
import arouter.dawn.zju.edu.module_nearby.adapter.NearbyPagerAdapter;
import arouter.dawn.zju.edu.module_nearby.config.Constants;
import arouter.dawn.zju.edu.module_nearby.ui.GoodsListFragment;
import baselib.base.BasePresenter;
import baselib.util.LogUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NearbyPresenter extends BasePresenter<NearbyContract.View> implements NearbyContract.Presenter {

    private static final String TAG = "NearbyPresenter";

    private Map<String, List<GoodsBean>> mGoodsMap;
    private List<String> mTitles;
    private List<Fragment> mFragments;

    @SuppressLint("CheckResult")
    @Override
    public void bindViewPager(final FragmentManager fragmentManager, final ViewPager viewPager, final TabLayout tabLayout) {
        mGoodsMap = new HashMap<>();
        mGoodsMap.put(Constants.TYPE_ALL, new ArrayList<GoodsBean>());
        mGoodsMap.put(Constants.TYPE_ART, new ArrayList<GoodsBean>());
        mGoodsMap.put(Constants.TYPE_JOB, new ArrayList<GoodsBean>());
        mGoodsMap.put(Constants.TYPE_LANGUAGE, new ArrayList<GoodsBean>());
        mGoodsMap.put(Constants.TYPE_MUSIC, new ArrayList<GoodsBean>());

        mTitles = new ArrayList<>();
        mTitles.add(Constants.TYPE_ALL);
        mTitles.add(Constants.TYPE_ART);
        mTitles.add(Constants.TYPE_JOB);
        mTitles.add(Constants.TYPE_LANGUAGE);
        mTitles.add(Constants.TYPE_MUSIC);

        mFragments = new ArrayList<>();
        mFragments.add(new GoodsListFragment());
        mFragments.add(new GoodsListFragment());
        mFragments.add(new GoodsListFragment());
        mFragments.add(new GoodsListFragment());

        NearbyPagerAdapter adapter = new NearbyPagerAdapter(fragmentManager, mTitles, mFragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        refresh();
    }

    @SuppressLint("CheckResult")
    @Override
    public void refresh() {
        mView.showLoading();
        Api api = ApiRequest.getSingle().getApi();
        api.search()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SearchGoodsRespense>() {
                    @Override
                    public void accept(SearchGoodsRespense searchGoodsRespense) throws Exception {
                        LogUtil.i(TAG, searchGoodsRespense.toString());
                        mView.hideLoading();
                        List<GoodsBean> orders = searchGoodsRespense.getData();
                        for (List<GoodsBean> orderList : mGoodsMap.values()) {
                            orderList.clear();
                        }
                        for (GoodsBean goods : orders) {
                            Objects.requireNonNull(mGoodsMap.get(Constants.TYPE_ALL)).add(goods);
                            Objects.requireNonNull(mGoodsMap.get(goods.getType())).add(goods);
                        }
                        refreshGoodsListFragment();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.e(TAG, throwable.toString());
                        mView.hideLoading();
                        mView.showNetworkError();
                    }
                });
    }

    /**
     * 通过现有数据刷新ViewPager下所有页面
     */
    private void refreshGoodsListFragment() {
        for (int i = 0; i < mFragments.size(); i++) {
            Fragment fragment = mFragments.get(i);
            if (fragment instanceof GoodsListFragment) {
                GoodsListFragment goodsListFragment = (GoodsListFragment) fragment;
                goodsListFragment.refresh(mGoodsMap.get(mTitles.get(i)));
            }
        }
    }

}
