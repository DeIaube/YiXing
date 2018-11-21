package arouter.dawn.zju.edu.module_wallet.ui.non_secret_payment;


import baselib.base.BaseContract;

public interface WalletNonSecretPaymentContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void updateSecretPayment(int quota);
    }

}
