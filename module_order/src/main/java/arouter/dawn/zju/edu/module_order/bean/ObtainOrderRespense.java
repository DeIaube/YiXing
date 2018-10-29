package arouter.dawn.zju.edu.module_order.bean;

import java.util.List;

import baselib.bean.OrderBean;

public class ObtainOrderRespense {

    /**
     * {
     *     "request": 200,
     *     "data": [
     *         {
     *     "id": "123123123123123123",
     *     "account": "15064700768",
     *     "goods_title": "我只是一个小测试",
     *     "goods_preview": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1540730977404&di=4e75b92d42235e5896c2c9cf97bc47a2&imgtype=0&src=http%3A%2F%2Fwx2.sinaimg.cn%2Forj360%2Fa8d43f7egy1fhobz9zkr3j20e80e8dja.jpg",
     *     "create_time": "2018-10-28",
     *     "pay_number": 256
     * },
     *         {
     *     "id": "123123123123123123",
     *     "account": "15064700768",
     *     "goods_title": "我只是一个小测试",
     *     "goods_preview": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1540730977404&di=4e75b92d42235e5896c2c9cf97bc47a2&imgtype=0&src=http%3A%2F%2Fwx2.sinaimg.cn%2Forj360%2Fa8d43f7egy1fhobz9zkr3j20e80e8dja.jpg",
     *     "create_time": "2018-10-28",
     *     "pay_number": 256
     * },
     *         {
     *     "id": "123123123123123123",
     *     "account": "15064700768",
     *     "goods_title": "我只是一个小测试",
     *     "goods_preview": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1540730977404&di=4e75b92d42235e5896c2c9cf97bc47a2&imgtype=0&src=http%3A%2F%2Fwx2.sinaimg.cn%2Forj360%2Fa8d43f7egy1fhobz9zkr3j20e80e8dja.jpg",
     *     "create_time": "2018-10-28",
     *     "pay_number": 256
     * }
     *     ]
     * }
     *
     *
     * request : 200
     * data : [{"id":"123123123123123123","account":"15064700768","goods_title":"我只是一个小测试","goods_preview":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1540730977404&di=4e75b92d42235e5896c2c9cf97bc47a2&imgtype=0&src=http%3A%2F%2Fwx2.sinaimg.cn%2Forj360%2Fa8d43f7egy1fhobz9zkr3j20e80e8dja.jpg","create_time":"2018-10-28","pay_number":256},{"id":"123123123123123123","account":"15064700768","goods_title":"我只是一个小测试","goods_preview":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1540730977404&di=4e75b92d42235e5896c2c9cf97bc47a2&imgtype=0&src=http%3A%2F%2Fwx2.sinaimg.cn%2Forj360%2Fa8d43f7egy1fhobz9zkr3j20e80e8dja.jpg","create_time":"2018-10-28","pay_number":256},{"id":"123123123123123123","account":"15064700768","goods_title":"我只是一个小测试","goods_preview":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1540730977404&di=4e75b92d42235e5896c2c9cf97bc47a2&imgtype=0&src=http%3A%2F%2Fwx2.sinaimg.cn%2Forj360%2Fa8d43f7egy1fhobz9zkr3j20e80e8dja.jpg","create_time":"2018-10-28","pay_number":256}]
     */

    private int request;
    private List<OrderBean> data;

    public int getRequest() {
        return request;
    }

    public void setRequest(int request) {
        this.request = request;
    }

    public List<OrderBean> getData() {
        return data;
    }

    public void setData(List<OrderBean> data) {
        this.data = data;
    }

}
