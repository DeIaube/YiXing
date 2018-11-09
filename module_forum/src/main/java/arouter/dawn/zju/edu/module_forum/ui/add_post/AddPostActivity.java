package arouter.dawn.zju.edu.module_forum.ui.add_post;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.module_forum.R;
import arouter.dawn.zju.edu.module_forum.adapter.ForumAddPostSelectImageAdapter;
import baselib.base.BaseActivity;
import baselib.config.Constants;
import baselib.util.LogUtil;

@Route(path = Constants.AROUTER_FORUM_ADD_POST)
public class AddPostActivity extends BaseActivity<AddPostContract.Presenter> implements AddPostContract.View,
        ForumAddPostSelectImageAdapter.SelectImageLisener, TakePhoto.TakeResultListener, InvokeListener {

    static final String TAG = "AddPostActivity";

    EditText postTitleEt;
    EditText postContentEt;
    RecyclerView selectImageListRv;

    InvokeParam invokeParam;

    private ForumAddPostSelectImageAdapter mAdapter;
    private List<String> mImages;

    @Override
    protected void initView() {
        postTitleEt = findViewById(R.id.forum_add_post_title);
        postContentEt = findViewById(R.id.forum_add_post_content);
        selectImageListRv = findViewById(R.id.forum_add_post_select_image_list);

        mImages = new ArrayList<>();
        selectImageListRv.setLayoutManager(new GridLayoutManager(this, 4));
        mAdapter = new ForumAddPostSelectImageAdapter(mImages, this);
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
            mPresenter.submit(postTitleEt.getText().toString(), postContentEt.getText().toString(),
                    mImages);
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkPortrait() {
        getTakePhoto().onPickMultiple(999);
    }

    public TakePhoto getTakePhoto(){
        TakePhoto takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this)
                    .bind(new TakePhotoImpl(this,this));
            // 图片压缩
        CompressConfig config=new CompressConfig.Builder()
                .setMaxSize(340 * 340)
                .setMaxPixel(340)
                .create();
        takePhoto.onEnableCompress(config, false);
        return takePhoto;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getTakePhoto().onSaveInstanceState(outState);
    }

    @Override
    public void takeSuccess(TResult result) {
        LogUtil.i(TAG, "takeSuccess");
        mImages.clear();
        for (TImage tImage : result.getImages()) {
            mImages.add(tImage.getCompressPath());
        }
        mAdapter.update(mImages);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        LogUtil.i(TAG, msg);
    }

    @Override
    public void takeCancel() {
        LogUtil.i(TAG, "takeCancel");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTakePhoto().onCreate(savedInstanceState);
    }

    /**
     * 引入takephoto处理权限问题
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(this,type,invokeParam, this);
    }

    /**
     * 引入takephoto处理权限问题
     */
    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
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
        checkPortrait();
    }

    /**
     * 删除此图片
     * @param position 图片下标
     */
    @Override
    public void deleteImage(int position) {
        if (position < mImages.size()) {
            mImages.remove(position);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void submitSuccess() {
        showMessage(getString(R.string.forum_add_post_share_success));
        onBackPressed();
    }
}
