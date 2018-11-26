package yixing.dawn.zju.edu.yixing.ui.home;


import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.goods.Goods;
import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface HomeContract {

    interface View extends BaseContract.BaseView {
        void refreshHomeView(List<Goods> goodsList, List<String> preView);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void refreshHomeView();
    }

}
