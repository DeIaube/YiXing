package arouter.dawn.zju.edu.module_pay.ui.cash_coupon;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.order.CashCoupon;
import baselib.base.BaseContract;

public interface PayCashCouponContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void selectCashCoupon(CashCoupon cashCoupon, double price);
    }

}
