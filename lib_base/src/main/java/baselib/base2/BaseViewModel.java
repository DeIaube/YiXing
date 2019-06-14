package baselib.base2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel<V extends IView, R extends IRepository> extends AndroidViewModel {
    public static final int INDEX_VIEW = 0;
    public static final int INDEX_REPOSITORY = 1;
    protected CompositeDisposable disposables;
    protected V view;
    protected R repository;

    public BaseViewModel(@NonNull Application application, V view, R repository) {
        super(application);
        this.repository = repository;
        this.view = view;
        this.disposables = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if(disposables != null){
            disposables.clear();
        }
    }
}
