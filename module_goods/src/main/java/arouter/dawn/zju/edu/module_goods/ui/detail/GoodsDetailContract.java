package arouter.dawn.zju.edu.module_goods.ui.detail;

import arouter.dawn.zju.edu.lib_net.bean.goods.Goods;
import baselib.base.BaseContract;

public interface GoodsDetailContract {

    interface View extends BaseContract.BaseView {
        void showGoodsAlreadyCollection();
        void showGoodsUnCollection();
        void refreshBuyCounterTextView(String text);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void init(Goods goods);
        void collection(Goods goods);
        void paySuccess(Goods goods);
        void payFailed(Goods goods, String failed);
    }

}
