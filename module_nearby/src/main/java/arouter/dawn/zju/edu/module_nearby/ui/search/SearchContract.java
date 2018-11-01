package arouter.dawn.zju.edu.module_nearby.ui.search;

import java.util.List;

import baselib.base.BaseContract;
import baselib.bean.Goods;

public interface SearchContract {

    interface View extends BaseContract.BaseView {
        void refresh(List<Goods> goodsList);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void search(String word);
    }

}
