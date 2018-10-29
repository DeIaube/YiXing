package arouter.dawn.zju.edu.module_nearby.ui.search;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.GoodsBean;
import baselib.base.BaseContract;

public interface SearchContract {

    interface View extends BaseContract.BaseView {
        void refresh(List<GoodsBean> goodsList);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void search(String word);
    }

}
