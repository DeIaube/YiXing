package arouter.dawn.zju.edu.module_account.ui.personal;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.SaveCallback;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import arouter.dawn.zju.edu.lib_net.bean.User;
import baselib.base2.BaseViewModel;
import baselib.util.LogUtil;

public class PersonalViewMoedl extends BaseViewModel<PersonalActivity, PersonalRepository> {

    public MutableLiveData<String> portraitData = new MutableLiveData<>();
    public MutableLiveData<Date> birthData = new MutableLiveData<>();
    public MutableLiveData<String> usernameData = new MutableLiveData<>();
    public MutableLiveData<String> picknameData = new MutableLiveData<>();
    public MutableLiveData<String> phoneNumberData = new MutableLiveData<>();

    private static final String TAG = "PersonalViewMoedl";

    public PersonalViewMoedl(@NonNull Application application, PersonalActivity view, PersonalRepository repository) {
        super(application, view, repository);
    }

    public void updateUserData() {
        User user = User.getCurrentUser(User.class);
        portraitData.setValue(user.getPortrait());
        birthData.setValue(user.getBirth());
        usernameData.setValue(user.getUsername());
        picknameData.setValue(user.getPickName());
        phoneNumberData.setValue(user.getMobilePhoneNumber());
    }


    /**
     * 上传文件至云端服务器
     * 获取url并且与用户绑定
     * @param path 本地路径
     */
    public void updateUserPortrait(String path) {
        view.showLoading("");
        try {
            final AVFile avFile =
                    AVFile.withAbsoluteLocalPath(String.format("%s_portrait.png", User.getCurrentUser().getUsername()), path);
            avFile.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null) {
                        LogUtil.i(TAG, "AVFile Save");
                        LogUtil.i(TAG, avFile.getUrl());
                        updatePortrait(avFile.getUrl());
                    } else {
                        LogUtil.e(TAG, e.toString());
                        view.hideLoading();
                    }
                }
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            view.hideLoading();
        }
    }

    @SuppressLint("DefaultLocale")
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
        view.showLoading("");
        final Date finalDate = date;
        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                view.hideLoading();
                if (e == null) {
                    LogUtil.i(TAG, "updateUserBirth");
                    birthData.setValue(finalDate);
                } else {
                    LogUtil.e(TAG, e.toString());
                    view.makeToast(e.toString());
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
                view.hideLoading();
                if (e == null) {
                    LogUtil.i(TAG, "userSave");
                    portraitData.setValue(url);
                } else {
                    LogUtil.e(TAG, e.getLocalizedMessage());
                }
            }
        });
    }
}
