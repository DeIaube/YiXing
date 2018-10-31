package arouter.dawn.zju.edu.module_nearby.ui.goods_list;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.GoodsBean;
import baselib.base.BaseContract;

public interface GoodsListContract {

    interface View extends BaseContract.BaseView {
        void updateSortGoodsResult(List<GoodsBean> goodsList);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void checkoutSortGoods(List<GoodsBean> goodsList, int sortType);
    }

}
