package arouter.dawn.zju.edu.module_wallet.ui.bill_list;

import android.view.View;

import arouter.dawn.zju.edu.module_wallet.R;
import baselib.base.BaseFragment;

public class WalletBillListFragment extends BaseFragment<WalletBillListContract.Presenter> implements
        WalletBillListContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wallet_bill_list;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new WalletBillListPresenter();
    }

    @Override
    protected void initView(View view) {

    }
}
