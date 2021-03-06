package arouter.dawn.zju.edu.module_goods.ui.list;


import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.module_nearby.R;
import arouter.dawn.zju.edu.module_goods.adapter.GoodsListAdapter;
import baselib.base.BaseFragment;
import arouter.dawn.zju.edu.lib_net.bean.goods.Goods;
import baselib.callback.GoodsListRefreshCallback;
import baselib.constants.RouteConstants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 真正的用户详情页
 */
@Route(path = RouteConstants.AROUTER_GOODS_GOODS_LIST)
public class GoodsListFragment extends BaseFragment<GoodsListContract.Presenter> implements GoodsListContract.View, GoodsListAdapter.OnGoodsClickListener , GoodsListRefreshCallback {


    RecyclerView recyclerView;
    View goodsListEmptyView;

    private List<Goods> mGoodsList;
    private GoodsListAdapter mAdapter;

    @Override
    public void refresh(List<Goods> goodsList) {
        goodsListEmptyView.setVisibility(goodsList.isEmpty() ? View.VISIBLE : View.GONE);
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
        goodsListEmptyView = view.findViewById(R.id.goods_list_empty_view);

        mGoodsList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new GoodsListAdapter(new ArrayList<Goods>(), getContext());
        mAdapter.setOnGoodsClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onGoodsClick(View v, Goods goods) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(RouteConstants.GOODS_DETAIL_GOODS, goods);
        ARouter.getInstance().build(RouteConstants.AROUTER_GOODS_DETAIL)
                .withBundle(RouteConstants.GOODS_DETAIL_BUNDLE, bundle).navigation();
    }
}
