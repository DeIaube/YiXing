package baselib.callback;

import java.util.List;

import baselib.bean.Goods;

public interface GoodsListRefreshCallback {

    void refresh(List<Goods> goodsList);

}
