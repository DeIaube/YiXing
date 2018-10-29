package arouter.dawn.zju.edu.lib_net;


import arouter.dawn.zju.edu.lib_net.bean.network.ObtainOrderRespense;
import arouter.dawn.zju.edu.lib_net.bean.network.SearchGoodsRespense;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

    @GET(Constants.OBTAIN_ORDER)
    Observable<ObtainOrderRespense> obtainOrder();

    @GET(Constants.SEARCH)
    Observable<SearchGoodsRespense> search();

}
