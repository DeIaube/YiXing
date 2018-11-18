package arouter.dawn.zju.edu.module_order.ui.order_list;

import arouter.dawn.zju.edu.lib_net.bean.order.Order;
import baselib.base.BaseContract;

public interface OrderListContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void savePayInformation(Order order);
        void cancelOrder(Order order);
    }

}
