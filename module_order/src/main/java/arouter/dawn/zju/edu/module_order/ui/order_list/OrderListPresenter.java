package arouter.dawn.zju.edu.module_order.ui.order_list;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SaveCallback;

import arouter.dawn.zju.edu.lib_net.bean.Order;
import arouter.dawn.zju.edu.module_order.R;
import arouter.dawn.zju.edu.module_order.config.Constants;
import baselib.App;
import baselib.base.BasePresenter;
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
                } else {
                    LogUtil.e(TAG, e.getLocalizedMessage());
                    mView.showMessage(e.getLocalizedMessage());
                }
            }
        });
    }
}
