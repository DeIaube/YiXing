package arouter.dawn.zju.edu.module_nearby.ui.search;

import android.annotation.SuppressLint;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.ApiRequest;
import arouter.dawn.zju.edu.lib_net.bean.network.SearchGoodsRespense;
import baselib.base.BasePresenter;
import baselib.bean.AVGoods;
import baselib.util.LogUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {

    static final String TAG = "SearchPresenter";

    @SuppressLint("CheckResult")
    @Override
    public void search(String word) {
        mView.showLoading();
        AVQuery<AVGoods> query = AVGoods.getQuery(AVGoods.class);
        query.findInBackground(new FindCallback<AVGoods>() {
            @Override
            public void done(List<AVGoods> list, AVException e) {
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
