package arouter.dawn.zju.edu.module_mine.ui.notice;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_db.bean.Notice;
import arouter.dawn.zju.edu.module_mine.R;
import arouter.dawn.zju.edu.module_mine.adapter.NoticeListAdapter;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_SETTING_NOTICE)
public class NoticeActivity extends BaseActivity<NoticeContract.Presenter> implements NoticeContract.View{

    RecyclerView noticeList;
    private NoticeListAdapter mAdapter;

    @Override
    protected void initView() {
        noticeList = findViewById(R.id.notice_list);
        mAdapter = new NoticeListAdapter(this, new ArrayList<Notice>());
        noticeList.setLayoutManager(new LinearLayoutManager(this));
        noticeList.setAdapter(mAdapter);

        mPresenter.refreshNoticeList();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notice;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new NoticePresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void refreshNoticeList(List<Notice> noticeList) {
        mAdapter.refresh(noticeList);
    }
}
