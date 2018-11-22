package arouter.dawn.zju.edu.module_pay.ui.select_pay_type;

import android.view.View;

import org.greenrobot.eventbus.EventBus;

import arouter.dawn.zju.edu.module_pay.R;
import arouter.dawn.zju.edu.module_pay.config.Constants;
import arouter.dawn.zju.edu.module_pay.ui.container.PayContainerFragment;
import baselib.base.BaseFragment;
import baselib.bus.BusEvent;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 选择支付方式
 * 支付宝or微信or钱包余额
 */
public class PaySelectPayTypeFragment extends BaseFragment<PaySelectPayTypeContract.Presenter> implements
        View.OnClickListener, PaySelectPayTypeContract.View{

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pay_select_pay_type;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new PaySelectPayTypePresenter();
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
