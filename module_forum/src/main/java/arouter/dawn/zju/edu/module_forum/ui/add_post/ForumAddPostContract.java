package arouter.dawn.zju.edu.module_forum.ui.add_post;


import java.util.List;

import baselib.base.BaseContract;

public interface ForumAddPostContract {

    interface View extends BaseContract.BaseView {
        void submitSuccess();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void submit(String title, String content, String tag, List<String> images);
    }

}
