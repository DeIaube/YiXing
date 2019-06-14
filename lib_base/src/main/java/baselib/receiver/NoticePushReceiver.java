package baselib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import baselib.util.LogUtil;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 接收消息推送
 */
public class NoticePushReceiver extends BroadcastReceiver {

    static final String  TAG = "NoticePushReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.d(TAG, "Get NoticePush");
        //获取消息内容
        final String data = intent.getExtras().getString("com.avos.avoscloud.Data");
        Log.d(TAG, "NoticePush data:" + data);
    }
}
