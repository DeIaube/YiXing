package arouter.dawn.zju.edu.module_pay.callback;

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
        payCallback.paySuccess();
    }

    @Override
    public void payFailed() {
        payCallback.payFailed();
    }
}
