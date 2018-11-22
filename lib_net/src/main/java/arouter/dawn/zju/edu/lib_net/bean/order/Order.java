package arouter.dawn.zju.edu.lib_net.bean.order;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import arouter.dawn.zju.edu.lib_net.bean.goods.Goods;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 订单实体类
 */
@AVClassName("Order")
public class Order extends AVObject {

    public void setGoods(Goods goods) {
        put("goods", goods);
    }

    public Goods getGoods() {
        try {
            return getAVObject("goods", Goods.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 1:待付款
     * 2:待评价
     * 3:已评价
     * @return type
     */
    public int getType() {
        return getInt("type");
    }

    public void setType(int type) {
        put("type", type);
    }

}
