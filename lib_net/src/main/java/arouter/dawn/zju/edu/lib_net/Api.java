package arouter.dawn.zju.edu.lib_net;


import arouter.dawn.zju.edu.lib_net.bean.network.ObtainOrderRespense;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

    @GET("obtain_order")
    Observable<ObtainOrderRespense> obtainOrder();

}
