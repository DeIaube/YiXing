package arouter.dawn.zju.edu.module_order.ui.evaluate;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.squareup.picasso.Picasso;

import arouter.dawn.zju.edu.module_order.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_ORDER_EVALUATE)
public class EvaluateActivity extends BaseActivity implements View.OnClickListener {

    ImageView frownIv;
    ImageView mehIv;
    ImageView smileIv;
    TextView smileTv;
    TextView mehTv;
    TextView frownTv;
    ImageView previewIv;
    TextView titleTv;

    // Constants.ORDER_GOODS_TITLE
    @Autowired
    String orderGoodsTitle;
    // Constans.ORDER_GOODS_PREVIEW
    @Autowired
    String orderGoodsPreview;
    // Constans.ORDER_GOODS_ID
    @Autowired
    String orderGoodsId;

    @Override
    protected void initView() {
        frownIv = findViewById(R.id.frown_image);
        mehIv = findViewById(R.id.meh_image);
        smileIv = findViewById(R.id.smile_image);
        smileTv = findViewById(R.id.smile_text);
        mehTv = findViewById(R.id.meh_text);
        frownTv = findViewById(R.id.frown_text);
        previewIv = findViewById(R.id.goods_preview);
        titleTv = findViewById(R.id.goods_title);

        Picasso.with(this).load(orderGoodsPreview).into(previewIv);
        titleTv.setText(orderGoodsTitle);

        frownIv.setOnClickListener(this);
        mehIv.setOnClickListener(this);
        smileIv.setOnClickListener(this);
        smileTv.setOnClickListener(this);
        mehTv.setOnClickListener(this);
        frownTv.setOnClickListener(this);

        resetEvaluate();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_evaluate;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.smile_text || id == R.id.smile_image) {
            resetEvaluate();
            smileTv.setTextColor(getResources().getColor(R.color.colorPrimary));
            Picasso.with(this).load(R.drawable.select_smile).into(smileIv);
        } else if (id == R.id.meh_text || id == R.id.meh_image) {
            resetEvaluate();
            mehTv.setTextColor(getResources().getColor(R.color.colorPrimary));
            Picasso.with(this).load(R.drawable.select_meh).into(mehIv);
        } else if (id == R.id.frown_text || id == R.id.frown_image) {
            resetEvaluate();
            frownTv.setTextColor(getResources().getColor(R.color.colorPrimary));
            Picasso.with(this).load(R.drawable.select_frown).into(frownIv);
        }
    }

    private void resetEvaluate() {
        smileTv.setTextColor(getResources().getColor(R.color.grey));
        Picasso.with(this).load(R.drawable.default_smile).into(smileIv);
        mehTv.setTextColor(getResources().getColor(R.color.grey));
        Picasso.with(this).load(R.drawable.default_meh).into(mehIv);
        frownTv.setTextColor(getResources().getColor(R.color.grey));
        Picasso.with(this).load(R.drawable.default_frown).into(frownIv);
    }
}
