package arouter.dawn.zju.edu.module_goods.ui.detail;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.DeleteCallback;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.SaveCallback;

import arouter.dawn.zju.edu.lib_net.bean.Goods;
import arouter.dawn.zju.edu.lib_net.bean.GoodsCollection;
import arouter.dawn.zju.edu.lib_net.bean.User;
import baselib.base.BasePresenter;
import baselib.util.LogUtil;

public class GoodsDetailPresenter extends BasePresenter<GoodsDetailContract.View> implements GoodsDetailContract.Presenter {

    String TAG = "GoodsDetailPresenter";

    private GoodsCollection mGoodsCollection;

    @Override
    public void init(Goods goods) {
        initCollection(goods);
    }

    private void initCollection(Goods goods) {
        AVQuery<GoodsCollection> goodsCollectionAVQuery = GoodsCollection.getQuery(GoodsCollection.class);
        goodsCollectionAVQuery.
                whereEqualTo("goods", goods).
                whereEqualTo("user", User.getCurrentUser(User.class))
                .getFirstInBackground(new GetCallback<GoodsCollection>() {
                    @Override
                    public void done(GoodsCollection goodsCollection, AVException e) {
                        if (e == null) {
                            mGoodsCollection = goodsCollection;
                        }
                        if (mGoodsCollection == null) {
                            mView.showGoodsUnCollection();
                        } else {
                            mView.showGoodsAlreadyCollection();
                        }
                    }
                });
    }

    @Override
    public void collection(Goods goods) {
        if (mGoodsCollection == null) {
            mGoodsCollection = new GoodsCollection();
            mGoodsCollection.setOwner(User.getCurrentUser(User.class));
            mGoodsCollection.setGoods(goods);
            mGoodsCollection.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null) {
                        mView.showGoodsAlreadyCollection();
                    } else {
                        LogUtil.e(TAG, e.getLocalizedMessage());
                    }
                }
            });
        } else {
            mGoodsCollection.deleteInBackground(new DeleteCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null) {
                        mGoodsCollection = null;
                        mView.showGoodsUnCollection();
                    } else {
                        LogUtil.e(TAG, e.getLocalizedMessage());
                    }
                }
            });
        }
    }
}
