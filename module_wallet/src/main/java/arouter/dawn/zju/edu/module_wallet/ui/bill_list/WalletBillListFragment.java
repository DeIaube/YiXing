package arouter.dawn.zju.edu.module_wallet.ui.bill_list;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.wallet.Bill;
import arouter.dawn.zju.edu.module_wallet.R;
import arouter.dawn.zju.edu.module_wallet.adapter.WalletBillListAdapter;
import baselib.base.BaseFragment;

public class WalletBillListFragment extends BaseFragment<WalletBillListContract.Presenter> implements
        WalletBillListContract.View {

    RecyclerView walletBillListView;

    private WalletBillListAdapter mAdapter;

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
        walletBillListView = view.findViewById(R.id.wallet_bill_list_view);

        mAdapter = new WalletBillListAdapter(getContext(), new ArrayList<Bill>());
        walletBillListView.setLayoutManager(new LinearLayoutManager(getContext()));
        walletBillListView.setAdapter(mAdapter);
    }

    public void refresh(List<Bill> billList) {
        mAdapter.refresh(billList);
    }
}
