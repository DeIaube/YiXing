package arouter.dawn.zju.edu.module_goods.ui.search;

import android.annotation.SuppressLint;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.List;

import baselib.base.BasePresenter;
import arouter.dawn.zju.edu.lib_net.bean.Goods;
import baselib.util.LogUtil;

public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {

    static final String TAG = "SearchPresenter";

    @SuppressLint("CheckResult")
    @Override
    public void search(String word) {
        mView.showLoading();
        AVQuery<Goods> query = Goods.getQuery(Goods.class);
        query.whereContains("title", word).findInBackground(new FindCallback<Goods>() {
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
