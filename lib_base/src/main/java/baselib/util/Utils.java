package baselib.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

@SuppressWarnings("SpellCheckingInspection")
public class Utils {

    private static final String TAG = "Arc";

    @NonNull
    public static <T> LiveData<T> fromObservable(@NonNull ObservableSource<T> observableSource) {
        return new ObservableLiveData<>(observableSource);
    }

    private static class ObservableLiveData<T> extends LiveData<T> {
        private final ObservableSource<T> observableSource;
        final AtomicReference<LiveDataObserver> observer;

        ObservableLiveData(@NonNull ObservableSource<T> observableSource) {
            this.observableSource = observableSource;
            observer = new AtomicReference<>();
        }

        @Override
        protected void onActive() {
            super.onActive();
            LiveDataObserver s = new LiveDataObserver();
            observer.set(s);
            observableSource.subscribe(s);
        }

        @Override
        protected void onInactive() {
            super.onInactive();
            LiveDataObserver s = observer.getAndSet(null);
            if (s != null) {
                s.cancelSubscription();
            }
        }

        final class LiveDataObserver extends AtomicReference<Disposable> implements Observer<T> {

            @Override
            public void onSubscribe(Disposable d) {
                if (!compareAndSet(null, d)) {
                    d.dispose();
                }
            }

            @Override
            public void onNext(T item) {
                postValue(item);
            }

            @Override
            public void onError(final Throwable ex) {
                observer.compareAndSet(this, null);
                postValue(null);
            }

            @Override
            public void onComplete() {
                observer.compareAndSet(this, null);
            }

            public void cancelSubscription() {
                Disposable d = get();
                if (d != null) {
                    d.dispose();
                }
            }
        }
    }

    public static <T extends ViewDataBinding> T injectDataBinding(Activity activity, Class<?> clazz, int index, ViewGroup root, Boolean attachToRoot) {
        Class<T> bindingClass = Utils.getGenericClass(activity, clazz, index);
        return Utils.invokeMethod(bindingClass, null, "inflate", new Class<?>[]{LayoutInflater.class, ViewGroup.class, boolean.class}, new Object[]{LayoutInflater.from(activity), root, attachToRoot});
    }

    public static <T extends ViewDataBinding> T injectDataBinding(Fragment fragment, Class<?> clazz, int index, ViewGroup root, Boolean attachToRoot) {
        Class<T> bindingClass = Utils.getGenericClass(fragment, clazz, index);
        return Utils.invokeMethod(bindingClass, null, "inflate", new Class<?>[]{LayoutInflater.class, ViewGroup.class, boolean.class}, new Object[]{fragment.getLayoutInflater(), root, attachToRoot});
    }

    public static <T> T invokeMethod(Class<?> clazz, Object object, String methodName, Class<?>[] classes, Object[] params) {
        try {
            Method method = clazz.getMethod(methodName, classes);
            return (T) method.invoke(object, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T, V> void injectDataBindingValue(T databinding, V value) {
        try {
            if (value == null) {
                return;
            }
            Method[] methods = databinding.getClass().getMethods();
            for (Method method : methods) {
                if (method != null) {
                    Class<?>[] paramTypes = method.getParameterTypes();
                    if (paramTypes != null && paramTypes.length == 1
                            && paramTypes[0] != null
                            && !paramTypes[0].equals(Object.class)
                            && paramTypes[0].isAssignableFrom(value.getClass())) {
                        method.invoke(databinding, new Object[]{value});
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> Class<T> getGenericClass(Class<?> clazz, int index) {
        try {
            return (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[index];
        } catch (Exception e) {
            Log.d(TAG, "calss is " + clazz);
            e.printStackTrace();
        }
        return null;
    }

    public static <T> Class<T> getGenericClass(Object instance, Class<?> clazz, int index) {
        try {
            Class<?> startClazz = instance.getClass();
            Log.d(TAG, "startClass is " + startClazz);
            while (!startClazz.getSuperclass().equals(clazz) && !startClazz.getSuperclass().equals(Object.class)) {
                startClazz = startClazz.getSuperclass();
            }
            if (!startClazz.equals(Object.class)) {
                Log.d(TAG, "endClass is " + startClazz);
                Log.d(TAG, "genericSuperclass is " + startClazz.getGenericSuperclass());
                Class<T> viewModelClass = (Class<T>) ((ParameterizedType) startClazz.getGenericSuperclass()).getActualTypeArguments()[index];
                return viewModelClass;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
