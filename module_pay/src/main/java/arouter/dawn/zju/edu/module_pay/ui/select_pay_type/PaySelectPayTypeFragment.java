package arouter.dawn.zju.edu.module_pay.ui.select_pay_type;

import android.view.View;

import org.greenrobot.eventbus.EventBus;

import arouter.dawn.zju.edu.module_pay.R;
import arouter.dawn.zju.edu.module_pay.config.Constants;
import arouter.dawn.zju.edu.module_pay.ui.container.PayContainerFragment;
import baselib.base.BaseFragment;
import baselib.bus.BusEvent;

public class PaySelectPayTypeFragment extends BaseFragment implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pay_select_pay_type;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.pay_type_ali_layout).setOnClickListener(this);
        view.findViewById(R.id.pay_type_wechat_layout).setOnClickListener(this);
        view.findViewById(R.id.pay_type_wallet_layout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.pay_type_ali_layout) {
            // 选择支付宝支付
            BusEvent busEvent = new BusEvent();
            busEvent.setCode(Constants.EVENT_SELETED_PAY_TYPE);
            busEvent.setTarget(PayContainerFragment.TAG);
            busEvent.setData(Constants.PAY_TYPE_ALI);
            EventBus.getDefault().post(busEvent);
        } else if (id == R.id.pay_type_wechat_layout) {
            // 选择微信支付
            showMessage("暂不支持微信支付");
        } else if (id == R.id.pay_type_wallet_layout) {
            // 选择余额支付
            BusEvent busEvent = new BusEvent();
            busEvent.setCode(Constants.EVENT_SELETED_PAY_TYPE);
            busEvent.setTarget(PayContainerFragment.TAG);
            busEvent.setData(Constants.PAY_TYPE_WALLET);
            EventBus.getDefault().post(busEvent);
        }
    }
}
