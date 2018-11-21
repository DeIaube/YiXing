package arouter.dawn.zju.edu.module_order.ui.list;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.DeleteCallback;
import com.avos.avoscloud.SaveCallback;

import org.greenrobot.eventbus.EventBus;

import arouter.dawn.zju.edu.lib_net.bean.order.Order;
import arouter.dawn.zju.edu.module_order.R;
import arouter.dawn.zju.edu.module_order.config.Constants;
import arouter.dawn.zju.edu.module_order.config.EventBusCode;
import arouter.dawn.zju.edu.module_order.ui.home.OrderHomeFragment;
import baselib.App;
import baselib.base.BasePresenter;
import baselib.bus.BusEvent;
import baselib.util.LogUtil;

public class OrderHomeListPresenter extends BasePresenter<OrderHomeListContract.View> implements OrderHomeListContract.Presenter {

    private static final String TAG = "OrderHomeListPresenter";

    @Override
    public void paySuccess(final Order order) {
        order.setType(Constants.ORDER_TYPE_COMPLETE_REQUE_EVALUATE);
        order.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    LogUtil.i(TAG, "savePayInformation: " + order.toString());
                    mView.showMessage(App.getContext().getString(R.string.order_pay_success));
                    sendOrderListRefreshEvent();
                } else {
                    LogUtil.e(TAG, e.getLocalizedMessage());
                    mView.showMessage(e.getLocalizedMessage());
                }
            }
        });
    }

    @Override
    public void payFailed(Order order, String failed) {
        mView.showMessage(failed);
    }

    @Override
    public void cancelOrder(Order order) {
        mView.showLoading();
        order.deleteInBackground(new DeleteCallback() {
            @Override
            public void done(AVException e) {
                mView.hideLoading();
                if (e == null) {
                    LogUtil.i(TAG, "cancelOrder");
                    sendOrderListRefreshEvent();
                    mView.showMessage(App.getContext().getString(R.string.order_home_list_cancel_order_success));
                } else {
                    LogUtil.e(TAG, e.getLocalizedMessage());
                    mView.showMessage(e.getLocalizedMessage());
                }
            }
        });
    }

    private void sendOrderListRefreshEvent() {
        BusEvent event = new BusEvent();
        event.setTarget(OrderHomeFragment.TAG);
        event.setCode(EventBusCode.ORDER_LIST_REFRESH);
        EventBus.getDefault().post(event);
    }
}
