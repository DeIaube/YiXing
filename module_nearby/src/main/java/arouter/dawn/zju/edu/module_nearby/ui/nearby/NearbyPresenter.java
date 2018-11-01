package arouter.dawn.zju.edu.module_nearby.ui.nearby;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import arouter.dawn.zju.edu.module_nearby.ui.goods_list.GoodsListFragment;
import baselib.base.BasePresenter;
import baselib.bean.AVGoods;
import baselib.util.LogUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NearbyPresenter extends BasePresenter<NearbyContract.View> implements NearbyContract.Presenter {

    private static final String TAG = "NearbyPresenter";

    private Map<String, List<AVGoods>> mGoodsMap;
    private List<String> mTitles;
    private List<Fragment> mFragments;
    private int mCurrentSortType = Constants.SORT_COMPREHENSIVE;

    @SuppressLint("CheckResult")
    @Override
    public void bindViewPager(final FragmentManager fragmentManager, final ViewPager viewPager, final TabLayout tabLayout) {
        mGoodsMap = new HashMap<>();
        mGoodsMap.put(Constants.TYPE_ALL, new ArrayList<AVGoods>());
        mGoodsMap.put(Constants.TYPE_ART, new ArrayList<AVGoods>());
        mGoodsMap.put(Constants.TYPE_JOB, new ArrayList<AVGoods>());
        mGoodsMap.put(Constants.TYPE_LANGUAGE, new ArrayList<AVGoods>());
        mGoodsMap.put(Constants.TYPE_MUSIC, new ArrayList<AVGoods>());

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
        mFragments.add(new GoodsListFragment());

        NearbyPagerAdapter adapter = new NearbyPagerAdapter(fragmentManager, mTitles, mFragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(mTitles.size());
        refresh();
    }

    @SuppressLint("CheckResult")
    @Override
    public void refresh() {
        mView.showSwipeRefreshLayout();
        AVQuery<AVGoods> query = AVGoods.getQuery(AVGoods.class);
        query.findInBackground(new FindCallback<AVGoods>() {
            @Override
            public void done(List<AVGoods> list, AVException e) {
                mView.hideSwipeRefreshLayout();
                if (e == null) {
                    LogUtil.i(TAG, list.toString());
                    for (List<AVGoods> orderList : mGoodsMap.values()) {
                        orderList.clear();
                    }
                    for (AVGoods goods : list) {
                        mGoodsMap.get(Constants.TYPE_ALL).add(goods);
                        mGoodsMap.get(goods.getType()).add(goods);
                    }
                    checkoutSortGoods(mCurrentSortType);
                } else {
                    LogUtil.i(TAG, e.toString());
                }
            }
        });
    }

    @Override
    public void checkoutSortGoods(int sortType) {
        mCurrentSortType = sortType;
        Comparator<AVGoods> comparator = null;
        if (sortType == Constants.SORT_COMPREHENSIVE) {
            comparator = new Comparator<AVGoods>() {
                @Override
                public int compare(AVGoods o1, AVGoods o2) {
                    return o1.getObjectId().compareTo(o2.getObjectId());
                }
            };
        } else if (sortType == Constants.SORT_PRICE_DOWN) {
            comparator = new Comparator<AVGoods>() {
                @Override
                public int compare(AVGoods o1, AVGoods o2) {
                    return o1.getPrice() - o2.getPrice();
                }
            };
        } else if (sortType == Constants.SORT_PRICE_UP) {
            comparator = new Comparator<AVGoods>() {
                @Override
                public int compare(AVGoods o1, AVGoods o2) {
                    return o2.getPrice() - o1.getPrice();
                }
            };
        } else if (sortType == Constants.SORT_DISTANCE) {
            comparator = new Comparator<AVGoods>() {
                @Override
                public int compare(AVGoods o1, AVGoods o2) {
                    return 1;
                }
            };
        }
        for (List<AVGoods> goodsList : mGoodsMap.values()) {
            Collections.sort(goodsList, comparator);
        }
        refreshGoodsListFragment();
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
