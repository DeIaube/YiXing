package arouter.dawn.zju.edu.module_account.ui.personal;

import android.annotation.SuppressLint;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.SaveCallback;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import arouter.dawn.zju.edu.lib_net.bean.User;
import baselib.base.BasePresenter;
import baselib.util.LogUtil;

public class PersionPresenter extends BasePresenter<PersionContract.View> implements PersionContract.Presenter {

    private static final String TAG = "PersionPresenter";

    /**
     * 上传文件至云端服务器
     * 获取url并且与用户绑定
     * @param path 本地路径
     */
    @Override
    public void updateUserPortrait(String path) {
        mView.showLoading();
        try {
            final AVFile avFile =
                    AVFile.withAbsoluteLocalPath(String.format("%s_portrait.png",User.getCurrentUser().getUsername()), path);
            avFile.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null) {
                        LogUtil.i(TAG, "AVFile Save");
                        LogUtil.i(TAG, avFile.getUrl());
                        updatePortrait(avFile.getUrl());
                    } else {
                        LogUtil.e(TAG, e.toString());
                        mView.hideLoading();
                    }
                }
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            mView.hideLoading();
        }
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void updateUserBirth(int year, int month, int day) {
        Log.e(TAG, String.format("%04d-%02d-%02d", year, month, day));
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(String.format("%04d-%02d-%02d", year, month, day));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null) {
            LogUtil.e(TAG, "updateUserBirth: data == null");
            return;
        }
        User user = User.getCurrentUser(User.class);
        user.setBirth(date);
        mView.showLoading();
        final Date finalDate = date;
        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                mView.hideLoading();
                if (e == null) {
                    mView.refreshUserBirth(finalDate);
                    LogUtil.i(TAG, "updateUserBirth");
                } else {
                    LogUtil.e(TAG, e.toString());
                    mView.showMessage(e.toString());
                }
            }
        });
    }

    /**
     * 将url保存到云端
     * @param url
     */
    private void updatePortrait(final String url) {
        User user = User.getCurrentUser(User.class);
        user.setPortrait(url);
        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                mView.hideLoading();
                if (e == null) {
                    LogUtil.i(TAG, "userSave");
                    mView.refreshUserPortrait(url);
                } else {
                    LogUtil.e(TAG, e.getLocalizedMessage());
                }
            }
        });
    }

}
