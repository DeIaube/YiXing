package arouter.dawn.zju.edu.module_pay.callback;

import arouter.dawn.zju.edu.lib_net.bean.User;

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
        payCallback.paySuccess();
    }

    private void updateUserShopPoint() {
        User user = User.getCurrentUser(User.class);
        user.setShopPoint((int) (user.getShopPoint() + price));
        user.saveInBackground();
    }

    @Override
    public void payFailed() {
        payCallback.payFailed();
    }
}
