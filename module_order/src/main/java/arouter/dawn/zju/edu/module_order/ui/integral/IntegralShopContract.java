package arouter.dawn.zju.edu.module_order.ui.integral;


import java.util.List;
import java.util.Set;

import arouter.dawn.zju.edu.lib_net.bean.order.CashCoupon;
import baselib.base.BaseContract;

public interface IntegralShopContract {

    interface View extends BaseContract.BaseView {
        void refresh(List<CashCoupon> cashCouponList, List<CashCoupon> userCashCoupons);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void init();
    }

}
