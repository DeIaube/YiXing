package arouter.dawn.zju.edu.module_mine.ui.notice;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_db.entity.NoticeEntity;
import arouter.dawn.zju.edu.module_mine.R;
import arouter.dawn.zju.edu.module_mine.adapter.NoticeListAdapter;
import baselib.base.BaseActivity;
import baselib.constants.RouteConstants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 通知页面
 */
@Route(path = RouteConstants.AROUTER_SETTING_NOTICE)
public class NoticeActivity extends BaseActivity<NoticeContract.Presenter> implements NoticeContract.View{

    RecyclerView noticeList;
    private NoticeListAdapter mAdapter;

    @Override
    protected void initView() {
        noticeList = findViewById(R.id.notice_list);
        mAdapter = new NoticeListAdapter(this, new ArrayList<NoticeEntity>());
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
    public void refreshNoticeList(List<NoticeEntity> noticeList) {
        mAdapter.refresh(noticeList);
    }
}
