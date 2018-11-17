package arouter.dawn.zju.edu.module_goods.ui.nearby;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import arouter.dawn.zju.edu.module_goods.adapter.NearbyPagerAdapter;
import arouter.dawn.zju.edu.module_goods.config.Constants;
import arouter.dawn.zju.edu.module_goods.ui.goods_list.GoodsGoodsListFragment;
import baselib.base.BasePresenter;
import arouter.dawn.zju.edu.lib_net.bean.Goods;
import baselib.util.LogUtil;

public class GoodsNearbyPresenter extends BasePresenter<GoodsNearbyContract.View> implements GoodsNearbyContract.Presenter {

    private static final String TAG = "NearbyPresenter";

    private Map<String, List<Goods>> mGoodsMap;
    private List<String> mTitles;
    private List<Fragment> mFragments;
    private int mCurrentSortType = Constants.SORT_COMPREHENSIVE;

    @SuppressLint("CheckResult")
    @Override
    public void bindViewPager(final FragmentManager fragmentManager,
                              final ViewPager viewPager, final TabLayout tabLayout, String location) {
        mGoodsMap = new HashMap<>();
        mGoodsMap.put(Constants.TYPE_ALL, new ArrayList<Goods>());
        mGoodsMap.put(Constants.TYPE_ART, new ArrayList<Goods>());
        mGoodsMap.put(Constants.TYPE_JOB, new ArrayList<Goods>());
        mGoodsMap.put(Constants.TYPE_LANGUAGE, new ArrayList<Goods>());
        mGoodsMap.put(Constants.TYPE_MUSIC, new ArrayList<Goods>());

        mTitles = new ArrayList<>();
        mTitles.add(Constants.TYPE_ALL);
        mTitles.add(Constants.TYPE_ART);
        mTitles.add(Constants.TYPE_JOB);
        mTitles.add(Constants.TYPE_LANGUAGE);
        mTitles.add(Constants.TYPE_MUSIC);

        mFragments = new ArrayList<>();
        mFragments.add(new GoodsGoodsListFragment());
        mFragments.add(new GoodsGoodsListFragment());
        mFragments.add(new GoodsGoodsListFragment());
        mFragments.add(new GoodsGoodsListFragment());
        mFragments.add(new GoodsGoodsListFragment());

        NearbyPagerAdapter adapter = new NearbyPagerAdapter(fragmentManager, mTitles, mFragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(mTitles.size());
        refresh(location);
    }

    @SuppressLint("CheckResult")
    @Override
    public void refresh(String location) {
        mView.showSwipeRefreshLayout();
        AVQuery<Goods> query = Goods.getQuery(Goods.class);
        query.whereEqualTo("city", location).findInBackground(new FindCallback<Goods>() {
            @Override
            public void done(List<Goods> list, AVException e) {
                mView.hideSwipeRefreshLayout();
                if (e == null) {
                    LogUtil.i(TAG, list.toString());
                    for (List<Goods> orderList : mGoodsMap.values()) {
                        orderList.clear();
                    }
                    for (Goods goods : list) {
                        Objects.requireNonNull(mGoodsMap.get(Constants.TYPE_ALL)).add(goods);
                        Objects.requireNonNull(mGoodsMap.get(goods.getType())).add(goods);
                    }
                    checkoutSortGoods(mCurrentSortType);
                } else {
                    LogUtil.e(TAG, e.toString());
                }
            }
        });
    }

    @Override
    public void checkoutSortGoods(int sortType) {
        mCurrentSortType = sortType;
        Comparator<Goods> comparator = null;
        if (sortType == Constants.SORT_COMPREHENSIVE) {
            comparator = new Comparator<Goods>() {
                @Override
                public int compare(Goods o1, Goods o2) {
                    return o1.getObjectId().compareTo(o2.getObjectId());
                }
            };
        } else if (sortType == Constants.SORT_PRICE_DOWN) {
            comparator = new Comparator<Goods>() {
                @Override
                public int compare(Goods o1, Goods o2) {
                    return (int)(o1.getPrice() * 100 )- (int)(o2.getPrice() * 100);
                }
            };
        } else if (sortType == Constants.SORT_PRICE_UP) {
            comparator = new Comparator<Goods>() {
                @Override
                public int compare(Goods o1, Goods o2) {
                    return (int)(o2.getPrice() * 100 )- (int)(o1.getPrice() * 100);
                }
            };
        } else if (sortType == Constants.SORT_DISTANCE) {
            comparator = new Comparator<Goods>() {
                @Override
                public int compare(Goods o1, Goods o2) {
                    return o1.getRegion().compareTo(o2.getRegion());
                }
            };
        }
        for (List<Goods> goodsList : mGoodsMap.values()) {
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
            if (fragment instanceof GoodsGoodsListFragment) {
                GoodsGoodsListFragment goodsListFragment = (GoodsGoodsListFragment) fragment;
                goodsListFragment.refresh(mGoodsMap.get(mTitles.get(i)));
            }
        }
    }

}
