package arouter.dawn.zju.edu.module_pay.pay;

import android.app.Activity;

import arouter.dawn.zju.edu.module_pay.callback.PayCallback;

public class PayBuiled {
    private Activity context;
    private double price;
    private String title;
    private String content;
    private PayCallback payCallback = new PayCallback() {
        @Override
        public void paySuccess() {

        }

        @Override
        public void payFailed() {

        }
    };

    public PayBuiled setPayCallback(PayCallback payCallback) {
        this.payCallback = payCallback;
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

    public AliPay buileAliPay() {
        return new AliPay(context, price, title, content, payCallback);
    }
}
