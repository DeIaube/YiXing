package arouter.dawn.zju.edu.module_wallet.ui.pay_settings;


import arouter.dawn.zju.edu.module_wallet.R;
import baselib.App;
import baselib.base.BasePresenter;
import baselib.constants.SpConstants;
import baselib.util.FingerPrintUtils;
import baselib.util.SPUtil;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class WalletPaySettingsPresenter extends BasePresenter<WalletPaySettingsContract.View> implements WalletPaySettingsContract.Presenter {

    static final String TAG = "WalletPaySettingsPresenter";

    @Override
    public void refreshView() {
        if (!FingerPrintUtils.isFinger()) {
            mView.setFingerprintStatus(false);
            return;
        }
        mView.setFingerprintStatus(SPUtil.getBoolean(SpConstants.SP_PAY_FOR_FINGERPRINT, false));
    }

    @Override
    public void openFingerprint() {
        if (!FingerPrintUtils.isFinger()) {
            mView.setFingerprintStatus(false);
            mView.showMessage(App.getAppalication().getString(R.string.wallet_settings_no_fingerprint));
            return;
        }
        SPUtil.put(SpConstants.SP_PAY_FOR_FINGERPRINT, true);
        mView.setFingerprintStatus(true);
    }

    @Override
    public void closeFingerprint() {
        SPUtil.put(SpConstants.SP_PAY_FOR_FINGERPRINT, false);
        mView.setFingerprintStatus(false);
    }

}
