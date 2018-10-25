package yixing.dawn.zju.edu.yixing.base;

public interface BaseContract {

    interface BasePresenter<T extends BaseContract.BaseView> {

        void attachView(T view);

        void detachView();
    }

    interface BaseView {
        void showLoading();

        void hideLoading();

        void showMessage(String msg);

        void showNetworkError();
    }


}
