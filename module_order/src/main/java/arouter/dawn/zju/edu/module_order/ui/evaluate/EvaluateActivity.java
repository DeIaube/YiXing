package arouter.dawn.zju.edu.module_order.ui.evaluate;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

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

    @Override
    protected void initView() {
        frownIv = findViewById(R.id.frown_image);
        mehIv = findViewById(R.id.meh_image);
        smileIv = findViewById(R.id.smile_image);
        smileTv = findViewById(R.id.smile_text);
        mehTv = findViewById(R.id.meh_text);
        frownTv = findViewById(R.id.frown_text);

        frownIv.setOnClickListener(this);
        mehIv.setOnClickListener(this);
        smileIv.setOnClickListener(this);
        smileTv.setOnClickListener(this);
        mehTv.setOnClickListener(this);
        frownTv.setOnClickListener(this);
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

        } else if (id == R.id.meh_text || id == R.id.meh_image) {

        } else if (id == R.id.frown_text || id == R.id.frown_image) {

        }
    }
}
