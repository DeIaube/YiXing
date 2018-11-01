package arouter.dawn.zju.edu.module_nearby.ui.goods_list;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.GoodsBean;
import arouter.dawn.zju.edu.module_nearby.R;
import arouter.dawn.zju.edu.module_nearby.adapter.GoodsListAdapter;
import baselib.base.BaseFragment;
import baselib.bean.AVGoods;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_NEARBY_GOODS_LIST)
public class GoodsListFragment extends BaseFragment<GoodsListContract.Presenter> implements GoodsListContract.View, GoodsListAdapter.OnGoodsClickListener {


    RecyclerView recyclerView;

    private List<AVGoods> mGoodsList;
    private GoodsListAdapter mAdapter;

    public void refresh(List<AVGoods> goodsList) {
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
        recyclerView = view.findViewById(R.id.goods_list_view);

        mGoodsList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new GoodsListAdapter(new ArrayList<AVGoods>(), getContext());
        mAdapter.setOnGoodsClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onGoodsClick(View v, AVGoods goods) {
        // todo 商品点击回调
    }
}
