package arouter.dawn.zju.edu.module_mine.ui.notice;


import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_db.bean.Notice;
import baselib.base.BasePresenter;
import baselib.util.LogUtil;
import io.realm.Realm;
import io.realm.RealmResults;

public class NoticePresenter extends BasePresenter<NoticeContract.View> implements NoticeContract.Presenter {

    static final String TAG = "NoticePresenter";

    @Override
    public void refreshNoticeList() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Notice> noticeRealmResults = realm.where(Notice.class).findAll();
                List<Notice> noticeList = new ArrayList<>();
                for (Notice noticeRealmResult : noticeRealmResults) {
                    noticeList.add(new Notice(noticeRealmResult.getTitle(),
                            noticeRealmResult.getContent(), noticeRealmResult.getTime()));
                }
                mView.refreshNoticeList(noticeList);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                LogUtil.i(TAG, "refreshNoticeList");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                LogUtil.e(TAG, error.toString());
            }
        });
    }
}
