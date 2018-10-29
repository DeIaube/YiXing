package arouter.dawn.zju.edu.module_nearby.ui.search;

import android.annotation.SuppressLint;

import arouter.dawn.zju.edu.lib_net.ApiRequest;
import arouter.dawn.zju.edu.lib_net.bean.network.SearchGoodsRespense;
import baselib.base.BasePresenter;
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
        ApiRequest.getSingle().getApi()
                .search().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SearchGoodsRespense>() {
                    @Override
                    public void accept(SearchGoodsRespense searchGoodsRespense) throws Exception {
                        LogUtil.i(TAG, searchGoodsRespense.toString());
                        mView.hideLoading();
                        mView.refresh(searchGoodsRespense.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.e(TAG, throwable.toString());
                        mView.hideLoading();
                    }
                });
    }
}
