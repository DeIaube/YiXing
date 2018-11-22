package arouter.dawn.zju.edu.module_mine.ui.mine;


import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface MineContract {

    interface View extends BaseContract.BaseView {
        void refreshCashCouponCount(int count);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void refreshCashCouponCount();
    }

}
