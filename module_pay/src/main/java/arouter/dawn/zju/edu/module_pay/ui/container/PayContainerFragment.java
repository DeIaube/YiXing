package arouter.dawn.zju.edu.module_pay.ui.container;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import arouter.dawn.zju.edu.module_pay.R;
import arouter.dawn.zju.edu.module_pay.config.Constants;
import arouter.dawn.zju.edu.module_pay.ui.cash_coupon.PayCashCouponFragment;
import arouter.dawn.zju.edu.module_pay.ui.home.PayHomeFragment;
import arouter.dawn.zju.edu.module_pay.ui.select_pay_type.PaySelectPayTypeFragment;
import baselib.bus.BusEvent;

public class PayContainerFragment extends BottomSheetDialogFragment implements View.OnKeyListener {

    public static final String TAG = "PayContainerFragment";

    private double price;
    private String title;
    private String content;

    private int payType;

    PayHomeFragment payHomeFragment;
    PayCashCouponFragment payCashCouponFragment;
    PaySelectPayTypeFragment paySelectPayTypeFragment;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_container, container, false);

        payType = Constants.PAY_TYPE_ALI;

        // 拦截父类的后退按键
        rootView.setFocusable(true);
        rootView.setFocusableInTouchMode(true);
        rootView.setOnKeyListener(this);

        payHomeFragment = new PayHomeFragment();
        payCashCouponFragment = new PayCashCouponFragment();
        paySelectPayTypeFragment = new PaySelectPayTypeFragment();

        getChildFragmentManager().beginTransaction().add(R.id.container, payHomeFragment).commit();

        return rootView;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                int count = getChildFragmentManager().getBackStackEntryCount();
                if (count < 0) {
                    return false;
                }
                if (count == 0) {
                    dismiss();
                } else {
                    getChildFragmentManager().popBackStack();
                }
                return true;
            }
        }
        return false;
    }

    public void show(FragmentManager manager, double price, String title, String content) {
        this.price = price;
        this.title = title;
        this.content = content;
        show(manager, this.getClass().getSimpleName());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEventinMainThread(BusEvent event) {
        if (event.getTarget() != null && !event.getTarget().equals(TAG)) {
            return;
        }
        if (event.getCode() == Constants.EVENT_SELETE_PAY_TYPE) {
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, paySelectPayTypeFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (event.getCode() == Constants.EVENT_SELETED_PAY_TYPE) {
            payType = (int) event.getData();
            getChildFragmentManager().popBackStack();
            if (payType == Constants.PAY_TYPE_ALI) {
                payHomeFragment.setPayType(getString(R.string.pay_type_ali));
            } else if (payType == Constants.PAY_TYPE_WALLET) {
                payHomeFragment.setPayType(getString(R.string.pay_type_wallet));
            }
        } else if (event.getCode() == Constants.EVENT_SELETE_CASH_COUPON) {
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, payCashCouponFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
