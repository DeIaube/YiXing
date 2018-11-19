package arouter.dawn.zju.edu.module_pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;

import java.util.Map;

import arouter.dawn.zju.edu.module_pay.util.OrderInfoUtil2_0;

public class AliPay {

	private static final int SDK_PAY_FLAG = 1;

	private Activity context;
	private double price;
	private String title;
	private String content;
	private PayCallback payCallback;

	private AliPay(Activity context, double price, String title, String content, PayCallback payCallback) {
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
					payCallback.payFailed();
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

	public static class Builed {
		private Activity context;
		private double price;
		private String title;
		private String content;
		private PayCallback payCallback = new PayCallback() {
			@Override
			public void paySuccess() {

			}

			@Override
			public void payFailed() {

			}
		};

		public Builed setPayCallback(PayCallback payCallback) {
			this.payCallback = payCallback;
			return this;
		}

		public Builed setPrice(double price) {
			this.price = price;
			return this;
		}

		public Builed setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builed setContent(String content) {
			this.content = content;
			return this;
		}

		public Builed(Activity context) {
			this.context = context;
		}

		public AliPay buile() {
			return new AliPay(context, price, title, content, payCallback);
		}
	}
}
