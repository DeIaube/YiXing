package arouter.dawn.zju.edu.module_mine.ui.mine;


import baselib.base.BaseContract;

public interface MineContract {

    interface View extends BaseContract.BaseView {
        void refreshCashCouponCount(int count);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void refreshCashCouponCount();
    }

}
