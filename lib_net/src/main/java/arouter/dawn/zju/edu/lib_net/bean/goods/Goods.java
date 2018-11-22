package arouter.dawn.zju.edu.lib_net.bean.goods;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import java.util.Date;
import java.util.List;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 商品实体类
 */
@AVClassName("Goods")
public class Goods extends AVObject {

    /**
     * preview : http://attach.bbs.miui.com/forum/201805/31/181909by8vfl4gxglq8z9h.jpg
     * price : 1999
     * title : 小米6
     * region : 高新区
     * city : 宁波
     * type : 数码产品
     * explain : 非全面屏智能拍照游戏手机学生机商务机 全网通 官方旗舰正品
     */

    public String getPreview() {
        return getString("preview");
    }

    public void setPreview(String preview) {
        put("preview", preview);
    }

    public double getPrice() {
        return getDouble("price");
    }

    public void setPrice(double price) {
        put("price", price);
    }

    public String getTitle() {
        return getString("title");
    }

    public void setTitle(String title) {
        put("title", title);
    }

    public String getRegion() {
        return getString("region");
    }

    public void setRegion(String region) {
        put("region", region);
    }

    public void setLocation(String location) {
        put("location", location);
    }

    public String getLocation() {
        return getString("location");
    }

    public String getCity() {
        return getString("city");
    }

    public void setCity(String city) {
        put("city", city);
    }

    public String getType() {
        return getString("type");
    }

    public void setType(String type) {
        put("type", type);
    }

    public String getExplain() {
        return getString("explain");
    }

    public void setExplain(String explain) {
        put("explain", explain);
    }

    public void setShop(String shop) {
        put("shop", shop);
    }

    public String getShop() {
        return getString("shop");
    }

    public void setStartTime(Date date) {
        put("startDate", date);
    }

    public Date getStartTime() {
        return getDate("startDate");
    }

    public void setEndTime(Date date) {
        put("endDate", date);
    }

    public Date getEndTime() {
        return getDate("endDate");
    }

    public void setPreviewList(List<String> previewList) {
        put("previewList", previewList);
    }

    public List getPreviewList() {
        return getList("previewList");
    }
}
