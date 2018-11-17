package arouter.dawn.zju.edu.module_goods.ui.goods_detail;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.Goods;
import arouter.dawn.zju.edu.module_goods.util.PicassoUrlImageLeader;
import arouter.dawn.zju.edu.module_nearby.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_GOODS_DETAIL)
public class GoodsDetailActivity extends BaseActivity<GoodsDetailContract.Presenter> implements GoodsDetailContract.View {

    @Autowired(name = Constants.GOODS_DETAIL_BUNDLE)
    Bundle bundle;
    Goods goods;

    Banner goodsDetailBanner;

    @Override
    protected void initView() {
        goodsDetailBanner = findViewById(R.id.goods_detail_banner);
        goods = bundle.getParcelable(Constants.GOODS_DETAIL_GOODS);
        setToolbarTitle(goods.getTitle());

        List<String> imageList = new ArrayList<>();
        imageList.add("http://lc-otc2yuns.cn-n1.lcfile.com/loVl6uctWbMc2uYj4aiZk47ad0yvovvgnfv5SSmv.jpg");
        imageList.add("http://lc-otc2yuns.cn-n1.lcfile.com/loVl6uctWbMc2uYj4aiZk47ad0yvovvgnfv5SSmv.jpg");
        imageList.add("http://lc-otc2yuns.cn-n1.lcfile.com/loVl6uctWbMc2uYj4aiZk47ad0yvovvgnfv5SSmv.jpg");
        imageList.add("http://lc-otc2yuns.cn-n1.lcfile.com/loVl6uctWbMc2uYj4aiZk47ad0yvovvgnfv5SSmv.jpg");
        imageList.add("http://lc-otc2yuns.cn-n1.lcfile.com/loVl6uctWbMc2uYj4aiZk47ad0yvovvgnfv5SSmv.jpg");

        goodsDetailBanner.setImageLoader(new PicassoUrlImageLeader());
        goodsDetailBanner.setImages(imageList);
        goodsDetailBanner.start();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new GoodsDetailPresenter();
    }

}
