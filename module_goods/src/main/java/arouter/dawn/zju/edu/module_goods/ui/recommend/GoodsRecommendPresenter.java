package arouter.dawn.zju.edu.module_goods.ui.recommend;


import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.goods.Goods;
import baselib.base.BasePresenter;

/**
 * @Auther: Dawn
 * @Date: 2018/11/23 00:10
 * @Description:
 */
public class GoodsRecommendPresenter extends BasePresenter<GoodsRecommendContract.View> implements GoodsRecommendContract.Presenter {

    private static final String TAG = "RecommendPresenter";

    @Override
    public void refreshRecommendGoodsList() {
        AVQuery<Goods> goodsAVQuery = Goods.getQuery(Goods.class);
        goodsAVQuery.findInBackground(new FindCallback<Goods>() {
            @Override
            public void done(List<Goods> list, AVException e) {
                if (e == null) {
                    mView.refreshRecommendGoodsList(list);
                }
            }
        });
    }
}
