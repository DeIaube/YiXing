package baselib.base2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import baselib.util.Utils;

/**
 * ViewModel工厂类
 * 自动创建泛型中带的Repository，传入泛型中带的View接口
 * 创建的ViewModelClass必须继承自BaseViewModel，View必须BaseActivity或BaseTitleActivity,仓库必须继承自BaseRepository
 */
public class AppViewModelFactory implements ViewModelProvider.Factory {
    private Application application;
    private IView view;

    public AppViewModelFactory(Application application, IView view) {
        this.application = application;
        this.view = view;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            Class<?> repositoryClass = getActualType(modelClass, BaseViewModel.INDEX_REPOSITORY, IRepository.class);
            IRepository repository = (IRepository) repositoryClass.getConstructor(Application.class).newInstance(application);
            return modelClass.getConstructor(Application.class, getActualType(modelClass, BaseViewModel.INDEX_VIEW, IView.class)
                    , repositoryClass)
                    .newInstance(application, view, repository);
        } catch (Exception e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass + ", viewModel must extends BaseViewModel and repo must have default constructor", e);
        }
    }

    private Class<?> getActualType(Class<?> clazz, int index, Class<?> defaultClazz){
        Class<?> actualClazz = Utils.getGenericClass(clazz, index);
        return actualClazz != null ? actualClazz : defaultClazz;
    }
}
