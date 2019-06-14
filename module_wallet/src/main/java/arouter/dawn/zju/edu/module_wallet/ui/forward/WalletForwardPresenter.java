package arouter.dawn.zju.edu.module_wallet.ui.forward;


import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.lib_net.bean.wallet.Bill;
import arouter.dawn.zju.edu.module_wallet.R;
import baselib.App;
import baselib.base.BasePresenter;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class WalletForwardPresenter extends BasePresenter<WalletForwardContract.View> implements WalletForwardContract.Presenter {

    static final String TAG = "WalletForwardPresenter";

    @Override
    public void paySuccess(String title, String content, String source, double amount) {
        mView.showMessage(App.getAppalication().getString(R.string.wallet_forward_success));

        User user = User.getCurrentUser(User.class);
        user.setBalance(user.getBalance() + amount);
        user.saveInBackground();

        Bill bill = new Bill();
        bill.setOwner(User.getCurrentUser(User.class));
        bill.setType(true);
        bill.setSource(source);
        bill.setAmount(amount);
        bill.setDeal(title);
        bill.saveInBackground();

        mView.finish();
    }

    @Override
    public void payFaile(String msg) {
        mView.showMessage(App.getAppalication().getString(R.string.wallet_forward_faile));
    }

}
