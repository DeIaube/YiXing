package arouter.dawn.zju.edu.module_pay.callback;

public interface PayCallback {
    void paySuccess();
    void payFailed(String msg);
}
