package arouter.dawn.zju.edu.module_wallet.ui.non_secret_payment;


import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface WalletNonSecretPaymentContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void updateSecretPayment(int quota);
    }

}
