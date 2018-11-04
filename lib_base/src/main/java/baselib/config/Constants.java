package baselib.config;

public interface Constants {

    // isDebug
    boolean isDebug = true;

    String CLOUD_APPLICATION_ID = "otc2yunsXXUGetz84g9NM9eX-gzGzoHsz";
    String CLOUD_CLIENT_LEY = "woIpcGUxFiOUevOnNXfiadcG";

    // App
    String AROUTER_APP_GROP = "app";
    String AROUTER_APP_MAIN = "/app/main_main";

    // Account
    String AROUTER_ACCOUNT_GROP = "account";
    String AROUTER_ACCOUNT_LOGIN = "/account/login";
    String AROUTER_ACCOUNT_REGISTER = "/account/register";
    String AROUTER_ACCOUNT_SET_PASSWORD = "/account/set_password";
    String AROUTER_ACCOUNT_RESET_PASSWORD = "/account/reset_password";
    String ACCOUNT_PHONE_NUMBER = "accountPhoneNumber";

    // Goods
    String AROUTER_GOODS_GROP = "goods";
    String AROUTER_GOODS_NEARBY = "/goods/nearby";
    String AROUTER_GOODS_SEARCH = "/goods/search";
    String AROUTER_GOODS_CART = "/goods/cart";
    String AROUTER_GOODS_GOODS_LIST = "/goods/goods_list";

    // Order
    String AROUTER_ORDER_GROP = "order";
    String AROUTER_ORDER_EVALUATE = "/order/evaluate";
    String AROUTER_ORDER_ORDER = "/order/order";
    String AROUTER_ORDER_LIST = "/order/list";
    String ORDER_GOODS_TITLE = "orderGoodsTitle";
    String ORDER_GOODS_PREVIEW = "orderGoodsPreview";
    String ORDER_GOODS_ID = "orderGoodsId";

    // Setting
    String AROUTER_SETTING_GROP = "setting";
    String AROUTER_SETTING_MINE = "/setting/mine";
    String AROUTER_SETTING_SETTING = "/setting/setting";
    String AROUTER_SETTING_NOTICE = "/setting/notice";
    String AROUTER_SETTING_COLLECTION = "/setting/collection";
    String AROUTER_SETTING_FEEDBACK = "/setting/feedback";
}
