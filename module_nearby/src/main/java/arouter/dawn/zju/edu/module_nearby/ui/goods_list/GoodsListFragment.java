package arouter.dawn.zju.edu.module_nearby.ui.goods_list;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.GoodsBean;
import arouter.dawn.zju.edu.module_nearby.R;
import arouter.dawn.zju.edu.module_nearby.adapter.GoodsListAdapter;
import baselib.base.BaseFragment;

public class GoodsListFragment extends BaseFragment<GoodsListContract.Presenter> implements GoodsListContract.View, View.OnClickListener {

    private static final int SORT_COMPREHENSIVE = 1;
    private static final int SORT_PRICE_UP = 2;
    private static final int SORT_PRICE_DOWN = 3;
    private static final int SORT_DISTANCE = 4;

    RecyclerView recyclerView;
    TextView comprehensiveSortTv;
    TextView priceSort;
    TextView distanceSortTv;
    ImageView priceSortUpIv;
    ImageView priceSortDownIv;

    private List<GoodsBean> mGoodsList;
    private GoodsListAdapter mAdapter;
    private int mCurrentSortType = 1;

    public void refresh(List<GoodsBean> goodsList) {
        mGoodsList = goodsList;
        mAdapter.refresh(mGoodsList);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goods_list;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new GoodsListPresenter();
    }

    @Override
    protected void initView(View view) {
        comprehensiveSortTv = view.findViewById(R.id.comprehensive_sort);
        priceSort = view.findViewById(R.id.price_sort);
        distanceSortTv = view.findViewById(R.id.distence_sort);
        recyclerView = view.findViewById(R.id.goods_list_view);
        priceSortUpIv = view.findViewById(R.id.price_sort_up);
        priceSortDownIv = view.findViewById(R.id.price_sort_down);

        view.findViewById(R.id.comprehensive_sort).setOnClickListener(this);
        view.findViewById(R.id.price_sort_layout).setOnClickListener(this);
        view.findViewById(R.id.distence_sort).setOnClickListener(this);

        mGoodsList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new GoodsListAdapter(mGoodsList, getContext());
        recyclerView.setAdapter(mAdapter);
    }

    private void checkoutSort(int sortComprehensive) {
        if (sortComprehensive == mCurrentSortType) {
            return;
        }
        mCurrentSortType = sortComprehensive;
        comprehensiveSortTv.setTextColor(getResources().getColor(R.color.grey));
        priceSort.setTextColor(getResources().getColor(R.color.grey));
        distanceSortTv.setTextColor(getResources().getColor(R.color.grey));
        Picasso.with(getContext()).load(R.drawable.default_up).into(priceSortUpIv);
        Picasso.with(getContext()).load(R.drawable.default_down).into(priceSortDownIv);
        if (mCurrentSortType == SORT_COMPREHENSIVE) {
            comprehensiveSortTv.setTextColor(getResources().getColor(R.color.colorPrimary));
        } else if (mCurrentSortType == SORT_DISTANCE) {
            distanceSortTv.setTextColor(getResources().getColor(R.color.colorPrimary));
        } else {
            priceSort.setTextColor(getResources().getColor(R.color.colorPrimary));
            if (mCurrentSortType == SORT_PRICE_UP) {
                Picasso.with(getContext()).load(R.drawable.select_up).into(priceSortUpIv);
            } else {
                Picasso.with(getContext()).load(R.drawable.select_down).into(priceSortDownIv);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.comprehensive_sort) {
            // 综合排序
            checkoutSort(SORT_COMPREHENSIVE);
        } else if (id == R.id.price_sort_layout) {
            // 价格排序
            checkoutSort(mCurrentSortType == SORT_PRICE_UP ? SORT_PRICE_DOWN : SORT_PRICE_UP);
        } else if (id == R.id.distence_sort) {
            // 距离排序
            checkoutSort(SORT_DISTANCE);
        }
    }
}
