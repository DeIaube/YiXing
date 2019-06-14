package arouter.dawn.zju.edu.module_goods.ui.recommend;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.goods.Goods;
import arouter.dawn.zju.edu.module_goods.adapter.GoodsRecommendListAdapter;
import arouter.dawn.zju.edu.module_nearby.R;
import baselib.base.BaseFragment;
import baselib.constants.RouteConstants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/23 00:10
 * @Description:
 * 商品推荐页面
 */
@Route(path = RouteConstants.AROUTER_GOODS_RECOMMEND)
public class GoodsRecommendFragment extends BaseFragment<GoodsRecommendContract.Presenter> implements GoodsRecommendContract.View {

    RecyclerView goodsRecommendListView;

    private GoodsRecommendListAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goods_recommend;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new GoodsRecommendPresenter();
    }

    @Override
    protected void initView(View view) {
        goodsRecommendListView = view.findViewById(R.id.goods_recommend_list_view);
        goodsRecommendListView.setFocusable(false);

        goodsRecommendListView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mAdapter = new GoodsRecommendListAdapter(new ArrayList<Goods>(), getContext());
        goodsRecommendListView.setAdapter(mAdapter);

        mPresenter.refreshRecommendGoodsList();
    }

    @Override
    public void refreshRecommendGoodsList(List<Goods> goodsList) {
        mAdapter.refresh(goodsList);
    }
}
