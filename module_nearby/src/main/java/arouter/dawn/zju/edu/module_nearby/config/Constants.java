package arouter.dawn.zju.edu.module_nearby.config;

public interface Constants {

    String DEFAULT_LOCATION = "宁波";

    String LAST_LOCATION = "last_location";

    String TYPE_ALL = "所有类型";
    String TYPE_LANGUAGE = "外语培训";
    String TYPE_MUSIC = "音乐培训";
    String TYPE_ART = "美术培训";
    String TYPE_JOB = "职业培训";

    int SORT_COMPREHENSIVE = 1; // 综合排序
    int SORT_PRICE_UP = 2; // 价格排序 高->低
    int SORT_PRICE_DOWN = 3; // 价格排序 低->高
    int SORT_DISTANCE = 4; // 距离排序

}
