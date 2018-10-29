package arouter.dawn.zju.edu.lib_net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRequest {

    Api api;
    OkHttpClient client;

    private ApiRequest() {
        client = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
    }

    public static ApiRequest getSingle() {
        return ApiRequestHolder.request;
    }

    public Api getApi() {
        return api;
    }

    private static class ApiRequestHolder {
        public static ApiRequest request = new ApiRequest();
    }
}
