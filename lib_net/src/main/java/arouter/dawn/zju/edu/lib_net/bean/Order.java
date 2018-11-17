package arouter.dawn.zju.edu.lib_net.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

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
     * 2:已取消
     * 3:待评价
     * 4:已评价
     * @return type
     */
    public int getType() {
        return getInt("type");
    }

    public void setType(int type) {
        put("type", type);
    }

}
