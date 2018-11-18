package arouter.dawn.zju.edu.module_order.ui.evaluate;

import arouter.dawn.zju.edu.lib_net.bean.order.Order;
import baselib.base.BaseContract;

public interface EvaluateContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void submitEvaluate(Order order, int evaluateLever, String content);
    }

}
