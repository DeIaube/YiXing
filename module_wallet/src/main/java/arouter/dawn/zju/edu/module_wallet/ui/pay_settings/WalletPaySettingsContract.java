package arouter.dawn.zju.edu.module_wallet.ui.pay_settings;


import baselib.base.BaseContract;

public interface WalletPaySettingsContract {

    interface View extends BaseContract.BaseView {
        void setFingerprintStatus(boolean status);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void refreshView();
        void openFingerprint();
        void closeFingerprint();
    }

}
