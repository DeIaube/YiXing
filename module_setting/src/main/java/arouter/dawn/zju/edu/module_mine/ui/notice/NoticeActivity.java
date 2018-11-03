package arouter.dawn.zju.edu.module_mine.ui.notice;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_mine.R;
import arouter.dawn.zju.edu.module_mine.adapter.NoticeListAdapter;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_SETTING_NOTICE)
public class NoticeActivity extends BaseActivity {

    RecyclerView noticeList;
    private NoticeListAdapter mAdapter;

    @Override
    protected void initView() {
        noticeList = findViewById(R.id.notice_list);

        mAdapter = new NoticeListAdapter(this);
        noticeList.setLayoutManager(new LinearLayoutManager(this));
        noticeList.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notice;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
