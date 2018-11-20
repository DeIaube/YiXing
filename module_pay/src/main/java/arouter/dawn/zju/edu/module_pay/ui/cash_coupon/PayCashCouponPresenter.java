package arouter.dawn.zju.edu.module_pay.ui.cash_coupon;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.lib_net.bean.order.CashCoupon;
import arouter.dawn.zju.edu.lib_net.bean.order.UserCashCoupon;
import baselib.base.BasePresenter;

public class PayCashCouponPresenter extends BasePresenter<PayCashCouponContract.View> implements PayCashCouponContract.Presenter {

    private static final String TAG = "PayCashCouponPresenter";

    @Override
    public void refreshCashCouponList() {
        AVQuery<UserCashCoupon> userCashCouponAVQuery = UserCashCoupon.getQuery(UserCashCoupon.class);
        userCashCouponAVQuery
                .whereEqualTo("owner", User.getCurrentUser(User.class))
                .include("cashCoupon")
                .findInBackground(new FindCallback<UserCashCoupon>() {
                    @Override
                    public void done(List<UserCashCoupon> list, AVException e) {
                        if (e == null) {
                            List<CashCoupon> cashCouponList = new ArrayList<>();
                            for (UserCashCoupon userCashCoupon : list) {
                                cashCouponList.add(userCashCoupon.getCashCoupon());
                            }
                            mView.refreshCashCouponList(cashCouponList);
                        }
                    }
                });
    }
}
