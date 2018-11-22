package arouter.dawn.zju.edu.module_mine.ui.feedback;


import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface FeedbackContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void submitFeedback(String title, String content);
    }

}
