package arouter.dawn.zju.edu.module_forum.ui.list;


import android.annotation.SuppressLint;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.ForumPost;
import arouter.dawn.zju.edu.module_forum.config.EventBusCode;
import arouter.dawn.zju.edu.module_forum.ui.home.ForumHomeFragment;
import baselib.base.BasePresenter;
import baselib.bus.BusEvent;
import baselib.util.LogUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ForumListPresenter extends BasePresenter<ForumListContract.View> implements ForumListContract.Presenter {

    String TAG = "ForumListPresenter";

    @Override
    public void sendScrollUpEvent() {
        BusEvent event = new BusEvent();
        event.setTarget(ForumHomeFragment.TAG);
        event.setCode(EventBusCode.FORUM_LIST_SCROLL_UP);
        EventBus.getDefault().post(event);
    }

    @Override
    public void sendScrollDownEvent() {
        BusEvent event = new BusEvent();
        event.setTarget(ForumHomeFragment.TAG);
        event.setCode(EventBusCode.FORUM_LIST_SCROLL_DOWN);
        EventBus.getDefault().post(event);
    }

    @SuppressLint("CheckResult")
    @Override
    public void refresh(final String tag) {
        mView.showSwipeRefreshLayout();
        final AVQuery<ForumPost> query = ForumPost.getQuery(ForumPost.class);
        query.whereEqualTo("tag", tag).
                include("author").
                include("likes_user_list").
                include("comment_List").
                findInBackground(new FindCallback<ForumPost>() {
                    @Override
                    public void done(List<ForumPost> list, AVException e) {
                        mView.hideSwipeRefreshLayout();
                        if (e == null) {
                            LogUtil.i(TAG, "refresh:" + list.toString());
                            mView.refresh(list);
                        } else {
                            LogUtil.e(TAG, e.getLocalizedMessage());
                            mView.showMessage(e.getLocalizedMessage());
                        }
                    }
                });
    }
}
