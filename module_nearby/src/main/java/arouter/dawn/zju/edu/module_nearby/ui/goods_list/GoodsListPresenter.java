package arouter.dawn.zju.edu.module_nearby.ui.goods_list;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.GoodsBean;
import arouter.dawn.zju.edu.module_nearby.config.Constants;
import baselib.base.BasePresenter;

public class GoodsListPresenter extends BasePresenter<GoodsListContract.View> implements GoodsListContract.Presenter {

    @Override
    public void checkoutSortGoods(List<GoodsBean> goodsList, int sortType) {
        Comparator<GoodsBean> comparator = null;
        if (sortType == Constants.SORT_COMPREHENSIVE) {
            comparator = new Comparator<GoodsBean>() {
                @Override
                public int compare(GoodsBean o1, GoodsBean o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            };
        } else if (sortType == Constants.SORT_PRICE_DOWN) {
            comparator = new Comparator<GoodsBean>() {
                @Override
                public int compare(GoodsBean o1, GoodsBean o2) {
                    return o1.getPrice() - o2.getPrice();
                }
            };
        } else if (sortType == Constants.SORT_PRICE_UP) {
            comparator = new Comparator<GoodsBean>() {
                @Override
                public int compare(GoodsBean o1, GoodsBean o2) {
                    return o2.getPrice() - o1.getPrice();
                }
            };
        } else if (sortType == Constants.SORT_DISTANCE) {
            comparator = new Comparator<GoodsBean>() {
                @Override
                public int compare(GoodsBean o1, GoodsBean o2) {
                    return 1;
                }
            };
        }
        Collections.sort(goodsList, comparator);
        mView.updateSortGoodsResult(goodsList);
    }

}
