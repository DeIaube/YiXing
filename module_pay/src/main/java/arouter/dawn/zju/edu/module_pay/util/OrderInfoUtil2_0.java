package arouter.dawn.zju.edu.module_pay.util;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class OrderInfoUtil2_0 {

	/**
	 * 构造授权参数列表
	 * 
	 * @param pid
	 * @param app_id
	 * @param target_id
	 * @return
	 */
	public static Map<String, String> buildAuthInfoMap(String pid, String app_id, String target_id, boolean rsa2) {
		Map<String, String> keyValues = new HashMap<String, String>();

		// 商户签约拿到的app_id，如：2013081700024223
		keyValues.put("app_id", app_id);

		// 商户签约拿到的pid，如：2088102123816631
		keyValues.put("pid", pid);

		// 服务接口名称， 固定值
		keyValues.put("apiname", "com.alipay.account.auth");

		// 商户类型标识， 固定值
		keyValues.put("app_name", "mc");

		// 业务类型， 固定值
		keyValues.put("biz_type", "openservice");

		// 产品码， 固定值
		keyValues.put("product_id", "APP_FAST_LOGIN");

		// 授权范围， 固定值
		keyValues.put("scope", "kuaijie");

		// 商户唯一标识，如：kkkkk091125
		keyValues.put("target_id", target_id);

		// 授权类型， 固定值
		keyValues.put("auth_type", "AUTHACCOUNT");

		// 签名类型
		keyValues.put("sign_type", rsa2 ? "RSA2" : "RSA");

		return keyValues;
	}

	/**
	 * 构造支付订单参数列表
	 * @return
	 */
	public static Map<String, String> buildOrderParamMap(String orderTitle, String orderContent, double orderPrice) {
		Map<String, String> keyValues = new HashMap<String, String>();
		keyValues.put("app_id", "2016080100137923");
		String sss = "{\"timeout_express\":\"30m\",\"product_code\":\"QUICK_MSECURITY_PAY\",\"total_amount\":\"" + orderPrice + "\",\"subject\":\""+orderTitle+"\",\"body\":\""+orderContent+"\",\"out_trade_no\":\"" + getOutTradeNo() +  "\"}";

		keyValues.put("biz_content", sss);

		keyValues.put("charset", "utf-8");

		keyValues.put("method", "alipay.trade.app.pay");

		keyValues.put("sign_type", "RSA2");

		keyValues.put("timestamp", "2016-07-29 16:55:53");

		keyValues.put("version", "1.0");

		return keyValues;
	}
	
	/**
	 * 构造支付订单参数信息
	 * 
	 * @param map
	 * 支付订单参数
	 * @return
	 */
	public static String buildOrderParam(Map<String, String> map) {
		List<String> keys = new ArrayList<String>(map.keySet());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < keys.size() - 1; i++) {
			String key = keys.get(i);
			String value = map.get(key);
			sb.append(buildKeyValue(key, value, true));
			sb.append("&");
		}

		String tailKey = keys.get(keys.size() - 1);
		String tailValue = map.get(tailKey);
		sb.append(buildKeyValue(tailKey, tailValue, true));

		return sb.toString();
	}
	
	/**
	 * 拼接键值对
	 * 
	 * @param key
	 * @param value
	 * @param isEncode
	 * @return
	 */
	private static String buildKeyValue(String key, String value, boolean isEncode) {
		StringBuilder sb = new StringBuilder();
		sb.append(key);
		sb.append("=");
		if (isEncode) {
			try {
				sb.append(URLEncoder.encode(value, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				sb.append(value);
			}
		} else {
			sb.append(value);
		}
		return sb.toString();
	}
	
	/**
	 * 对支付参数信息进行签名
	 * 
	 * @param map
	 *            待签名授权信息
	 * 
	 * @return
	 */
	public static String getSign(Map<String, String> map, boolean rsa2) {
		List<String> keys = new ArrayList<String>(map.keySet());
		// key排序
		Collections.sort(keys);

		StringBuilder authInfo = new StringBuilder();
		for (int i = 0; i < keys.size() - 1; i++) {
			String key = keys.get(i);
			String value = map.get(key);
			authInfo.append(buildKeyValue(key, value, false));
			authInfo.append("&");
		}

		String tailKey = keys.get(keys.size() - 1);
		String tailValue = map.get(tailKey);
		authInfo.append(buildKeyValue(tailKey, tailValue, false));

		String oriSign = SignUtils.sign(authInfo.toString(), "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQChP753r6lVRpUxQMQ/QUmQ+tWfKxmidDmLH3iVCPaUlx3uQTAM3j6tXQ/K+crFVZ5L/H5iO80gZX7X1G+t0wY/rbuMIDQmxP0Y2IvBeSYicj6M8YXmYp0X+bsWBP0SNQ/hv7cl/VrHJuZcuxmyS8yWmAlcMchrWNqFO1E7bhFpztD+K/Fo/p8unZmWrpz2MOpyitD+WJ1Lf3j94Ncw6aUcv0YoaKAVLteqBnOystsBhzC5F93KeP6LPjtDvxV7h5T9ehaBc7ONdajlYefMAPOqrwePzpCEvRBFGDdPYhmYoV0AkEhhP1h3vsra7oeKC5nrCLp8+UVDOLZRebFsvisJAgMBAAECggEBAKDFjKOKylvS7yfXepPTBYV/AwUbFQkLtuAGtdRqxmfqsBYAeucpo/Z4ZimrmsfcEOOUUXKiS2h0qd6J2fvG/mD5WJhi93x8RhnCtr1ljGcl30Xp/eZyBk2EWkufF7BSpY4g93vyOtN77o0go4NI3ih8WEDLBhg51E6okNqfaM9DoTSGYS7jj0F+CmeZzEO8Jut0gLmEte2n6q9GI83OHZ1tU/fWrdSaj0oNNy+QCYxnKuGhxQPDdniQFEECh/y3McsVRgpSCYUKKW6xlf4ZnR0Yfv0ugPGgMWm8VKMbKj50EK5A1+n6ulLX3bQFTMHOPjtNglElNidAi3/6Kxx1hPECgYEA8s/rdLSjVZgutRlimPx4crFWyRJS75kLL0344jlQ+tZmsHvjzJIfTu9YWmZmMh3x2apOjGTPH9UQuzxJiV0do+Za80b3o/wbWextQ0rEjkMagQXqQbt2W0eo2IMRiyOvpFASS2pJd9RnevghQAHSaCIkK+wITD1pgxv4o4YhZQ0CgYEAqgHDXKnur5FDswdd55jK+g3UYKDgl0ox10BxGUgHYlOPmsIvg4Z5t+OfdgZtwruggC/+s/93WGLiJ2iO90j11gx1B9xB8enrZeJfmXQ3+dvKhyqwCkF7MYVkH/J+icXLXaOHsRzz4NPpvbu60dZjH1t0T1Kbcz99/TtJvx2Wlu0CgYBDMmluFeOx93jNo6XWwuAulYd4A/sx1b/zqnd7W/NeR+GJa+/iGHpTpQdllTspGYJqA6jTT77Gh3em6YtMWZmvVzvKYdR87HNBUp32ZoW4/Tcjh9iJsnCR2GYQ7Uz4nbqpFYPKnTKR2BV/QZYqGZb6PKCYCy1MtB40K5SG5MLvLQKBgE98t40x3umzr8WbIirQ48Z6hNwRHDFgfWE5Pigl513v1+rx9Ck4zNxPzxTt+I/eDjMhPaOHRJPM2BS47G9ua5MDDAz6zNKBHpDqgOxMzIdEXrNIIDsLVsBWr/PH+6YLFwTXGvCSquxp6JXPmo/V/06AAie8ZfZCr3OhLt/uw6QZAoGAdOtBRijHw3F0sSxSN+vs47fmxUSWOnre79aDVIr80x6XTGVNLuQSYvq8WuDLrXEd5LWjUAwaVOxpiDc77OiP79MBws4HNIXOgZ5hUzNvj0N1Uu8uCGujWJbRTEGMRcruoSj1msF8/mAgxXCCdbMKTBPeKsSNIrgpKwNltWOXyAM=", rsa2);
		String encodedSign = "";

		try {
			encodedSign = URLEncoder.encode(oriSign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "sign=" + encodedSign;
	}
	
	/**
	 * 要求外部订单号必须唯一。
	 * @return
	 */
	private static String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);

		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}

}
