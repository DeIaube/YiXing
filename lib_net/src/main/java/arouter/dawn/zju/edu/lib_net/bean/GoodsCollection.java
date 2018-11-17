package arouter.dawn.zju.edu.lib_net.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

@AVClassName("GoodsCollection")
public class GoodsCollection extends AVObject {

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

    public void setOwner(User user) {
        put("user", user);
    }

    public User getOwner() {
        return getAVUser("user", User.class);
    }

}
