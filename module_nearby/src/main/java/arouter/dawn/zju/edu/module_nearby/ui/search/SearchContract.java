package arouter.dawn.zju.edu.module_nearby.ui.search;

import baselib.base.BaseContract;

public interface SearchContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void search(String word);
    }

}
