package arouter.dawn.zju.edu.module_order.ui.list;

import arouter.dawn.zju.edu.lib_net.bean.order.Order;
import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface OrderHomeListContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void paySuccess(Order order);
        void payFailed(Order order, String failed);
        void cancelOrder(Order order);
    }

}
