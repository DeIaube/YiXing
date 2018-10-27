package arouter.dawn.zju.edu.module_account.login;

import baselib.base.BaseContract;

public interface LoginContract {

    interface View extends BaseContract.BaseView {

        /**
         * 登陆结果
         */
        void loginSuccess();
        void loginFailed();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        /**
         * 登录
         * @param account 用户名
         * @param password 密码
         */
        void login(String account, String password);

        /**
         *  三种第三方登录方式
         */
        void loginByQQ();
        void loginByWeibo();
        void loginByWechat();
    }

}
