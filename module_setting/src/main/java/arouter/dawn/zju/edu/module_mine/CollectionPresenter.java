package arouter.dawn.zju.edu.module_mine;


import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.List;

import baselib.base.BasePresenter;
import baselib.bean.Goods;
import baselib.util.LogUtil;

public class CollectionPresenter extends BasePresenter<CollectionContract.View> implements CollectionContract.Presenter {

    static final String TAG = "CollectionPresenter";

    @Override
    public void refresh() {
        mView.showLoading();
        AVQuery<Goods> query = Goods.getQuery(Goods.class);
        query.findInBackground(new FindCallback<Goods>() {
            @Override
            public void done(List<Goods> list, AVException e) {
                mView.hideLoading();
                if (e == null) {
                    LogUtil.i(TAG, list.toString());
                    mView.refresh(list);
                } else {
                    LogUtil.e(TAG, e.toString());
                }
            }
        });
    }

}
