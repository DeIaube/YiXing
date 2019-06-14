package baselib.base2;

import android.app.Application;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 如果不使用统一的DefaultRepository，请务必集成BaseRepository
 */
public class BaseRepository implements IRepository {
    protected Application application;
    private static ConcurrentHashMap<String, Object> retrofitServiceCache;

    public BaseRepository(Application application) {
        this.application = application;
    }

    @SuppressWarnings("unchecked")
    public static synchronized <T> T obtainRetrofitService(Class<T> service) {
        if (retrofitServiceCache == null) {
            retrofitServiceCache = new ConcurrentHashMap<>();
        }
        T retrofitService = (T) retrofitServiceCache.get(service.getCanonicalName());
        if(retrofitService == null){
            retrofitServiceCache.put(service.getCanonicalName(), retrofitService);
        }
        return retrofitService;
    }
}
