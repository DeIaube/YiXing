package arouter.dawn.zju.edu.module_pay.util;

import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

import baselib.App;

public class FingerprintUtil {
    
    @SuppressLint("MissingPermission")
    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isFinger() {
        FingerprintManager manager = (FingerprintManager) App.getContext().getSystemService(Context.FINGERPRINT_SERVICE);
        KeyguardManager keyManager = (KeyguardManager) App.getContext().getSystemService(Context.KEYGUARD_SERVICE);
        if (!manager.isHardwareDetected()) {
            return false;
        }
        if (!keyManager.isKeyguardSecure()) {
            return false;
        }
        if (!manager.hasEnrolledFingerprints()) {
            return false;
        }
        return true;
    }
}
