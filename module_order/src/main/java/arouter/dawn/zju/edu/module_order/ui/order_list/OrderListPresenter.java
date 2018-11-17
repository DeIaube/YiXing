package arouter.dawn.zju.edu.module_order.ui.order_list;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SaveCallback;

import org.greenrobot.eventbus.EventBus;

import arouter.dawn.zju.edu.lib_net.bean.Order;
import arouter.dawn.zju.edu.module_order.R;
import arouter.dawn.zju.edu.module_order.config.Constants;
import arouter.dawn.zju.edu.module_order.config.EventBusCode;
import arouter.dawn.zju.edu.module_order.ui.order.OrderFragment;
import baselib.App;
import baselib.base.BasePresenter;
import baselib.bus.BusEvent;
import baselib.util.LogUtil;

public class OrderListPresenter extends BasePresenter<OrderListContract.View> implements OrderListContract.Presenter {

    private static final String TAG = "OrderListPresenter";

    @Override
    public void savePayInformation(final Order order) {
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

    private void sendOrderListRefreshEvent() {
        BusEvent event = new BusEvent();
        event.setTarget(OrderFragment.TAG);
        event.setCode(EventBusCode.ORDER_LIST_REFRESH);
        EventBus.getDefault().post(event);
    }
}
