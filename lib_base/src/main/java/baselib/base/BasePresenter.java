package baselib.base;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
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
