package arouter.dawn.zju.edu.module_wallet.ui.alert_pay_password;


import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface WalletAlertPayPasswordContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void alertPayPassword(String payPassword);
    }

}
