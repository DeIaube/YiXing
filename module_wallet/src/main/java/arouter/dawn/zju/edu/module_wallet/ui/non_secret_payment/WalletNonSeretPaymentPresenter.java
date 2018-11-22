package arouter.dawn.zju.edu.module_wallet.ui.non_secret_payment;


import arouter.dawn.zju.edu.lib_net.bean.User;
import baselib.base.BasePresenter;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class WalletNonSeretPaymentPresenter extends BasePresenter<WalletNonSecretPaymentContract.View> implements WalletNonSecretPaymentContract.Presenter {

    static final String TAG = "WalletNonSeretPayPresenter";

    @Override
    public void updateSecretPayment(int quota) {
        User user = User.getCurrentUser(User.class);
        user.setSeretPayment(quota);
        user.saveInBackground();
    }
}
