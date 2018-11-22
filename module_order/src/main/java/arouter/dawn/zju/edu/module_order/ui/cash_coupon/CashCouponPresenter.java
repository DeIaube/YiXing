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

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class CashCouponPresenter extends BasePresenter<CashCouponContract.View> implements CashCouponContract.Presenter {

    private static final String TAG = "CashCouponPresenter";

    private CashCouponListFragment mEffectiveCouponListFragmen;
    private CashCouponListFragment mUsedCashCouponListFragmen;
    private CashCouponListFragment mOverdueCashCouponListFragmen;


    @Override
    public void bindViewPager(FragmentManager fragmentManager, ViewPager viewPager, TabLayout tabLayout) {
        List<String> titles = new ArrayList<>();
        titles.add("未使用");
        titles.add("已使用");
        titles.add("已过期");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(mEffectiveCouponListFragmen = new CashCouponListFragment());
        fragments.add(mUsedCashCouponListFragmen = new CashCouponListFragment());
        fragments.add(mOverdueCashCouponListFragmen = new CashCouponListFragment());

        CashCouponPagerAdapter adapter = new CashCouponPagerAdapter(fragmentManager, titles, fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(titles.size());

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
            if (userCashCoupon.getCashCoupon().getEndTime().before(currentDate)) {
                overdueCashCouponList.add(userCashCoupon);
            } else {
                if (userCashCoupon.getUsed()) {
                    effectiveCashCouponList.add(userCashCoupon);
                } else {
                    usedCashCouponList.add(userCashCoupon);
                }
            }
        }

        mEffectiveCouponListFragmen.refresh(effectiveCashCouponList);
        mUsedCashCouponListFragmen.refresh(usedCashCouponList);
        mOverdueCashCouponListFragmen.refresh(overdueCashCouponList);
    }
}
