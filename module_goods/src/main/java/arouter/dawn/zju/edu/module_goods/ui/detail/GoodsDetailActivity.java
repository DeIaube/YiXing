package arouter.dawn.zju.edu.module_goods.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

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

    private String mCollectionMenuContent;

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

        mCollectionMenuContent = getString(R.string.goods_detail_collection);

        mPresenter.init(goods);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem collectionItem = menu.findItem(R.id.detail_menu_collection);
        collectionItem.setTitle(mCollectionMenuContent);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.detail_menu_collection) {
            // 收藏商品
            mPresenter.collection(goods);
        } else if (id == R.id.detail_menu_share) {
            // 分享文章
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, goods.getTitle());
            shareIntent.setType("text/plain");
            startActivity(shareIntent.createChooser(shareIntent, goods.getTitle()));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showGoodsAlreadyCollection() {
        mCollectionMenuContent = getString(R.string.goods_detail_cancel_collection);
        getWindow().invalidatePanelMenu(Window.FEATURE_OPTIONS_PANEL);
    }

    @Override
    public void showGoodsUnCollection() {
        mCollectionMenuContent = getString(R.string.goods_detail_collection);
        getWindow().invalidatePanelMenu(Window.FEATURE_OPTIONS_PANEL);
    }
}
