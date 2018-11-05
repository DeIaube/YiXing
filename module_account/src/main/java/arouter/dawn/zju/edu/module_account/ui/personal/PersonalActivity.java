package arouter.dawn.zju.edu.module_account.ui.personal;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_account.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;
import baselib.util.LogUtil;

@Route(path = Constants.AROUTER_ACCOUNT_PERSONAL)
public class PersonalActivity extends BaseActivity<PersionContract.Presenter> implements View.OnClickListener,
        TakePhoto.TakeResultListener, InvokeListener, PersionContract.View {

    private static final String TAG = "PersonalActivity";

    TextView personalUsernameTv;
    TextView personalPhoneNumberTv;
    TextView personalPicknameTv;
    TextView personalBirthTv;
    ImageView personalPortraitIv;

    TakePhoto takePhoto;
    InvokeParam invokeParam;

    @Override
    protected void initView() {
        personalUsernameTv = findViewById(R.id.personal_username);
        personalPicknameTv = findViewById(R.id.personal_pickname);
        personalPhoneNumberTv = findViewById(R.id.personal_bind_phone_number);
        personalPortraitIv = findViewById(R.id.personal_portrait);
        personalBirthTv = findViewById(R.id.personal_birth);

        findViewById(R.id.check_portrait).setOnClickListener(this);
        findViewById(R.id.check_username).setOnClickListener(this);
        findViewById(R.id.check_pickname).setOnClickListener(this);
        findViewById(R.id.check_birth).setOnClickListener(this);
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        personalBirthTv.setText(user.getBirth() == null ? "1998-02-12" : sdf.format(user.getBirth()));
        refreshUserPortrait(user.getPortrait());
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
            checkPortrait();
        } else if (id == R.id.check_username) {
            // 点击用户名
            showMessage(getString(R.string.personal_username_static));
        } else if (id == R.id.check_pickname) {
            // 点击用户昵称
            ARouter.getInstance().build(Constants.AROUTER_ACCOUNT_MODIFY_PICKNAME).navigation();
        } else if (id == R.id.check_birth) {
            // 点击用户生日
            showPickBirthDialog();
        }
    }

    /**
     * 展示日期对话框选择并且更新用户生日
     */
    private void showPickBirthDialog() {
        new DatePickerDialog(this,
                // 绑定监听器(How the parent is notified that the date is set.)
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        mPresenter.updateUserBirth(year, monthOfYear - 1, dayOfMonth);
                    }
                }
                // 设置初始日期
                , 2018
                ,10
                ,5).show();
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
        mPresenter.updateUserPortrait(result.getImage().getCompressPath());
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
    public void refreshUserPortrait(String url) {
        if (url != null) {
            Picasso.with(this).load(url).into(personalPortraitIv);
        }
    }

    @Override
    public void refreshUserBirth(Date date) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        personalBirthTv.setText(sdf.format(date));
    }
}
