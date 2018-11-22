package arouter.dawn.zju.edu.module_goods.ui.recommend;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.goods.Goods;
import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/23 00:10
 * @Description:
 */
public interface GoodsRecommendContract {

    interface View extends BaseContract.BaseView {
        void refreshRecommendGoodsList(List<Goods> goodsList);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void refreshRecommendGoodsList();
    }

}
