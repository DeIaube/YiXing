package arouter.dawn.zju.edu.module_pay.ui.home;

import android.app.Activity;
import android.view.View;

import arouter.dawn.zju.edu.lib_net.bean.order.CashCoupon;
import arouter.dawn.zju.edu.lib_net.bean.order.UserCashCoupon;
import arouter.dawn.zju.edu.module_pay.callback.PayCallback;
import arouter.dawn.zju.edu.module_pay.config.Constants;
import arouter.dawn.zju.edu.module_pay.pay.PayBuiled;
import baselib.base.BasePresenter;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class PayHomePresenter extends BasePresenter<PayHomeContract.View> implements PayHomeContract.Presenter {

    private static final String TAG = "PayHomePresenter";

    @Override
    public void pay(Activity activity, final View v, double price,
                    double realPrice, String title, String content, final UserCashCoupon userCashCoupon, int type) {
        PayBuiled payBuiled = new PayBuiled(activity)
                .setPrice(realPrice)
                .setContent(content)
                .setTitle(title)
                .setPayCallback(new PayCallback() {
                    @Override
                    public void paySuccess() {
                        mView.paySuccess();
                        if (userCashCoupon != null) {
                            userCashCoupon.setUsed(false);
                            userCashCoupon.saveInBackground();
                        }
                    }

                    @Override
                    public void payFailed(String msg) {
                        mView.payFailed(msg);
                    }
                });
        if (type == Constants.PAY_TYPE_ALI) {
            payBuiled.builedAliPay().pay(v);
        } else if (type == Constants.PAY_TYPE_WALLET) {
            payBuiled.builedWalletPay().pay(v);
        }
    }

}
