package baselib.base2;

import androidx.lifecycle.LifecycleOwner;

public interface IView {
    void showLoading(String tips);
    void hideLoading();
    void showErrorView(String errorMsg);
    void makeToast(String tips);
    void finishView();
    LifecycleOwner getLifecycleOwner();
}