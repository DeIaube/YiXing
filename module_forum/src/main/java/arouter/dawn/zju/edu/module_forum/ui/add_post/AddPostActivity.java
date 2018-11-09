package arouter.dawn.zju.edu.module_forum.ui.add_post;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;

import arouter.dawn.zju.edu.module_forum.R;
import arouter.dawn.zju.edu.module_forum.adapter.ForumAddPostSelectImageAdapter;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_FORUM_ADD_POST)
public class AddPostActivity extends BaseActivity<AddPostContract.Presenter> implements AddPostContract.View{

    EditText postTitleEt;
    EditText postContentEt;
    RecyclerView selectImageListRv;

    private ForumAddPostSelectImageAdapter mAdapter;

    @Override
    protected void initView() {
        postTitleEt = findViewById(R.id.forum_add_post_title);
        postContentEt = findViewById(R.id.forum_add_post_content);
        selectImageListRv = findViewById(R.id.forum_add_post_select_image_list);

        selectImageListRv.setLayoutManager(new GridLayoutManager(this, 4));
        mAdapter = new ForumAddPostSelectImageAdapter(new ArrayList<String>(), this);
        selectImageListRv.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_post;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new AddPostPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_post_menu_post) {
            // todo 发布文章
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_post_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
