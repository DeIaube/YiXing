package arouter.dawn.zju.edu.module_account.ui.modify_pickname;

import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface ModifyPicknameContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void modifyPickname(String pickname);
    }

}
