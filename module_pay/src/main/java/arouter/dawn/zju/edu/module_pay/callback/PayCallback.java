package arouter.dawn.zju.edu.module_pay.callback;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 支付结果回调
 */
public interface PayCallback {
    void paySuccess();
    void payFailed(String msg);
}
