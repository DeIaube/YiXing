package arouter.dawn.zju.edu.module_forum.ui.post;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.lib_net.bean.ForumPost;
import arouter.dawn.zju.edu.module_forum.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_FORUM_FORUM_POST)
public class ForumPostActivity extends BaseActivity<ForumPostContract.Presenter> implements ForumPostContract.View {

    @Autowired(name = Constants.FORUM_POST_POST)
    ForumPost post;

    @Override
    protected void initView() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forum_post;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.post_menu, menu);
        MenuItem collectionItem = menu.findItem(R.id.post_menu_collection);
        collectionItem.setTitle(R.string.forum_post_collection);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.post_menu_collection) {
            
        } else if (id == R.id.post_menu_share) {

        } else if (id == R.id.post_menu_report) {

        }
        return super.onOptionsItemSelected(item);
    }
}
