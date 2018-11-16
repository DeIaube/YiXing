package arouter.dawn.zju.edu.module_pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;

import java.util.Map;

import arouter.dawn.zju.edu.module_pay.util.OrderInfoUtil2_0;

/**
 *  重要说明:
 *  
 *  这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
 *  真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
 *  防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险； 
 */
public class PayDemoActivity extends Activity {
	
	public static final String APPID = "2016080100137923";
	public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQChP753r6lVRpUxQMQ/QUmQ+tWfKxmidDmLH3iVCPaUlx3uQTAM3j6tXQ/K+crFVZ5L/H5iO80gZX7X1G+t0wY/rbuMIDQmxP0Y2IvBeSYicj6M8YXmYp0X+bsWBP0SNQ/hv7cl/VrHJuZcuxmyS8yWmAlcMchrWNqFO1E7bhFpztD+K/Fo/p8unZmWrpz2MOpyitD+WJ1Lf3j94Ncw6aUcv0YoaKAVLteqBnOystsBhzC5F93KeP6LPjtDvxV7h5T9ehaBc7ONdajlYefMAPOqrwePzpCEvRBFGDdPYhmYoV0AkEhhP1h3vsra7oeKC5nrCLp8+UVDOLZRebFsvisJAgMBAAECggEBAKDFjKOKylvS7yfXepPTBYV/AwUbFQkLtuAGtdRqxmfqsBYAeucpo/Z4ZimrmsfcEOOUUXKiS2h0qd6J2fvG/mD5WJhi93x8RhnCtr1ljGcl30Xp/eZyBk2EWkufF7BSpY4g93vyOtN77o0go4NI3ih8WEDLBhg51E6okNqfaM9DoTSGYS7jj0F+CmeZzEO8Jut0gLmEte2n6q9GI83OHZ1tU/fWrdSaj0oNNy+QCYxnKuGhxQPDdniQFEECh/y3McsVRgpSCYUKKW6xlf4ZnR0Yfv0ugPGgMWm8VKMbKj50EK5A1+n6ulLX3bQFTMHOPjtNglElNidAi3/6Kxx1hPECgYEA8s/rdLSjVZgutRlimPx4crFWyRJS75kLL0344jlQ+tZmsHvjzJIfTu9YWmZmMh3x2apOjGTPH9UQuzxJiV0do+Za80b3o/wbWextQ0rEjkMagQXqQbt2W0eo2IMRiyOvpFASS2pJd9RnevghQAHSaCIkK+wITD1pgxv4o4YhZQ0CgYEAqgHDXKnur5FDswdd55jK+g3UYKDgl0ox10BxGUgHYlOPmsIvg4Z5t+OfdgZtwruggC/+s/93WGLiJ2iO90j11gx1B9xB8enrZeJfmXQ3+dvKhyqwCkF7MYVkH/J+icXLXaOHsRzz4NPpvbu60dZjH1t0T1Kbcz99/TtJvx2Wlu0CgYBDMmluFeOx93jNo6XWwuAulYd4A/sx1b/zqnd7W/NeR+GJa+/iGHpTpQdllTspGYJqA6jTT77Gh3em6YtMWZmvVzvKYdR87HNBUp32ZoW4/Tcjh9iJsnCR2GYQ7Uz4nbqpFYPKnTKR2BV/QZYqGZb6PKCYCy1MtB40K5SG5MLvLQKBgE98t40x3umzr8WbIirQ48Z6hNwRHDFgfWE5Pigl513v1+rx9Ck4zNxPzxTt+I/eDjMhPaOHRJPM2BS47G9ua5MDDAz6zNKBHpDqgOxMzIdEXrNIIDsLVsBWr/PH+6YLFwTXGvCSquxp6JXPmo/V/06AAie8ZfZCr3OhLt/uw6QZAoGAdOtBRijHw3F0sSxSN+vs47fmxUSWOnre79aDVIr80x6XTGVNLuQSYvq8WuDLrXEd5LWjUAwaVOxpiDc77OiP79MBws4HNIXOgZ5hUzNvj0N1Uu8uCGujWJbRTEGMRcruoSj1msF8/mAgxXCCdbMKTBPeKsSNIrgpKwNltWOXyAM=";

	private static final int SDK_PAY_FLAG = 1;

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@SuppressWarnings("unused")
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG: {
				@SuppressWarnings("unchecked")
				PayResult payResult = new PayResult((Map<String, String>) msg.obj);
				/**
				 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
				 */
				String resultInfo = payResult.getResult();// 同步返回需要验证的信息
				String resultStatus = payResult.getResultStatus();
				// 判断resultStatus 为9000则代表支付成功
				if (TextUtils.equals(resultStatus, "9000")) {
					// 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
					Toast.makeText(PayDemoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
				} else {
					// 该笔订单真实的支付结果，需要依赖服务端的异步通知。
					Toast.makeText(PayDemoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
				}
				break;
			}
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Button btn = new Button(this);
		setContentView(btn);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				payV2(v);
			}
		});
		EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
	}
	
	/**
	 * 支付宝支付业务
	 * 
	 * @param v
	 */
	public void payV2(View v) {
		/**
		 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
		 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
		 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险； 
		 * 
		 * orderInfo的获取必须来自服务端；
		 */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
		Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
		String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

		String sign = OrderInfoUtil2_0.getSign(params, RSA2_PRIVATE, rsa2);
		final String orderInfo = orderParam + "&" + sign;

		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				PayTask alipay = new PayTask(PayDemoActivity.this);
				Map<String, String> result = alipay.payV2(orderInfo, true);
				Log.i("msp", result.toString());

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
