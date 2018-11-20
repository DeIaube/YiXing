package arouter.dawn.zju.edu.module_pay.ui.home;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;

import org.greenrobot.eventbus.EventBus;

import arouter.dawn.zju.edu.lib_net.bean.order.CashCoupon;
import arouter.dawn.zju.edu.module_pay.R;
import arouter.dawn.zju.edu.module_pay.ui.container.PayContainerFragment;
import baselib.base.BaseFragment;
import baselib.bus.BusEvent;
import baselib.config.Constants;

public class PayHomeFragment extends BaseFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    TextView payHomeCashCouponTv;
    TextView payHomePayTypeTv;
    TextView payHomePayAmountTv;
    CheckBox payHomePayAgreementCb;
    Button payHomeSubmitBtn;

    private CashCoupon mCashCoupon;

    @Override
    protected void initView(View view) {
        payHomeCashCouponTv = view.findViewById(R.id.pay_home_cash_coupon);
        payHomePayTypeTv = view.findViewById(R.id.pay_home_pay_type);
        payHomePayAmountTv = view.findViewById(R.id.pay_home_pay_amount);
        payHomePayAgreementCb = view.findViewById(R.id.pay_home_pay_protocol);
        payHomeSubmitBtn = view.findViewById(R.id.pay_home_submit);

        payHomePayAgreementCb.setOnCheckedChangeListener(this);

        view.findViewById(R.id.pay_home_cash_coupon_layout).setOnClickListener(this);
        view.findViewById(R.id.pay_home_invoice_layout).setOnClickListener(this);
        view.findViewById(R.id.pay_home_pay_type_layout).setOnClickListener(this);
        view.findViewById(R.id.pay_home_pay_protocol_layout).setOnClickListener(this);
        view.findViewById(R.id.pay_home_submit).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pay_home;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.pay_home_cash_coupon_layout) {
            // 选择代金券
            BusEvent busEvent = new BusEvent();
            busEvent.setTarget(PayContainerFragment.TAG);
            busEvent.setCode(arouter.dawn.zju.edu.module_pay.config.Constants.EVENT_SELETE_CASH_COUPON);
            EventBus.getDefault().post(busEvent);
        } else if (id == R.id.pay_home_invoice_layout) {
            // 选择开发票
            showMessage("当前不支持开发票");
        } else if (id == R.id.pay_home_pay_type_layout) {
            // 选择支付方式
            BusEvent busEvent = new BusEvent();
            busEvent.setTarget(PayContainerFragment.TAG);
            busEvent.setCode(arouter.dawn.zju.edu.module_pay.config.Constants.EVENT_SELETE_PAY_TYPE);
            EventBus.getDefault().post(busEvent);
        } else if (id == R.id.pay_home_pay_protocol_layout) {
            // 查看支付协议
            ARouter.getInstance().build(Constants.AROUTER_PAY_PROTOTOL).navigation();
        } else if (id == R.id.pay_home_submit) {
            // 提交
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            payHomeSubmitBtn.setClickable(true);
            payHomeSubmitBtn.setBackgroundResource(R.drawable.pay_home_submit_able_bg);
        } else {
            payHomeSubmitBtn.setClickable(false);
            payHomeSubmitBtn.setBackgroundResource(R.drawable.pay_home_submit_unable_bg);
        }
    }

    public void setPayType(String type) {
        payHomePayTypeTv.setText(type);
    }

    public void setCashCoupon(CashCoupon cashCoupon) {
        mCashCoupon = cashCoupon;
        if (mCashCoupon == null) {
            payHomeCashCouponTv.setText("不使用");
        } else {
            payHomeCashCouponTv.setText(mCashCoupon.getTitle());
        }
    }
}
