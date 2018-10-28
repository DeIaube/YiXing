package arouter.dawn.zju.edu.module_order.ui.order;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import arouter.dawn.zju.edu.module_order.adapter.OrderPagerAdapter;
import arouter.dawn.zju.edu.module_order.ui.order_list.OrderListFragment;
import baselib.base.BasePresenter;
import baselib.bean.OrderBean;

public class OrderPresenter extends BasePresenter<OrderContract.View> implements OrderContract.Presenter, ViewPager.OnPageChangeListener {

    private int mCurrentIndex;

    @Override
    public void bindViewPager(FragmentManager fragmentManager, ViewPager viewPager, TabLayout tabLayout) {
        List<String> titles = new ArrayList<>();
        titles.add("全部");
        titles.add("待付款");
        titles.add("已取消");
        titles.add("已完成");
        List<Fragment> fragments = new ArrayList<>();
        Map<String, List<OrderBean>> fakeData = getFakeData();
        fragments.add(new OrderListFragment(fakeData.get(titles.get(0))));
        fragments.add(new OrderListFragment(fakeData.get(titles.get(1))));
        fragments.add(new OrderListFragment(fakeData.get(titles.get(2))));
        fragments.add(new OrderListFragment(fakeData.get(titles.get(3))));

        OrderPagerAdapter adapter = new OrderPagerAdapter(fragmentManager, titles, fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
        tabLayout.setupWithViewPager(viewPager);
    }

    private Map<String, List<OrderBean>> getFakeData() {
        Map<String, List<OrderBean>> map = new HashMap<>();
        map.put("全部", new ArrayList<OrderBean>());
        map.put("待付款", new ArrayList<OrderBean>());
        map.put("已取消", new ArrayList<OrderBean>());
        map.put("已完成", new ArrayList<OrderBean>());
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

        for (OrderBean order : orders) {
            Objects.requireNonNull(map.get("全部")).add(order);
            Objects.requireNonNull(map.get(order.getType())).add(order);
        }
        return map;
    }

    @Override
    public void refresh() {
        mView.showSwipeRefreshLayout();
        // todo 刷新订单情况
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        mCurrentIndex = i;
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
