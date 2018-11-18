package arouter.dawn.zju.edu.module_order.ui.integral;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.order.CashCoupon;
import baselib.base.BasePresenter;
import baselib.util.LogUtil;

public class IntegralShopPresenter extends BasePresenter<IntegralShopContract.View> implements IntegralShopContract.Presenter {

    static final String TAG = "IntegralPresenter";

    @Override
    public void init() {
        AVQuery<CashCoupon> cashCouponAVQuery = CashCoupon.getQuery(CashCoupon.class);
        cashCouponAVQuery.findInBackground(new FindCallback<CashCoupon>() {
            @Override
            public void done(List<CashCoupon> list, AVException e) {
                if (e == null) {
                    LogUtil.i(TAG, "init: " + list.toString());
                    mView.refresh(list);
                } else {
                    LogUtil.e(TAG, e.getLocalizedMessage());
                    mView.showMessage(e.getLocalizedMessage());
                }
            }
        });
    }
}
