package baselib.base;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
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

        void finish();
    }


}
