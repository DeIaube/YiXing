package arouter.dawn.zju.edu.module_order.ui.integral;

import android.annotation.SuppressLint;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.lib_net.bean.order.CashCoupon;
import arouter.dawn.zju.edu.lib_net.bean.order.UserCashCoupon;
import baselib.base.BasePresenter;
import baselib.util.LogUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class IntegralShopPresenter extends BasePresenter<IntegralShopContract.View> implements IntegralShopContract.Presenter {

    static final String TAG = "IntegralPresenter";

    @SuppressLint("CheckResult")
    @Override
    public void init() {
        final List<List<CashCoupon>> cashCouponLists = new ArrayList<>();
        final AVQuery<UserCashCoupon> userCashCouponAVQuery = UserCashCoupon.getQuery(UserCashCoupon.class);
        final AVQuery<CashCoupon> cashCouponAVQuery = CashCoupon.getQuery(CashCoupon.class);
        Observable.create(new ObservableOnSubscribe<List<CashCoupon>>() {
            @Override
            public void subscribe(ObservableEmitter<List<CashCoupon>> e) throws Exception {
                List<UserCashCoupon> userCashCouponList = userCashCouponAVQuery
                        .whereEqualTo("owner", User.getCurrentUser(User.class))
                        .include("cashCoupon")
                        .find();
                List<CashCoupon> cashCouponList = new ArrayList<>();
                for (UserCashCoupon userCashCoupon : userCashCouponList) {
                    cashCouponList.add(userCashCoupon.getCashCoupon());
                }
                e.onNext(cashCouponList);
                cashCouponList = cashCouponAVQuery.find();
                e.onNext(cashCouponList);
                e.onComplete();
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<CashCoupon>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<CashCoupon> cashCouponList) {
                        cashCouponLists.add(cashCouponList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mView.refresh(cashCouponLists.get(1), cashCouponLists.get(0));
                    }
                });
    }
}
