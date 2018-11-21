package arouter.dawn.zju.edu.module_pay.ui.cash_coupon;


import org.greenrobot.eventbus.EventBus;

import arouter.dawn.zju.edu.lib_net.bean.order.CashCoupon;
import arouter.dawn.zju.edu.module_pay.R;
import arouter.dawn.zju.edu.module_pay.config.Constants;
import arouter.dawn.zju.edu.module_pay.ui.container.PayContainerFragment;
import baselib.App;
import baselib.base.BasePresenter;
import baselib.bus.BusEvent;

public class PayCashCouponPresenter extends BasePresenter<PayCashCouponContract.View> implements PayCashCouponContract.Presenter {

    private static final String TAG = "PayCashCouponPresenter";


    @Override
    public void selectCashCoupon(CashCoupon cashCoupon, double price) {
        if (price < cashCoupon.getDoorsill()) {
            mView.showMessage(App.getContext().getString(R.string.pay_cash_coupon_dont_achieve_doorsill));
            return;
        }
        BusEvent busEvent = new BusEvent();
        busEvent.setCode(Constants.EVENT_SELETED_CASH_COUPON);
        busEvent.setTarget(PayContainerFragment.TAG);
        busEvent.setData(cashCoupon);
        EventBus.getDefault().post(busEvent);
    }

}
