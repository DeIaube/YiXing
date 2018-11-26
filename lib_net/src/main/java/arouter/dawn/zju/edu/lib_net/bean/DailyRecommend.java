package arouter.dawn.zju.edu.lib_net.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import arouter.dawn.zju.edu.lib_net.bean.goods.Goods;

/**
 * @Auther: Dawn
 * @Date: 2018/11/26 22:09
 * @Description:
 * 每日推荐
 */
@AVClassName("DailyRecommend")
public class DailyRecommend extends AVObject {

    public String getPreview() {
        return getString("preview");
    }

    public void setPreview(String preview) {
        put("preview", preview);
    }

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

}
