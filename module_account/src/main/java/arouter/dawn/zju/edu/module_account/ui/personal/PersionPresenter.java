package arouter.dawn.zju.edu.module_account.ui.personal;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.SaveCallback;

import java.io.FileNotFoundException;

import arouter.dawn.zju.edu.lib_net.bean.User;
import baselib.base.BasePresenter;
import baselib.util.LogUtil;

public class PersionPresenter extends BasePresenter<PersionContract.View> implements PersionContract.Presenter {

    private static final String TAG = "PersionPresenter";

    @Override
    public void updateUserPortrait(String path) {
        try {
            final AVFile avFile =
                    AVFile.withAbsoluteLocalPath(String.format("%s_portrait.png",User.getCurrentUser().getUsername()), path);
            avFile.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null) {
                        LogUtil.i(TAG, "AVFile Save");
                        LogUtil.i(TAG, avFile.getUrl());
                    } else {
                        LogUtil.e(TAG, e.toString());
                    }
                }
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
