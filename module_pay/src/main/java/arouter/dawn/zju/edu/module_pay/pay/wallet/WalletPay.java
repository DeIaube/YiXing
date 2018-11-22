package arouter.dawn.zju.edu.module_pay.pay.wallet;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.ethanco.lib.PasswordInput;

import java.util.Timer;
import java.util.TimerTask;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_pay.R;
import arouter.dawn.zju.edu.module_pay.callback.PayCallback;
import baselib.util.FingerPrintUtils;
import baselib.config.Constants;
import baselib.util.SPUtil;

public class WalletPay {

    private Activity context;
    private double price;
    private String title;
    private String content;
    private PayCallback payCallback;

    public WalletPay(Activity context, double price, String title, String content, PayCallback payCallback) {
        this.context = context;
        this.price = price;
        this.title = title;
        this.content = content;
        this.payCallback = payCallback;
    }

    public void pay(View v) {
        if (price <= User.getCurrentUser(User.class).getSeretPayment()) {
            checkBalance();
        } else {
            if (FingerPrintUtils.isFinger() &&
                    SPUtil.getBoolean(Constants.SP_PAY_FOR_FINGERPRINT, false)) {
                payByFingerPrint();
            } else {
                payByPayPassword();
            }
        }
    }

    private void payByFingerPrint() {
        final AlertDialog checkFingerPrintDialog = new AlertDialog.Builder(context)
                .setTitle(R.string.wallet_pay_check_finger_print)
                .setNegativeButton(R.string.cancel, null)
                .setView(R.layout.dialog_wallet_pay_finger)
                .create();
        
        FingerPrintUtils.init(context, new FingerPrintUtils.FingerPrintResult() {
            @Override
            public void success() {
                checkFingerPrintDialog.dismiss();
                checkBalance();
            }

            @Override
            public void error(int code, CharSequence info) {
                Toast.makeText(context, context.getString(R.string.wallet_pay_finger_print_check_error), Toast.LENGTH_SHORT).show();
                checkFingerPrintDialog.dismiss();
            }
            
            @Override
            public void retry(int code, CharSequence info) {
                Toast.makeText(context, context.getString(R.string.wallet_pay_finger_print_check_retry), Toast.LENGTH_SHORT).show();
            }
        });
        
        checkFingerPrintDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                FingerPrintUtils.cancelCallback();
            }
        });
        checkFingerPrintDialog.setCancelable(false);
        checkFingerPrintDialog.show();
    }

    private void checkBalance() {
        User user = User.getCurrentUser(User.class);
        if (user.getBalance() < price) {
            payCallback.payFailed("余额不足");
        } else {
            user.setBalance(user.getBalance() - price);
            user.saveInBackground();
            payCallback.paySuccess();
        }
    }

    private void payByPayPassword() {
        View rootView = LayoutInflater.from(context).inflate(R.layout.dialog_wallet_input_pay_pay_password, null);
        final AlertDialog payPassworDialog = new AlertDialog.Builder(context)
                .setView(rootView)
                .setTitle(R.string.wallet_pay_please_input_pay_password)
                .setCancelable(false)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        payCallback.payFailed(context.getString(R.string.wallet_pay_cancel_pay));
                    }
                })
                .create();
        final PasswordInput passwordInput = rootView.findViewById(R.id.password_input);
        passwordInput.setTextLenChangeListener(new PasswordInput.TextLenChangeListener() {
            @Override
            public void onTextLenChange(CharSequence charSequence, int i) {
                if (i != 6) {
                    return;
                }
                String password = charSequence.toString();
                if (User.getCurrentUser(User.class).getPayPassword().equals(password)) {
                    checkBalance();
                } else {
                    payCallback.payFailed(context.getString(R.string.wallet_pay_password_error));
                }
                payPassworDialog.dismiss();
            }
        });
        passwordInput.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == View.VISIBLE) {
                    showSoftInput(passwordInput);
                }
            }
        });
        payPassworDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                hideSoftInput();
            }
        });
        payPassworDialog.show();
    }

    /**
     * 隐藏软键盘
     * 延时任务
     */
    private void hideSoftInput() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager imm =  (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                if(imm != null) {
                    imm.hideSoftInputFromWindow(context.getWindow().getDecorView().getWindowToken(),
                            0);
                }
            }
        }, 250);
    }

    /**
     * 显示软键盘
     */
    private void showSoftInput(final View v) {
        v.setFocusable(true);
        v.setFocusableInTouchMode(true);
        v.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v,0);
    }

}
