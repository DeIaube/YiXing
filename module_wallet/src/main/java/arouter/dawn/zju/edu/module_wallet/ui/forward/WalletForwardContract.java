package arouter.dawn.zju.edu.module_wallet.ui.forward;


import baselib.base.BaseContract;

public interface WalletForwardContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void paySuccess(String title, String content, String source, double amount);
        void payFaile(String msg);
    }

}
