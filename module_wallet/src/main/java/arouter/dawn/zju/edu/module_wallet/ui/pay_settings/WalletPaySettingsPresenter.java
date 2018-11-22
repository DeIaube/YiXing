package arouter.dawn.zju.edu.module_wallet.ui.pay_settings;


import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

import arouter.dawn.zju.edu.module_wallet.R;
import baselib.App;
import baselib.base.BasePresenter;
import baselib.config.Constants;
import baselib.util.SPUtil;

public class WalletPaySettingsPresenter extends BasePresenter<WalletPaySettingsContract.View> implements WalletPaySettingsContract.Presenter {

    static final String TAG = "WalletSettingsPresenter";

    @Override
    public void refreshView() {
        mView.setFingerprintStatus(SPUtil.getBoolean(Constants.SP_PAY_FOR_FINGERPRINT, false));
    }

    @Override
    public void openFingerprint() {
        if (!isFinger()) {
            mView.setFingerprintStatus(false);
            return;
        }
        SPUtil.put(Constants.SP_PAY_FOR_FINGERPRINT, true);
        mView.setFingerprintStatus(true);
    }

    @Override
    public void closeFingerprint() {
        SPUtil.put(Constants.SP_PAY_FOR_FINGERPRINT, false);
        mView.setFingerprintStatus(false);
    }

    private boolean isFinger() {
        FingerprintManager manager = (FingerprintManager) App.getContext().getSystemService(Context.FINGERPRINT_SERVICE);
        KeyguardManager keyManager = (KeyguardManager) App.getContext().getSystemService(Context.KEYGUARD_SERVICE);
        if (!manager.isHardwareDetected()) {
            mView.showMessage(App.getContext().getString(R.string.wallet_settings_no_fingerprint_mpdule));
            return false;
        }
        if (!keyManager.isKeyguardSecure()) {
            mView.showMessage(App.getContext().getString(R.string.wallet_settings_no_keyguard_secure));
            return false;
        }
        if (!manager.hasEnrolledFingerprints()) {
            mView.showMessage(App.getContext().getString(R.string.wallet_settings_no_fingerprint));
            return false;
        }
        return true;
    }
}
