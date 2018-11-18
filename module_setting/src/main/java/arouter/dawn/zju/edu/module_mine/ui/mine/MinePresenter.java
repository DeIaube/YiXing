package arouter.dawn.zju.edu.module_mine.ui.mine;


import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.CountCallback;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.lib_net.bean.order.UserCashCoupon;
import baselib.base.BasePresenter;
import baselib.util.LogUtil;

public class MinePresenter extends BasePresenter<MineContract.View> implements MineContract.Presenter {

    static final String TAG = "MinePresenter";

    @Override
    public void refreshCashCouponCount() {
        AVQuery<UserCashCoupon> userCashCouponAVQuery = UserCashCoupon.getQuery(UserCashCoupon.class);
        userCashCouponAVQuery
                .whereEqualTo("owner", User.getCurrentUser(User.class))
                .countInBackground(new CountCallback() {
                    @Override
                    public void done(int i, AVException e) {
                        if (e == null) {
                            mView.refreshCashCouponCount(i);
                        } else {
                            LogUtil.e(TAG, e.getLocalizedMessage());
                        }
                    }
                });
    }
}
