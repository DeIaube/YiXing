package arouter.dawn.zju.edu.module_order.ui.order;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import arouter.dawn.zju.edu.lib_net.Api;
import arouter.dawn.zju.edu.lib_net.ApiRequest;
import arouter.dawn.zju.edu.lib_net.bean.network.ObtainOrderRespense;
import arouter.dawn.zju.edu.module_order.adapter.OrderPagerAdapter;
import arouter.dawn.zju.edu.module_order.config.Constants;
import arouter.dawn.zju.edu.module_order.ui.order_list.OrderListFragment;
import baselib.base.BasePresenter;
import arouter.dawn.zju.edu.lib_net.bean.OrderBean;
import baselib.util.LogUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class OrderPresenter extends BasePresenter<OrderContract.View> implements OrderContract.Presenter {

    private static final String TAG = "OrderPresenter";

    private Map<String, List<OrderBean>> ordersMap;
    private List<String> titles;
    private List<Fragment> fragments;

    @SuppressLint("CheckResult")
    @Override
    public void bindViewPager(final FragmentManager fragmentManager, final ViewPager viewPager, final TabLayout tabLayout) {
        ordersMap = new HashMap<>();
        ordersMap.put(Constants.ORDER_TYPE_ALL, new ArrayList<OrderBean>());
        ordersMap.put(Constants.ORDER_TYPE_PAYMENT, new ArrayList<OrderBean>());
        ordersMap.put(Constants.ORDER_TYPE_CANCEL, new ArrayList<OrderBean>());
        ordersMap.put(Constants.ORDER_TYPE_COMPLETE, new ArrayList<OrderBean>());

        titles = new ArrayList<>();
        titles.add("全部");
        titles.add("待付款");
        titles.add("已取消");
        titles.add("已完成");

        fragments = new ArrayList<>();
        fragments.add(new OrderListFragment());
        fragments.add(new OrderListFragment());
        fragments.add(new OrderListFragment());
        fragments.add(new OrderListFragment());

        OrderPagerAdapter adapter = new OrderPagerAdapter(fragmentManager, titles, fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        refresh();
    }

    @SuppressLint("CheckResult")
    @Override
    public void refresh() {
        mView.showSwipeRefreshLayout();
        Api api = ApiRequest.getSingle().getApi();
        api.obtainOrder()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ObtainOrderRespense>() {
                    @Override
                    public void accept(ObtainOrderRespense obtainOrderRespense) throws Exception {
                        LogUtil.i(TAG, obtainOrderRespense.toString());
                        mView.hideSwipeRefreshLayout();
                        List<OrderBean> orders = obtainOrderRespense.getData();
                        for (List<OrderBean> orderList : ordersMap.values()) {
                            orderList.clear();
                        }
                        for (OrderBean order : orders) {
                            Objects.requireNonNull(ordersMap.get("全部")).add(order);
                            Objects.requireNonNull(ordersMap.get(order.getType())).add(order);
                        }
                        refreshOrderListFragment();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.e(TAG, throwable.toString());
                        mView.showSwipeRefreshLayout();
                    }
                });
    }

    /**
     * 通过现有数据刷新ViewPager下所有页面
     */
    private void refreshOrderListFragment() {
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            if (fragment instanceof OrderListFragment) {
                OrderListFragment orderListFragment = (OrderListFragment) fragment;
                orderListFragment.refresh(ordersMap.get(titles.get(i)));
            }
        }
    }

    private Map<String, List<OrderBean>> getFakeData() {
        Map<String, List<OrderBean>> map = new HashMap<>();
        map.put(Constants.ORDER_TYPE_ALL, new ArrayList<OrderBean>());
        map.put(Constants.ORDER_TYPE_PAYMENT, new ArrayList<OrderBean>());
        map.put(Constants.ORDER_TYPE_CANCEL, new ArrayList<OrderBean>());
        map.put(Constants.ORDER_TYPE_COMPLETE, new ArrayList<OrderBean>());
        List<OrderBean> orders = new ArrayList<>();
        OrderBean orderBean1 = new OrderBean();
        orderBean1.setCreate_time("2018-10-28");
        orderBean1.setGoods_title("守望屁股");
        orderBean1.setPay(128);
        orderBean1.setType("待付款");
        orderBean1.setGoods_preview("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_86d58ae1.png");
        orders.add(orderBean1);

        OrderBean orderBean2 = new OrderBean();
        orderBean2.setCreate_time("2018-10-28");
        orderBean2.setGoods_title("小胡子养成计划");
        orderBean2.setType("已完成");
        orderBean2.setPay(541);
        orderBean2.setGoods_preview("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1378027402,433619994&fm=26&gp=0.jpg");
        orders.add(orderBean2);

        OrderBean orderBean3 = new OrderBean();
        orderBean3.setCreate_time("2018-10-11");
        orderBean3.setGoods_title("绿帽风云");
        orderBean3.setType("已完成");
        orderBean3.setPay(233);
        orderBean3.setGoods_preview("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1540745214791&di=a54f46552f975dcab0effefdb981a022&imgtype=0&src=http%3A%2F%2F7vihbu.com1.z0.glb.clouddn.com%2Fsonkwo-top-L-pdx-Crusader-Kings-II---Warriors-of-Faith-Unit-Pack-.jpg");
        orders.add(orderBean3);

        OrderBean orderBean4 = new OrderBean();
        orderBean4.setCreate_time("2015-10-13");
        orderBean4.setGoods_title("尼玛尼玛");
        orderBean4.setType("已取消");
        orderBean4.setPay(1);
        orderBean4.setGoods_preview("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1540745287526&di=d464bb789d208fd19a061bfa0d7415f7&imgtype=0&src=http%3A%2F%2Fpic2.zhimg.com%2F50%2Fv2-905bef905d9453a4cfdb63e28960b2b0_hd.jpg");
        orders.add(orderBean4);

        OrderBean orderBean5 = new OrderBean();
        orderBean5.setCreate_time("2016-10-13");
        orderBean5.setGoods_title("哈哈哈哈哈");
        orderBean5.setType("已取消");
        orderBean5.setPay(2);
        orderBean5.setGoods_preview("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1540745287526&di=d464bb789d208fd19a061bfa0d7415f7&imgtype=0&src=http%3A%2F%2Fpic2.zhimg.com%2F50%2Fv2-905bef905d9453a4cfdb63e28960b2b0_hd.jpg");
        orders.add(orderBean5);

        OrderBean orderBean6 = new OrderBean();
        orderBean6.setCreate_time("2017-10-13");
        orderBean6.setGoods_title("过于真实");
        orderBean6.setType("已取消");
        orderBean6.setPay(3);
        orderBean6.setGoods_preview("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1540745287526&di=d464bb789d208fd19a061bfa0d7415f7&imgtype=0&src=http%3A%2F%2Fpic2.zhimg.com%2F50%2Fv2-905bef905d9453a4cfdb63e28960b2b0_hd.jpg");
        orders.add(orderBean6);

        for (OrderBean order : orders) {
            Objects.requireNonNull(map.get("全部")).add(order);
            Objects.requireNonNull(map.get(order.getType())).add(order);
        }
        return map;
    }

}
