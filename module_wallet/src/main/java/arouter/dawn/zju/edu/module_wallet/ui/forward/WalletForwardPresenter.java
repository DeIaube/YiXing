package arouter.dawn.zju.edu.module_wallet.ui.forward;


import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_wallet.R;
import baselib.App;
import baselib.base.BasePresenter;

public class WalletForwardPresenter extends BasePresenter<WalletForwardContract.View> implements WalletForwardContract.Presenter {

    static final String TAG = "WalletForwardPresenter";

    @Override
    public void paySuccess(double amount) {
        mView.showMessage(App.getContext().getString(R.string.wallet_forward_success));
        User user = User.getCurrentUser(User.class);
        user.setBalance(user.getBalance() + amount);
        user.saveInBackground();
        mView.finish();
    }

    @Override
    public void pasFaile() {
        mView.showMessage(App.getContext().getString(R.string.wallet_forward_faile));
    }
}
