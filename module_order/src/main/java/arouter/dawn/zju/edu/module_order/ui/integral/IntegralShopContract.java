package arouter.dawn.zju.edu.module_order.ui.integral;


import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.order.CashCoupon;
import baselib.base.BaseContract;

public interface IntegralShopContract {

    interface View extends BaseContract.BaseView {
        void refresh(List<CashCoupon> cashCouponList);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void init();
    }

}
