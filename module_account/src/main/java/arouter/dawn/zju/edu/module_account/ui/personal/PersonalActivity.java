package arouter.dawn.zju.edu.module_account.ui.personal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_account.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;
import baselib.util.LogUtil;

@Route(path = Constants.AROUTER_ACCOUNT_PERSONAL)
public class PersonalActivity extends BaseActivity<PersionContract.Presenter> implements View.OnClickListener,
        TakePhoto.TakeResultListener, InvokeListener {

    private static final String TAG = "PersonalActivity";

    TextView personalUsernameTv;
    TextView personalPhoneNumberTv;
    TextView personalPicknameTv;
    ImageView personalPortraitIv;

    TakePhoto takePhoto;
    InvokeParam invokeParam;

    @Override
    protected void initView() {
        personalUsernameTv = findViewById(R.id.personal_username);
        personalPicknameTv = findViewById(R.id.personal_pickname);
        personalPhoneNumberTv = findViewById(R.id.personal_bind_phone_number);
        personalPortraitIv = findViewById(R.id.personal_portrait);

        findViewById(R.id.check_portrait).setOnClickListener(this);
        findViewById(R.id.check_username).setOnClickListener(this);
        findViewById(R.id.check_pickname).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshLayout();
    }

    private void refreshLayout() {
        User user = User.getCurrentUser(User.class);
        personalUsernameTv.setText(user.getUsername());
        personalPicknameTv.setText(user.getPickName());
        personalPhoneNumberTv.setText(user.getMobilePhoneNumber());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new PersionPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.check_portrait) {
            // 选取头像 跳转选择相册并且裁剪图片
            File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            Uri imageUri = Uri.fromFile(file);
            CropOptions cropOptions=new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(true).create();
            getTakePhoto().onPickFromGalleryWithCrop(imageUri, cropOptions);
        } else if (id == R.id.check_username) {
            // 点击用户名
        } else if (id == R.id.check_pickname) {
            // 点击用户昵称
            ARouter.getInstance().build(Constants.AROUTER_ACCOUNT_MODIFY_PICKNAME).navigation();
        }
    }


    public TakePhoto getTakePhoto(){
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this,this));
        }
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
        mPresenter.updateUserPortrait(result.getImage().getOriginalPath());
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
}
