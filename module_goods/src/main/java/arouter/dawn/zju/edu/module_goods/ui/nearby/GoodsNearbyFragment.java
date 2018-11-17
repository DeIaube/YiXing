package arouter.dawn.zju.edu.module_goods.ui.nearby;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.squareup.picasso.Picasso;
import com.zaaach.citypicker.CityPickerActivity;

import arouter.dawn.zju.edu.module_goods.config.Constants;
import arouter.dawn.zju.edu.module_nearby.R;
import baselib.base.BaseFragment;
import baselib.util.SPUtil;

import static android.app.Activity.RESULT_OK;

@Route(path = baselib.config.Constants.AROUTER_GOODS_NEARBY)
public class GoodsNearbyFragment extends BaseFragment<GoodsNearbyContract.Presenter> implements GoodsNearbyContract.View, View.OnClickListener {

    TextView searchTv;
    TextView loacationTv;
    ViewPager viewPager;
    TabLayout tabLayout;
    SwipeRefreshLayout refreshSrl;
    TextView comprehensiveSortTv;
    TextView priceSort;
    TextView distanceSortTv;
    ImageView priceSortUpIv;
    ImageView priceSortDownIv;

    private String mLocation;
    private int mCurrentSortType = 1;

    private static final int REQUEST_CODE_PICK_CITY = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goods_nearby;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new GoodsNearbyPresenter();
    }

    @Override
    protected void initView(View view) {
        searchTv = view.findViewById(R.id.near_search);
        loacationTv = view.findViewById(R.id.near_location);
        viewPager = view.findViewById(R.id.neary_view_pager);
        tabLayout = view.findViewById(R.id.neary_tab_layout);
        refreshSrl = view.findViewById(R.id.refresh_layout);
        comprehensiveSortTv = view.findViewById(R.id.comprehensive_sort);
        priceSort = view.findViewById(R.id.price_sort);
        distanceSortTv = view.findViewById(R.id.distence_sort);
        priceSortUpIv = view.findViewById(R.id.price_sort_up);
        priceSortDownIv = view.findViewById(R.id.price_sort_down);

        mCurrentSortType = Constants.SORT_COMPREHENSIVE;
        comprehensiveSortTv.setTextColor(getResources().getColor(R.color.colorPrimary));

        view.findViewById(R.id.comprehensive_sort).setOnClickListener(this);
        view.findViewById(R.id.price_sort_layout).setOnClickListener(this);
        view.findViewById(R.id.distence_sort).setOnClickListener(this);

        mLocation = SPUtil.getString(Constants.LAST_LOCATION, Constants.DEFAULT_LOCATION);

        refreshSrl.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        refreshSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refresh(mLocation);
            }
        });

        loacationTv.setText(mLocation);

        loacationTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(), CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);
            }
        });

        searchTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(baselib.config.Constants.AROUTER_GOODS_SEARCH).navigation();
            }
        });

        mPresenter.bindViewPager(getChildFragmentManager(), viewPager, tabLayout, mLocation);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK){
            if (data != null){
                mLocation = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                loacationTv.setText(mLocation);
                SPUtil.put(Constants.LAST_LOCATION, mLocation);
                mPresenter.refresh(mLocation);
            }
        }
    }

    @Override
    public void showSwipeRefreshLayout() {
        refreshSrl.setRefreshing(true);
    }

    @Override
    public void hideSwipeRefreshLayout() {
        refreshSrl.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.comprehensive_sort) {
            // 综合排序
            mCurrentSortType = Constants.SORT_COMPREHENSIVE;
        } else if (id == R.id.price_sort_layout) {
            // 价格排序
            mCurrentSortType = mCurrentSortType == Constants.SORT_PRICE_UP ? Constants.SORT_PRICE_DOWN : Constants.SORT_PRICE_UP;
        } else if (id == R.id.distence_sort) {
            // 距离排序
            mCurrentSortType = Constants.SORT_DISTANCE;
        }
        checkoutSort();
    }

    private void checkoutSort() {
        comprehensiveSortTv.setTextColor(getResources().getColor(R.color.grey));
        priceSort.setTextColor(getResources().getColor(R.color.grey));
        distanceSortTv.setTextColor(getResources().getColor(R.color.grey));
        Picasso.with(getContext()).load(R.drawable.default_up).into(priceSortUpIv);
        Picasso.with(getContext()).load(R.drawable.default_down).into(priceSortDownIv);
        if (mCurrentSortType == Constants.SORT_COMPREHENSIVE) {
            comprehensiveSortTv.setTextColor(getResources().getColor(R.color.colorPrimary));
        } else if (mCurrentSortType == Constants.SORT_DISTANCE) {
            distanceSortTv.setTextColor(getResources().getColor(R.color.colorPrimary));
        } else {
            priceSort.setTextColor(getResources().getColor(R.color.colorPrimary));
            if (mCurrentSortType == Constants.SORT_PRICE_UP) {
                Picasso.with(getContext()).load(R.drawable.select_up).into(priceSortUpIv);
            } else {
                Picasso.with(getContext()).load(R.drawable.select_down).into(priceSortDownIv);
            }
        }
        mPresenter.checkoutSortGoods(mCurrentSortType);
    }
}