package arouter.dawn.zju.edu.lib_net.bean.goods;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import arouter.dawn.zju.edu.lib_net.bean.User;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 用户收藏商品实体类
 */
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
