package arouter.dawn.zju.edu.module_goods.ui.search;

import java.util.List;

import baselib.base.BaseContract;
import arouter.dawn.zju.edu.lib_net.bean.goods.Goods;

public interface GoodsSearchContract {

    interface View extends BaseContract.BaseView {
        void refresh(List<Goods> goodsList);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void search(String word);
    }

}
