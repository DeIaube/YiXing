package arouter.dawn.zju.edu.module_mine.ui.collection;

import java.util.List;

import baselib.base.BaseContract;
import arouter.dawn.zju.edu.lib_net.bean.goods.Goods;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface CollectionContract {

    interface View extends BaseContract.BaseView {
        void refresh(List<Goods> goodsList);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void refresh();
    }

}
