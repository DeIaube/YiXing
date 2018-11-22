package arouter.dawn.zju.edu.module_account.ui.personal;

import java.util.Date;

import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface PersionContract {

    interface View extends BaseContract.BaseView {
        void refreshUserPortrait(String url);
        void refreshUserBirth(Date date);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void updateUserPortrait(String path);
        void updateUserBirth(int year, int month, int day);
    }

}
