package baselib.callback;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.Goods;

public interface GoodsListRefreshCallback {

    void refresh(List<Goods> goodsList);

}
