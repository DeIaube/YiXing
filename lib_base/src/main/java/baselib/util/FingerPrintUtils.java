package baselib.util;

import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import androidx.core.os.CancellationSignal;

import baselib.App;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 指纹相关的工具类
 */
public class FingerPrintUtils {
    
    public static boolean isFinger() {
        FingerprintManager manager = (FingerprintManager) App.getAppalication().getSystemService(Context.FINGERPRINT_SERVICE);
        KeyguardManager keyManager = (KeyguardManager) App.getAppalication().getSystemService(Context.KEYGUARD_SERVICE);
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

    private static FingerprintManagerCompat mFingerprintManagerCompat;
    private static CancellationSignal mCancellationSignal;

    public interface FingerPrintResult {
        void success();
        void error(int code, CharSequence info);
        void retry(int code, CharSequence info);
    }

    public static boolean cancelCallback(){
        if(mCancellationSignal!=null&&!mCancellationSignal.isCanceled()){
            mCancellationSignal.cancel();
            mCancellationSignal=null;
            return true;
        }else{
            return false;
        }
    }

    public static void init(Context context, final FingerPrintResult fpResult) {
        if (mFingerprintManagerCompat == null || mCancellationSignal == null) {
            mFingerprintManagerCompat = FingerprintManagerCompat.from(context);
            mCancellationSignal = new CancellationSignal();
        }
        if (mFingerprintManagerCompat.isHardwareDetected() && mFingerprintManagerCompat.hasEnrolledFingerprints()) {
            mFingerprintManagerCompat.authenticate(null, 0, mCancellationSignal, new FingerprintManagerCompat.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    if (errorCode == FingerprintManager.FINGERPRINT_ERROR_LOCKOUT) {
                        fpResult.error(FingerprintManager.FINGERPRINT_ERROR_LOCKOUT, errString);
                    }else {
                        fpResult.retry(errorCode,errString);
                    }
                }

                @Override
                public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                    super.onAuthenticationHelp(helpCode, helpString);
                    fpResult.retry(helpCode,helpString);
                }

                @Override
                public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    fpResult.success();
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    fpResult.retry(0,"authentication failed");
                }
            }, null);
        }
    }

}
