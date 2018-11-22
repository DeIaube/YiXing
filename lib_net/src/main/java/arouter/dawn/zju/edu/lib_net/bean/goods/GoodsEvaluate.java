package arouter.dawn.zju.edu.lib_net.bean.goods;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 用户评价商品实体类
 */
@AVClassName("GoodsEvaluate")
public class GoodsEvaluate extends AVObject {

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

    public String getContent() {
        return getString("content");
    }

    public void setContent(String type) {
        put("content", type);
    }

    public int getLevel() {
        return getInt("level");
    }

    public void setLevel(int type) {
        put("level", type);
    }

}
