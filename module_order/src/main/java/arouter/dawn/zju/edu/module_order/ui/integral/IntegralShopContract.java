package arouter.dawn.zju.edu.module_order.ui.integral;


import java.util.List;
import java.util.Set;

import arouter.dawn.zju.edu.lib_net.bean.order.CashCoupon;
import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface IntegralShopContract {

    interface View extends BaseContract.BaseView {
        void refresh(List<CashCoupon> cashCouponList, List<CashCoupon> userCashCoupons);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void init();
        void buyCashCoupon(CashCoupon cashCoupon);
    }

}
