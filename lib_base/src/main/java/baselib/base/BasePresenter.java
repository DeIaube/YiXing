package baselib.base;

public class BasePresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {

    protected T mView;

    @Override
    public void attachView(BaseContract.BaseView view) {
        this.mView = (T) view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }
}
