package arouter.dawn.zju.edu.module_account.ui.login;

import baselib.base.BaseContract;

public interface LoginContract {

    interface View extends BaseContract.BaseView {
        /**
         * 登录回调
         * @param result 登录结果
         */
        void loginRsult(boolean result);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        /**
         * 登录
         * @param phoneNumber 手机号
         * @param password 密码
         */
        void login(String phoneNumber, String password);

        /**
         *  三种第三方登录方式
         */
        void loginByQQ();
        void loginByWeibo();
        void loginByWechat();
    }

}
