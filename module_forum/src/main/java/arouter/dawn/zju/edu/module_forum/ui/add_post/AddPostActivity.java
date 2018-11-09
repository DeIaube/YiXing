package arouter.dawn.zju.edu.module_forum.ui.add_post;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.module_forum.R;
import arouter.dawn.zju.edu.module_forum.adapter.ForumAddPostSelectImageAdapter;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_FORUM_ADD_POST)
public class AddPostActivity extends BaseActivity<AddPostContract.Presenter> implements AddPostContract.View, ForumAddPostSelectImageAdapter.SelectImageLisener {

    EditText postTitleEt;
    EditText postContentEt;
    RecyclerView selectImageListRv;

    private ForumAddPostSelectImageAdapter mAdapter;
    private List<String> images;

    @Override
    protected void initView() {
        postTitleEt = findViewById(R.id.forum_add_post_title);
        postContentEt = findViewById(R.id.forum_add_post_content);
        selectImageListRv = findViewById(R.id.forum_add_post_select_image_list);

        images = new ArrayList<>();
        selectImageListRv.setLayoutManager(new GridLayoutManager(this, 4));
        mAdapter = new ForumAddPostSelectImageAdapter(images, this);
        selectImageListRv.setAdapter(mAdapter);

        mAdapter.setSelectImageLisener(this);
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

    /**
     * 展示图片大图
     * @param url 图片资源定位符
     */
    @Override
    public void showImage(String url) {

    }

    /**
     * 跳转相册选择照片
     */
    @Override
    public void addImage() {

    }

    /**
     * 删除此图片
     * @param position 图片下标
     */
    @Override
    public void deleteImage(int position) {
        images.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
}
