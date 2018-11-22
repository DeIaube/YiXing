package arouter.dawn.zju.edu.module_order.ui.integral;

import android.annotation.SuppressLint;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.SaveCallback;

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
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class IntegralShopPresenter extends BasePresenter<IntegralShopContract.View> implements IntegralShopContract.Presenter {

    static final String TAG = "IntegralPresenter";

    private List<List<CashCoupon>> cashCouponLists;

    public IntegralShopPresenter() {
        cashCouponLists = new ArrayList<>();
    }

    @Override
    public void buyCashCoupon(final CashCoupon cashCoupon) {
        final User user = User.getCurrentUser(User.class);
        if (user.getShopPoint() < cashCoupon.getIntegral()) {
            mView.showMessage("抱歉,积分不足");
            return;
        }
        final UserCashCoupon userCashCoupon = new UserCashCoupon();
        userCashCoupon.setOwner(user);
        userCashCoupon.setCashCoupon(cashCoupon);
        mView.showLoading();
        userCashCoupon.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                mView.hideLoading();
                if (e == null) {
                    LogUtil.i(TAG, "buyCashCoupon: " + userCashCoupon.toString());
                    cashCouponLists.get(0).add(cashCoupon);
                    mView.refresh(cashCouponLists.get(1), cashCouponLists.get(0));
                    user.setShopPoint(user.getShopPoint() - cashCoupon.getIntegral());
                    user.saveInBackground();
                } else {
                    LogUtil.e(TAG, e.getLocalizedMessage());
                    mView.showMessage(e.getLocalizedMessage());
                }
            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public void init() {
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
