package arouter.dawn.zju.edu.module_order.ui.cash_coupon;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.lib_net.bean.order.UserCashCoupon;
import arouter.dawn.zju.edu.module_order.adapter.CashCouponPagerAdapter;
import arouter.dawn.zju.edu.module_order.ui.cash_coupon_list.CashCouponListFragment;
import baselib.base.BasePresenter;
import baselib.util.LogUtil;

public class CashCouponPresenter extends BasePresenter<CashCouponContract.View> implements CashCouponContract.Presenter {

    private static final String TAG = "CashCouponPresenter";

    private List<String> mTitles;
    private List<Fragment> mFragments;

    @Override
    public void bindViewPager(FragmentManager fragmentManager, ViewPager viewPager, TabLayout tabLayout) {
        mTitles = new ArrayList<>();
        mFragments = new ArrayList<>();

        mTitles.add("未使用");
        mTitles.add("已使用");
        mTitles.add("已过期");

        for (int i = 0; i < mTitles.size(); i++) {
            mFragments.add(new CashCouponListFragment());
        }

        CashCouponPagerAdapter adapter = new CashCouponPagerAdapter(fragmentManager, mTitles, mFragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        refreshUserCashCouponList();
    }

    private void refreshUserCashCouponList() {
        AVQuery<UserCashCoupon> cashCouponAVQuery = UserCashCoupon.getQuery(UserCashCoupon.class);
        cashCouponAVQuery
                .whereEqualTo("owner", User.getCurrentUser(User.class))
                .include("cashCoupon")
                .findInBackground(new FindCallback<UserCashCoupon>() {
                    @Override
                    public void done(List<UserCashCoupon> list, AVException e) {
                        if (e == null) {
                            LogUtil.i(TAG, "bindViewPager: " + list.toString());
                            refreshUserCashCouponList(list);
                        } else {
                            LogUtil.e(TAG, e.getLocalizedMessage());
                            mView.showMessage(e.getLocalizedMessage());
                        }
                    }
                });
    }

    private void refreshUserCashCouponList(List<UserCashCoupon> list) {

        List<UserCashCoupon> usedCashCouponList = new ArrayList<>();
        List<UserCashCoupon> overdueCashCouponList = new ArrayList<>();
        List<UserCashCoupon> effectiveCashCouponList = new ArrayList<>();
        Date currentDate = new Date();

        for (UserCashCoupon userCashCoupon : list) {
            if (userCashCoupon.getUsed()) {
                usedCashCouponList.add(userCashCoupon);
                continue;
            }
            if (userCashCoupon.getCashCoupon().getEndTime().before(currentDate)) {
                overdueCashCouponList.add(userCashCoupon);
            } else {
                effectiveCashCouponList.add(userCashCoupon);
            }
        }

        if (mFragments.get(0) instanceof CashCouponListFragment) {
            ((CashCouponListFragment)mFragments.get(0)).refresh(usedCashCouponList);
        }

        if (mFragments.get(1) instanceof CashCouponListFragment) {
            ((CashCouponListFragment)mFragments.get(1)).refresh(effectiveCashCouponList);
        }

        if (mFragments.get(2) instanceof CashCouponListFragment) {
            ((CashCouponListFragment)mFragments.get(2)).refresh(overdueCashCouponList);
        }

    }
}
