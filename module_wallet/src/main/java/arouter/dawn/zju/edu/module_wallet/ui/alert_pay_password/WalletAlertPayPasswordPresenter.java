package arouter.dawn.zju.edu.module_wallet.ui.alert_pay_password;


import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_wallet.R;
import baselib.App;
import baselib.base.BasePresenter;

public class WalletAlertPayPasswordPresenter extends BasePresenter<WalletAlertPayPasswordContract.View> implements WalletAlertPayPasswordContract.Presenter {

    static final String TAG = "WalletAlertPayPasswordPresenter";

    @Override
    public void alertPayPassword(String payPassword) {
        User user = User.getCurrentUser(User.class);
        user.setPayPassword(payPassword);
        user.saveInBackground();
        mView.showMessage(App.getContext().getString(R.string.alert_pay_password_alert_success));
        mView.finish();
    }
}
