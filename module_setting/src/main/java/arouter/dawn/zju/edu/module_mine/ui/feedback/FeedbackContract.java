package arouter.dawn.zju.edu.module_mine.ui.feedback;


import baselib.base.BaseContract;

public interface FeedbackContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void submitFeedback(String title, String content);
    }

}
