package arouter.dawn.zju.edu.module_forum.ui.add_post;


import java.util.List;

import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface ForumAddPostContract {

    interface View extends BaseContract.BaseView {
        void submitSuccess();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void submit(String title, String content, String tag, List<String> images);
    }

}
