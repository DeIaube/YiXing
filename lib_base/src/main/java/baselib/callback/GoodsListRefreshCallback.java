package baselib.callback;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.goods.Goods;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 刷新商品数据接口
 */
public interface GoodsListRefreshCallback {

    void refresh(List<Goods> goodsList);

}
