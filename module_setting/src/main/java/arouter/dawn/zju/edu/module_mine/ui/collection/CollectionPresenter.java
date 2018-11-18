package arouter.dawn.zju.edu.module_mine.ui.collection;


import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.goods.GoodsCollection;
import arouter.dawn.zju.edu.lib_net.bean.User;
import baselib.base.BasePresenter;
import arouter.dawn.zju.edu.lib_net.bean.goods.Goods;
import baselib.util.LogUtil;

public class CollectionPresenter extends BasePresenter<CollectionContract.View> implements CollectionContract.Presenter {

    static final String TAG = "CollectionPresenter";

    @Override
    public void refresh() {
        mView.showLoading();
        AVQuery<GoodsCollection> goodsCollectionAVQuery = GoodsCollection.getQuery(GoodsCollection.class);
        goodsCollectionAVQuery.
                whereEqualTo("user", User.getCurrentUser(User.class))
                .include("goods")
                .findInBackground(new FindCallback<GoodsCollection>() {
                    @Override
                    public void done(List<GoodsCollection> list, AVException e) {
                        mView.hideLoading();
                        if (e == null) {
                            LogUtil.i(TAG, list.toString());
                            List<Goods> goodsList = new ArrayList<>();
                            for (GoodsCollection goodsCollection : list) {
                                goodsList.add(goodsCollection.getGoods());
                                mView.refresh(goodsList);
                            }
                        } else {
                            LogUtil.e(TAG, e.toString());
                        }
                    }
                });
    }

}
