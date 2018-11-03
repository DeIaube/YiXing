package arouter.dawn.zju.edu.module_goods.ui.cart;

import java.util.List;

import baselib.base.BaseContract;
import arouter.dawn.zju.edu.lib_net.bean.Goods;

public interface CartContract {

    interface View extends BaseContract.BaseView {
        void refresh(List<Goods> goodsList);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void refresh();
    }

}
