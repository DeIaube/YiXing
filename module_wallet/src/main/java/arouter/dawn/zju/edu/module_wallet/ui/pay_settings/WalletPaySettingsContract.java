package arouter.dawn.zju.edu.module_wallet.ui.pay_settings;


import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
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
