package arouter.dawn.zju.edu.module_wallet.ui.bill;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.lib_net.bean.wallet.Bill;
import arouter.dawn.zju.edu.module_wallet.adapter.WalletBillPagerAdapter;
import arouter.dawn.zju.edu.module_wallet.ui.bill_list.WalletBillListFragment;
import baselib.base.BasePresenter;
import baselib.util.LogUtil;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class WalletBillPresenter extends BasePresenter<WalletBillContract.View> implements WalletBillContract.Presenter {

    static final String TAG = "WalletBillPresenter";

    private List<String> mTitles;
    private List<Fragment> mFragments;
    private WalletBillListFragment mAllBillListFragment;
    private WalletBillListFragment mIncomeBillListFragment;
    private WalletBillListFragment mOutcomeBillListFragment;

    @Override
    public void bindViewPager(FragmentManager fragmentManager, ViewPager viewPager, TabLayout tabLayout) {
        mTitles = new ArrayList<>();
        mTitles.add("全部");
        mTitles.add("收入");
        mTitles.add("支出");
        mFragments = new ArrayList<>();
        mFragments.add(mAllBillListFragment = new WalletBillListFragment());
        mFragments.add(mIncomeBillListFragment = new WalletBillListFragment());
        mFragments.add(mOutcomeBillListFragment = new WalletBillListFragment());

        WalletBillPagerAdapter pagerAdapter = new WalletBillPagerAdapter(fragmentManager, mTitles, mFragments);
        viewPager.setOffscreenPageLimit(mTitles.size());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        refreshBillList();
    }

    private void refreshBillList() {
        AVQuery<Bill> billAVQuery = Bill.getQuery(Bill.class);
        billAVQuery
                .whereEqualTo("owner", User.getCurrentUser(User.class))
                .findInBackground(new FindCallback<Bill>() {
                    @Override
                    public void done(List<Bill> list, AVException e) {
                        if (e == null) {
                            LogUtil.i(TAG, "refreshBillList: " + list.toString());
                            refreshBillList(list);
                        } else {
                            LogUtil.e(TAG, e.getLocalizedMessage());
                            mView.showMessage(e.getLocalizedMessage());
                        }
                    }
                });
    }

    private void refreshBillList(List<Bill> list) {
        List<Bill> incomeBillList = new ArrayList<>();
        List<Bill> outcomeBillList = new ArrayList<>();
        for (Bill bill : list) {
            if (bill.getType()) {
                incomeBillList.add(bill);
            } else {
                outcomeBillList.add(bill);
            }
        }
        mAllBillListFragment.refresh(list);
        mIncomeBillListFragment.refresh(incomeBillList);
        mOutcomeBillListFragment.refresh(outcomeBillList);
    }

}
