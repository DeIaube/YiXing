package arouter.dawn.zju.edu.module_pay.pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;

import java.util.Map;

import arouter.dawn.zju.edu.module_pay.callback.PayCallback;
import arouter.dawn.zju.edu.module_pay.bean.PayResult;
import arouter.dawn.zju.edu.module_pay.util.OrderInfoUtil2_0;

public class AliPay {

	private static final int SDK_PAY_FLAG = 1;

	private Activity context;
	private double price;
	private String title;
	private String content;
	private PayCallback payCallback;

	AliPay(Activity context, double price, String title, String content, PayCallback payCallback) {
		this.context = context;
		this.price = price;
		this.title = title;
		this.content = content;
		this.payCallback = payCallback;
	}

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG: {
				@SuppressWarnings("unchecked")
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
				String resultStatus = payResult.getResultStatus();
				if (TextUtils.equals(resultStatus, "9000")) {
					payCallback.paySuccess();
				} else {
					payCallback.payFailed(payResult.getFaileMessage());
				}
				break;
			}
			}
		}
	};

	/**
	 * 支付宝支付业务
	 * 
	 * @param v
	 */
	public void pay(View v) {
		EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
		Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(title, content, price);
		String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
		String sign = OrderInfoUtil2_0.getSign(params, true);
		final String orderInfo = orderParam + "&" + sign;
		Runnable payRunnable = new Runnable() {
			@Override
			public void run() {
				PayTask alipay = new PayTask(context);
				Map<String, String> result = alipay.payV2(orderInfo, true);
				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

}
