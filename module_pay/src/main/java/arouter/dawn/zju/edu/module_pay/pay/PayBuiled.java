package arouter.dawn.zju.edu.module_pay.pay;

import android.app.Activity;

import arouter.dawn.zju.edu.module_pay.callback.PayCallback;
import arouter.dawn.zju.edu.module_pay.callback.PayCallbackProxy;
import arouter.dawn.zju.edu.module_pay.pay.ali.AliPay;
import arouter.dawn.zju.edu.module_pay.pay.wallet.WalletPay;

public class PayBuiled {
    private Activity context;
    private double price;
    private String title;
    private String content;
    private PayCallbackProxy payCallbackProxy;

    public PayBuiled setPayCallback(PayCallback payCallback) {
        this.payCallbackProxy = new PayCallbackProxy(payCallback);
        return this;
    }

    public PayBuiled setPrice(double price) {
        this.price = price;
        return this;
    }

    public PayBuiled setTitle(String title) {
        this.title = title;
        return this;
    }

    public PayBuiled setContent(String content) {
        this.content = content;
        return this;
    }

    public PayBuiled(Activity context) {
        this.context = context;
    }

    public AliPay builedAliPay() {
        return new AliPay(context, price, title, content,
                payCallbackProxy.setType("支付宝")
                .setContent(content)
                .setPrice(price)
                .setTitle(title)
        );
    }

    public WalletPay builedWalletPay() {
        return new WalletPay(context, price, title, content,
                payCallbackProxy.setType("余额支付")
                        .setContent(content)
                        .setPrice(price)
                        .setTitle(title));
    }
}
