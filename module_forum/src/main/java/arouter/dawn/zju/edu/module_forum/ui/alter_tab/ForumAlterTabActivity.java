package arouter.dawn.zju.edu.module_forum.ui.alter_tab;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_forum.R;
import arouter.dawn.zju.edu.module_forum.adapter.ForumAlterTabAdapter;
import baselib.base.BaseActivity;
import baselib.constants.RouteConstants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 更改首页Tab页面
 */
@Route(path = RouteConstants.AROUTER_FORUM_ALTER_TAB)
public class ForumAlterTabActivity extends BaseActivity<ForumAlterTabContract.Presenter> implements ForumAlterTabContract.View, ForumAlterTabAdapter.TabStatusListener {

    RecyclerView formAltertabListView;

    @Override
    protected void initView() {
        formAltertabListView = findViewById(R.id.forum_alter_tab_list_view);

        formAltertabListView.setLayoutManager(new LinearLayoutManager(this));
        ForumAlterTabAdapter adapter = new ForumAlterTabAdapter(this);
        formAltertabListView.setAdapter(adapter);
        adapter.setTabStatusListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forum_alter_tab;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new ForumAlterTabPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void statusChange() {
        setResult(RESULT_OK);
    }
}
