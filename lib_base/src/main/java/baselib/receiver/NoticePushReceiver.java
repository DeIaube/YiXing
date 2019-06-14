package baselib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import android.util.Log;


import arouter.dawn.zju.edu.lib_db.bean.Notice;
import baselib.util.LogUtil;
import io.realm.Realm;

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
        Realm.getDefaultInstance().executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.createObjectFromJson(Notice.class, data);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "Storage Notice Success");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d(TAG, "Storage Notice Error:" + error.toString());
            }
        });
    }
}
