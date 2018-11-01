package arouter.dawn.zju.edu.module_nearby.ui.search;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.GoodsBean;
import baselib.base.BaseContract;
import baselib.bean.AVGoods;

public interface SearchContract {

    interface View extends BaseContract.BaseView {
        void refresh(List<AVGoods> goodsList);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void search(String word);
    }

}
