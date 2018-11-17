package arouter.dawn.zju.edu.module_order.ui.evaluate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.squareup.picasso.Picasso;

import arouter.dawn.zju.edu.lib_net.bean.Goods;
import arouter.dawn.zju.edu.lib_net.bean.Order;
import arouter.dawn.zju.edu.module_order.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_ORDER_EVALUATE)
public class EvaluateActivity extends BaseActivity<EvaluateContract.Presenter> implements
        View.OnClickListener, EvaluateContract.View {

    ImageView frownIv;
    ImageView mehIv;
    ImageView smileIv;
    TextView smileTv;
    TextView mehTv;
    TextView frownTv;
    ImageView previewIv;
    TextView titleTv;
    EditText contentEt;
    Button submitBtn;

    @Autowired(name = Constants.ORDER_ORDER_LIST_BOUNDLE)
    Bundle bundle;

    Order order;

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
        contentEt = findViewById(R.id.order_evaluate_content);
        submitBtn = findViewById(R.id.order_evaluate_submit);

        order = bundle.getParcelable(Constants.ORDER_ORDER_LIST_ORDER);

        Picasso.with(this).load(order.getGoods().getPreview()).into(previewIv);
        titleTv.setText(order.getGoods().getTitle());

        frownIv.setOnClickListener(this);
        mehIv.setOnClickListener(this);
        smileIv.setOnClickListener(this);
        smileTv.setOnClickListener(this);
        mehTv.setOnClickListener(this);
        frownTv.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        resetEvaluate();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_evaluate;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new EvaluatePresenter();
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
        } else if (id == R.id.order_evaluate_submit) {

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
