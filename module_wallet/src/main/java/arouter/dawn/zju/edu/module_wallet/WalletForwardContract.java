package arouter.dawn.zju.edu.module_wallet;


import baselib.base.BaseContract;

public interface WalletForwardContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<View> {
    }

}
