package arouter.dawn.zju.edu.module_wallet.ui.forward;


import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface WalletForwardContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void paySuccess(String title, String content, String source, double amount);
        void payFaile(String msg);
    }

}
