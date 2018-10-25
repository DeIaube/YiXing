package yixing.dawn.zju.edu.yixing.ui.login;

import yixing.dawn.zju.edu.yixing.base.BaseContract;

public interface LoginContract {

    interface View extends BaseContract.BaseView {

        /**
         * 登录成功
         */
        void showLoginSuccess();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        /**
         * 登录
         * @param account 用户名
         * @param password 密码
         */
        void login(String account, String password);
    }

}
