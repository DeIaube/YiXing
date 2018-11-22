package arouter.dawn.zju.edu.module_pay.ui.cash_coupon;


import arouter.dawn.zju.edu.lib_net.bean.order.UserCashCoupon;
import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface PayCashCouponContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void selectCashCoupon(UserCashCoupon cashCoupon, double price);
    }

}
