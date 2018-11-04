package arouter.dawn.zju.edu.module_account.ui.modify_pickname;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SaveCallback;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_account.R;
import baselib.App;
import baselib.base.BasePresenter;
import baselib.util.LogUtil;

public class ModifyPicknamePresenter extends BasePresenter<ModifyPicknameContract.View> implements ModifyPicknameContract.Presenter {

    private static final String TAG = "ModifyPicknamePresenter";

    @Override
    public void modifyPickname(String pickname) {
        User user =User.getCurrentUser(User.class);
        user.setPickName(pickname);
        mView.showLoading();
        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                mView.hideLoading();
                if (e == null) {
                    LogUtil.i(TAG, "modifyPickname");
                    mView.showMessage(App.getContext().getString(R.string.modify_success));
                    mView.finish();
                } else {
                    LogUtil.e(TAG, e.getLocalizedMessage());
                    mView.showMessage(e.getLocalizedMessage());
                }
            }
        });
    }

}
