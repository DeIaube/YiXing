package arouter.dawn.zju.edu.module_mine.ui.feedback;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_mine.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 用户反馈页面
 */
@Route(path = Constants.AROUTER_SETTING_FEEDBACK)
public class FeedbackActivity extends BaseActivity<FeedbackContract.Presenter> implements View.OnClickListener , FeedbackContract.View{

    static final String TAG = "FeedbackActivity";

    EditText titleEt;
    EditText contentEt;

    @Override
    protected void initView() {
        titleEt = findViewById(R.id.findback_title);
        contentEt = findViewById(R.id.feedback_content);
        findViewById(R.id.feedback_submit).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new FeedbackPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void onBackPressed() {
        String title = titleEt.getText().toString();
        String content = contentEt.getText().toString();
        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(content)) {
            super.onBackPressed();
        } else {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.tips)
                    .setMessage(R.string.confirm_exit)
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.cancel, null)
                    .show();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.feedback_submit) {
            String title = titleEt.getText().toString();
            String content = contentEt.getText().toString();
            mPresenter.submitFeedback(title, content);
        }
    }
}
