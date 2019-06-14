package arouter.dawn.zju.edu.module_order.ui.evaluate;

import android.annotation.SuppressLint;

import arouter.dawn.zju.edu.lib_net.bean.goods.GoodsEvaluate;
import arouter.dawn.zju.edu.lib_net.bean.order.Order;
import arouter.dawn.zju.edu.module_order.R;
import arouter.dawn.zju.edu.module_order.config.Constants;
import baselib.App;
import baselib.base.BasePresenter;
import baselib.util.LogUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class OrderEvaluatePresenter extends BasePresenter<OrderEvaluateContract.View> implements OrderEvaluateContract.Presenter {

    private static final String TAG = "OrderEvaluatePresenter";

    @SuppressLint("CheckResult")
    @Override
    public void submitEvaluate(Order order, final int evaluateLever, final String content) {
        mView.showLoading();
        Observable.just(order)
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<Order>() {
                    @Override
                    public void accept(Order order) throws Exception {
                        GoodsEvaluate evaluate = new GoodsEvaluate();
                        evaluate.setGoods(order.getGoods());
                        evaluate.setLevel(evaluateLever);
                        evaluate.setContent(content);
                        evaluate.save();
                        order.setType(Constants.ORDER_TYPE_COMPLETE_EVALUATED);
                        order.save();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Order>() {
                    @Override
                    public void accept(Order order) throws Exception {
                        LogUtil.i(TAG, "submitEvaluate :" + order.toString());
                        mView.hideLoading();
                        mView.showMessage(App.getAppalication().getString(R.string.order_evaluate_evaluation_success));
                        mView.finish();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.e(TAG, throwable.getLocalizedMessage());
                        mView.hideLoading();
                        mView.showMessage(throwable.getLocalizedMessage());
                    }
                });
    }
}
