package arouter.dawn.zju.edu.module_pay.callback;

public class PayCallbackProxy implements PayCallback {

    private PayCallback payCallback;
    private String type;

    public PayCallback setType(String type) {
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
