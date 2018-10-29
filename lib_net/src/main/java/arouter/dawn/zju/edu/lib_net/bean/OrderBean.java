package arouter.dawn.zju.edu.lib_net.bean;

public class OrderBean {

    /**
     *
     * {
     *     "id": "123123123123123123",
     *     "account": "15064700768",
     *     "goods_title": "我只是一个小测试",
     *     "goods_preview": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1540730977404&di=4e75b92d42235e5896c2c9cf97bc47a2&imgtype=0&src=http%3A%2F%2Fwx2.sinaimg.cn%2Forj360%2Fa8d43f7egy1fhobz9zkr3j20e80e8dja.jpg",
     *     "create_time": "2018-10-28",
     *     "pay": 256,
     *     "type": "待付款"
     * }
     *
     * id : 123123123123123123
     * account : 15064700768
     * goods_title : 我只是一个小测试
     * goods_preview : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1540730977404&di=4e75b92d42235e5896c2c9cf97bc47a2&imgtype=0&src=http%3A%2F%2Fwx2.sinaimg.cn%2Forj360%2Fa8d43f7egy1fhobz9zkr3j20e80e8dja.jpg
     * create_time : 2018-10-28
     * pay : 256
     * type : 待付款
     */

    private String id;
    private String account;
    private String goods_title;
    private String goods_preview;
    private String create_time;
    private int pay;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getGoods_title() {
        return goods_title;
    }

    public void setGoods_title(String goods_title) {
        this.goods_title = goods_title;
    }

    public String getGoods_preview() {
        return goods_preview;
    }

    public void setGoods_preview(String goods_preview) {
        this.goods_preview = goods_preview;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
