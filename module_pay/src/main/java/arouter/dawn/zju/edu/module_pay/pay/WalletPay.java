package arouter.dawn.zju.edu.module_pay.pay;

import android.app.Activity;
import android.view.View;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_pay.callback.PayCallback;

public class WalletPay {

    private Activity context;
    private double price;
    private String title;
    private String content;
    private PayCallback payCallback;

    WalletPay(Activity context, double price, String title, String content, PayCallback payCallback) {
        this.context = context;
        this.price = price;
        this.title = title;
        this.content = content;
        this.payCallback = payCallback;
    }

    public void pay(View v) {
        User user = User.getCurrentUser(User.class);
        if (user.getBalance() < price) {
            payCallback.payFailed();
        } else {
            user.setBalance(user.getBalance() - price);
            user.saveInBackground();
            payCallback.paySuccess();
        }
    }
}
