package arouter.dawn.zju.edu.module_pay.ui.home;

import android.app.Activity;

import arouter.dawn.zju.edu.lib_net.bean.order.UserCashCoupon;
import baselib.base.BaseContract;

public interface PayHomeContract {

    interface View extends BaseContract.BaseView {
        void paySuccess();
        void payFailed(String msg);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void pay(Activity activity, android.view.View v, double price,
                 double realPrice, String title, String content, UserCashCoupon userCashCoupon, int type);
    }

}
