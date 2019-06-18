package arouter.dawn.zju.edu.module_account.ui.personal;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;
import java.util.Calendar;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_account.R;
import arouter.dawn.zju.edu.module_account.databinding.ActivityPersonalBinding;
import baselib.base2.BaseActivity;
import baselib.constants.RouteConstants;
import baselib.util.LogUtil;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 展示用户个人信息页面
 */
@Route(path = RouteConstants.AROUTER_ACCOUNT_PERSONAL)
public class PersonalActivity extends BaseActivity<ActivityPersonalBinding, PersonalViewMoedl> implements
        TakePhoto.TakeResultListener, InvokeListener {

    private static final String TAG = "PersonalActivity";

    TakePhoto takePhoto;
    InvokeParam invokeParam;

    @Override
    protected void init() {
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.checkPortrait.setOnClickListener(v -> checkPortrait());
        binding.checkUsername.setOnClickListener(v -> makeToast(getString(R.string.personal_username_static)));
        binding.checkPickname.setOnClickListener(v -> ARouter.getInstance().build(RouteConstants.AROUTER_ACCOUNT_MODIFY_PICKNAME).navigation());
        binding.checkBirth.setOnClickListener(v -> showPickBirthDialog());
        viewModel.updateUserData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    /**
     * 展示日期对话框选择并且更新用户生日
     */
    private void showPickBirthDialog() {
        User user = User.getCurrentUser(User.class);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(user.getBirth());
        new DatePickerDialog(this,
                (view, year, monthOfYear, dayOfMonth) -> viewModel.updateUserBirth(year, monthOfYear - 1, dayOfMonth)
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                ,calendar.get(Calendar.MONTH) - 1
                ,calendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    private void checkPortrait() {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        final Uri imageUri = Uri.fromFile(file);
        final CropOptions cropOptions=new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(true).create();
        new AlertDialog.Builder(this)
                .setTitle(R.string.personal_upload_portrait)
                .setItems(new String[]{getString(R.string.personal_photograph), getString(R.string.personal_select_by_album)}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            getTakePhoto().onPickFromCaptureWithCrop(imageUri, cropOptions);
                        } else {
                            getTakePhoto().onPickFromGalleryWithCrop(imageUri, cropOptions);
                        }
                    }
                })
                .show();
    }

    public TakePhoto getTakePhoto(){
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this)
                    .bind(new TakePhotoImpl(this,this));
            // 图片压缩
            CompressConfig config=new CompressConfig.Builder()
                    .setMaxSize(340 * 340)
                    .setMaxPixel(340)
                    .create();
            takePhoto.onEnableCompress(config, false);
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
        viewModel.updateUserPortrait(result.getImage().getCompressPath());
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
