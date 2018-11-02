package arouter.dawn.zju.edu.module_goods.ui.cart;


import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.List;

import arouter.dawn.zju.edu.module_goods.config.Constants;
import baselib.base.BasePresenter;
import baselib.bean.Goods;
import baselib.util.LogUtil;

public class CartPresenter extends BasePresenter<CartContract.View> implements CartContract.Presenter {

    private static final String TAG = "CartPresenter";

    @Override
    public void refresh() {
        AVQuery<Goods> query = Goods.getQuery(Goods.class);
        query.findInBackground(new FindCallback<Goods>() {
            @Override
            public void done(List<Goods> list, AVException e) {
                if (e == null) {
                    LogUtil.i(TAG, list.toString());
                    mView.refresh(list);
                } else {
                    LogUtil.e(TAG, e.toString());
                    mView.showNetworkError();
                }
            }
        });
    }
}
