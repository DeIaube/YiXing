package yixing.dawn.zju.edu.yixing.base;

public interface BaseContract {

    interface BasePresenter<T extends BaseContract.BaseView> {

        void attachView(T view);

        void detachView();
    }

    interface BaseView {
        void showLoading();

        void hideLoading();

        void showSuccess(String msg);

        void showFaild(String msg);

        void showNetworkError();

        void onRetry();
    }


}
