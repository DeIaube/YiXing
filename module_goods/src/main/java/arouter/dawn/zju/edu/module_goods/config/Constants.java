package arouter.dawn.zju.edu.module_goods.config;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface Constants {

    String DEFAULT_LOCATION = "宁波";

    String LAST_LOCATION = "last_location";

    String TYPE_ALL = "所有";
    String TYPE_LANGUAGE = "外语";
    String TYPE_MUSIC = "音乐";
    String TYPE_ART = "美术";
    String TYPE_JOB = "职业";
    String TYPE_SPORT = "运动";
    String TYPE_PHOTO = "摄影";
    String TYPE_COOK = "烹饪";
    String TYPE_MAKE_UP = "美妆";
    String TYPE_FLORICULTURE = "花艺";
    String TYPE_CALLIGRAPHY = "书法";

    int SORT_COMPREHENSIVE = 1; // 综合排序
    int SORT_PRICE_UP = 2; // 价格排序 高->低
    int SORT_PRICE_DOWN = 3; // 价格排序 低->高
    int SORT_DISTANCE = 4; // 距离排序

    int ORDER_TYPE_PAYMENT = 1;
    int ORDER_TYPE_COMPLETE_REQUE_EVALUATE = 2;

}
