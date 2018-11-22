package arouter.dawn.zju.edu.module_wallet.ui.bill_detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import java.text.SimpleDateFormat;

import arouter.dawn.zju.edu.lib_net.bean.wallet.Bill;
import arouter.dawn.zju.edu.module_wallet.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 我的账单详情页面
 */
@Route(path = Constants.AROUTER_WALLET_BILL_DETAIL)
public class WalletBillDetailActivity extends BaseActivity<WalletBillDetailContract.Presenter> implements
        WalletBillDetailContract.View {

    TextView walletBillDetailAmountTv;
    TextView walletBillDetailTypeTv;
    TextView walletBill_Detail_TimeTv;
    TextView walletBillDetailIdTv;
    TextView walletBillDetailSourceTv;

    @Autowired(name = Constants.WALLET_BILL_DETAIL_BUNDLE)
    Bundle bundle;

    Bill bill;

    @SuppressLint("DefaultLocale")
    @Override
    protected void initView() {
        bill = bundle.getParcelable(Constants.WALLET_BILL_DETAIL_BILL);

        walletBillDetailAmountTv = findViewById(R.id.wallet_bill_detail_amount);
        walletBillDetailTypeTv = findViewById(R.id.wallet_bill_detail_type);
        walletBill_Detail_TimeTv = findViewById(R.id.wallet_bill_detail_time);
        walletBillDetailIdTv = findViewById(R.id.wallet_bill_detail_id);
        walletBillDetailSourceTv = findViewById(R.id.wallet_bill_detail_source);

        walletBillDetailSourceTv.setText(bill.getSource());
        walletBillDetailIdTv.setText(bill.getObjectId());
        walletBillDetailTypeTv.setText(bill.getDeal());
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        walletBill_Detail_TimeTv.setText(sdf.format(bill.getUpdatedAt()));
        if(bill.getType()) {
            walletBillDetailAmountTv.setText(String.format("+%.2f", bill.getAmount()));
            walletBillDetailAmountTv.setTextColor(getResources().getColor(R.color.colorPrimary));
        } else {
            walletBillDetailAmountTv.setText(String.format("-%.2f", bill.getAmount()));
            walletBillDetailAmountTv.setTextColor(getResources().getColor(R.color.black));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet_bill_detail;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new WalletBillDetailPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
