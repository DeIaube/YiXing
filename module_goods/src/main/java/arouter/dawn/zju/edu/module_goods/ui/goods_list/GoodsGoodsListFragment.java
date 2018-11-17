package arouter.dawn.zju.edu.module_goods.ui.goods_list;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.module_nearby.R;
import arouter.dawn.zju.edu.module_goods.adapter.GoodsListAdapter;
import baselib.base.BaseFragment;
import arouter.dawn.zju.edu.lib_net.bean.Goods;
import baselib.callback.GoodsListRefreshCallback;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_GOODS_GOODS_LIST)
public class GoodsGoodsListFragment extends BaseFragment<GoodsGoodsListContract.Presenter> implements GoodsGoodsListContract.View, GoodsListAdapter.OnGoodsClickListener , GoodsListRefreshCallback {


    RecyclerView recyclerView;

    private List<Goods> mGoodsList;
    private GoodsListAdapter mAdapter;

    @Override
    public void refresh(List<Goods> goodsList) {
        mGoodsList = goodsList;
        mAdapter.refresh(mGoodsList);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goods_list;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new GoodsGoodsListPresenter();
    }

    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.goods_list_view);

        mGoodsList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new GoodsListAdapter(new ArrayList<Goods>(), getContext());
        mAdapter.setOnGoodsClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onGoodsClick(View v, Goods goods) {
        // todo 商品点击回调
    }
}
