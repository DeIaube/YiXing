package arouter.dawn.zju.edu.lib_net.bean.network;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.GoodsBean;

public class SearchGoodsRespense {

    /**
     * request : 200
     * data : [{"id":"123123123123123123","title":"小米8 青春版","explain":"全面屏智能拍照游戏手机学生机商务机 全网通 官方旗舰正品","preview":"http://attach.bbs.miui.com/forum/201805/31/181909by8vfl4gxglq8z9h.jpg","create_time":"2018-10-28","city":"宁波","type":"数码产品","price":2399},{"id":"123123123123123123","title":"小米6","explain":"非全面屏智能拍照游戏手机学生机商务机 全网通 官方旗舰正品","preview":"http://attach.bbs.miui.com/forum/201805/31/181909by8vfl4gxglq8z9h.jpg","create_time":"2018-10-28","city":"宁波","type":"数码产品","price":1999}]
     */

    private int request;
    private List<GoodsBean> data;

    public int getRequest() {
        return request;
    }

    public void setRequest(int request) {
        this.request = request;
    }

    public List<GoodsBean> getData() {
        return data;
    }

    public void setData(List<GoodsBean> data) {
        this.data = data;
    }

}
