package arouter.dawn.zju.edu.module_pay.pay.wallet;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.ethanco.lib.PasswordInput;

import java.util.Timer;
import java.util.TimerTask;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_pay.R;
import arouter.dawn.zju.edu.module_pay.callback.PayCallback;

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
        payByPayPassword();
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
                .setTitle("请输入支付密码")
                .setCancelable(false)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        payCallback.payFailed("操作已经取消。");
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
                    payCallback.payFailed("支付密码错误");
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
