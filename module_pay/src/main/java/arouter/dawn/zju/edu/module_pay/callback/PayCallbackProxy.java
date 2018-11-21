package arouter.dawn.zju.edu.module_pay.callback;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.lib_net.bean.wallet.Bill;

public class PayCallbackProxy implements PayCallback {

    private PayCallback payCallback;
    private String type;
    private double price;
    private String title;
    private String content;

    public PayCallbackProxy setPrice(double price) {
        this.price = price;
        return this;
    }

    public PayCallbackProxy setTitle(String title) {
        this.title = title;
        return this;
    }

    public PayCallbackProxy setContent(String content) {
        this.content = content;
        return this;
    }

    public PayCallbackProxy setType(String type) {
        this.type = type;
        return this;
    }

    public PayCallbackProxy(PayCallback payCallback) {
        this.payCallback = payCallback;
    }

    @Override
    public void paySuccess() {
        updateUserShopPoint();
        updateUserBill();
        payCallback.paySuccess();
    }

    private void updateUserBill() {
        Bill bill = new Bill();
        bill.setOwner(User.getCurrentUser(User.class));
        bill.setType(false);
        bill.setAmount(price);
        bill.setDeal(title);
        bill.setSource(type);
        bill.saveInBackground();
    }

    private void updateUserShopPoint() {
        User user = User.getCurrentUser(User.class);
        user.setShopPoint((int) (user.getShopPoint() + price));
        user.saveInBackground();
    }

    @Override
    public void payFailed(String msg) {
        payCallback.payFailed(msg);
    }
}
