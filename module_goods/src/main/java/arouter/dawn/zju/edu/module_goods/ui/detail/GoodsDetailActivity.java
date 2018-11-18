package arouter.dawn.zju.edu.module_goods.ui.detail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.youth.banner.Banner;

import java.text.SimpleDateFormat;

import arouter.dawn.zju.edu.lib_net.bean.goods.Goods;
import arouter.dawn.zju.edu.module_goods.util.PicassoUrlImageLeader;
import arouter.dawn.zju.edu.module_nearby.R;
import arouter.dawn.zju.edu.module_pay.AliPay;
import arouter.dawn.zju.edu.module_pay.PayCallback;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_GOODS_DETAIL)
public class GoodsDetailActivity extends BaseActivity<GoodsDetailContract.Presenter> implements GoodsDetailContract.View, View.OnClickListener {

    @Autowired(name = Constants.GOODS_DETAIL_BUNDLE)
    Bundle bundle;
    Goods goods;

    TextView goodsTitleTv;
    TextView goodsBuyCounterTv;
    TextView goodsLocationTv;
    TextView goodsExplainTv;
    TextView goodsStartTimeTv;
    TextView goodsEndTimeTv;
    TextView goodsPriceTv;
    Button goodsPayBtn;

    Banner goodsDetailBanner;

    private String mCollectionMenuContent;

    @SuppressLint("DefaultLocale")
    @Override
    protected void initView() {
        goods = bundle.getParcelable(Constants.GOODS_DETAIL_GOODS);
        setToolbarTitle(goods.getTitle());

        goodsDetailBanner = findViewById(R.id.goods_detail_banner);
        goodsTitleTv = findViewById(R.id.goods_detail_title);
        goodsBuyCounterTv = findViewById(R.id.goods_detail_buy_counter);
        goodsLocationTv = findViewById(R.id.goods_detail_loacation);
        goodsExplainTv = findViewById(R.id.goods_detail_explain);
        goodsStartTimeTv = findViewById(R.id.goods_detail_start_time);
        goodsEndTimeTv = findViewById(R.id.goods_detail_end_time);
        goodsPriceTv = findViewById(R.id.goods_detail_price);
        goodsPayBtn = findViewById(R.id.goods_detail_pay);

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        goodsTitleTv.setText(goods.getTitle());
        goodsExplainTv.setText(goods.getExplain());
        goodsLocationTv.setText(goods.getLocation());
        goodsPriceTv.setText(String.format("%.2f", goods.getPrice()));
        goodsStartTimeTv.setText(sdf.format(goods.getStartTime()));
        goodsEndTimeTv.setText(sdf.format(goods.getEndTime()));

        goodsPayBtn.setOnClickListener(this);


        goodsDetailBanner.setImageLoader(new PicassoUrlImageLeader());
        goodsDetailBanner.setImages(goods.getPreviewList());
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

    @Override
    public void refreshBuyCounterTextView(String text) {
        goodsBuyCounterTv.setVisibility(View.VISIBLE);
        goodsBuyCounterTv.setText(text);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.goods_detail_pay) {
            new AliPay.Builed(this)
                    .setPrice(goods.getPrice())
                    .setTitle(goods.getTitle())
                    .setContent(goods.getExplain())
                    .setPayCallback(new PayCallback() {
                        @Override
                        public void paySuccess() {
                            mPresenter.paySuccess(goods);
                        }

                        @Override
                        public void payFailed() {
                            mPresenter.payFailed(goods);
                        }
                    })
                    .buile()
                    .pay(v);
        }
    }
}
