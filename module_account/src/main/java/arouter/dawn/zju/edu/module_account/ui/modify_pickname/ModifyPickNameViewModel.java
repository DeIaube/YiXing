package arouter.dawn.zju.edu.module_account.ui.modify_pickname;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SaveCallback;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_account.R;
import baselib.base2.BaseViewModel;
import baselib.bus.EventBus;
import baselib.constants.BusConstants;
import baselib.util.LogUtil;

public class ModifyPickNameViewModel extends BaseViewModel<ModifyPicknameActivity, ModifyPickNameRepository> {

    private static final String TAG = "ModifyPickNameViewModel";

    public MutableLiveData<String> picknameData = new MutableLiveData<>();

    public ModifyPickNameViewModel(@NonNull Application application, ModifyPicknameActivity view, ModifyPickNameRepository repository) {
        super(application, view, repository);
    }

    public void modifyPickname() {
        User user =User.getCurrentUser(User.class);
        user.setPickName(picknameData.getValue());
        view.showLoading("");
        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                view.hideLoading();
                if (e == null) {
                    LogUtil.i(TAG, "modifyPickname");
                    view.makeToast(getApplication().getString(R.string.modify_success));
                    view.finish();
                    EventBus.get().with(BusConstants.MODIFY_PICK_NAME).postValue(new Object());
                } else {
                    LogUtil.e(TAG, e.getLocalizedMessage());
                    view.makeToast(e.getLocalizedMessage());
                }
            }
        });
    }
}
