package arouter.dawn.zju.edu.module_wallet.ui.bill;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.module_wallet.adapter.WalletBillPagerAdapter;
import arouter.dawn.zju.edu.module_wallet.ui.bill_list.WalletBillListFragment;
import baselib.base.BasePresenter;

public class WalletBillPresenter extends BasePresenter<WalletBillContract.View> implements WalletBillContract.Presenter {

    static final String TAG = "WalletBillPresenter";

    private List<String> mTitles;
    private List<Fragment> mFragments;

    @Override
    public void bindViewPager(FragmentManager fragmentManager, ViewPager viewPager, TabLayout tabLayout) {
        mTitles = new ArrayList<>();
        mTitles.add("全部");
        mTitles.add("收入");
        mTitles.add("支出");
        mFragments = new ArrayList<>();
        mFragments.add(new WalletBillListFragment());
        mFragments.add(new WalletBillListFragment());
        mFragments.add(new WalletBillListFragment());

        WalletBillPagerAdapter pagerAdapter = new WalletBillPagerAdapter(fragmentManager, mTitles, mFragments);
        viewPager.setOffscreenPageLimit(mTitles.size());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        refreshBill();
    }

    private void refreshBill() {

    }

}
