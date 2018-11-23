package arouter.dawn.zju.edu.module_forum.ui.list;


import android.annotation.SuppressLint;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.forum.ForumPost;
import arouter.dawn.zju.edu.module_forum.config.Constants;
import arouter.dawn.zju.edu.module_forum.config.EventBusCode;
import arouter.dawn.zju.edu.module_forum.ui.home.ForumHomeFragment;
import baselib.base.BasePresenter;
import baselib.bus.BusEvent;
import baselib.util.LogUtil;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class ForumListPresenter extends BasePresenter<ForumListContract.View> implements ForumListContract.Presenter {

    String TAG = "ForumListPresenter";

    private int currentIndex = 0;
    private static final int MAX_LIMIT = 10;

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
        currentIndex = 0;
        final AVQuery<ForumPost> query = ForumPost.getQuery(ForumPost.class);
        if (!tag.equals(Constants.TYPE_HOME)) {
            query.whereEqualTo("tag", tag);
        }
        query.include("author")
                .limit(MAX_LIMIT)
                .skip(MAX_LIMIT * currentIndex)
                .include("likes_user_list")
                .include("comment_List")
                .findInBackground(new FindCallback<ForumPost>() {
                    @Override
                    public void done(List<ForumPost> list, AVException e) {
                        mView.hideSwipeRefreshLayout();
                        if (e == null) {
                            currentIndex++;
                            LogUtil.i(TAG, "refresh:" + list.toString());
                            mView.refresh(list);
                        } else {
                            LogUtil.e(TAG, e.getLocalizedMessage());
                            mView.showMessage(e.getLocalizedMessage());
                        }
                    }
                });
    }

    @Override
    public void loadMore(final String tag) {
        mView.showSwipeRefreshLayout();
        final AVQuery<ForumPost> query = ForumPost.getQuery(ForumPost.class);
        if (!tag.equals(Constants.TYPE_HOME)) {
            query.whereEqualTo("tag", tag);
        }
        query.include("author")
                .limit(MAX_LIMIT)
                .skip(MAX_LIMIT * (currentIndex))
                .include("likes_user_list")
                .include("comment_List")
                .findInBackground(new FindCallback<ForumPost>() {
                    @Override
                    public void done(List<ForumPost> list, AVException e) {
                        mView.hideSwipeRefreshLayout();
                        if (e == null) {
                            LogUtil.i(TAG, "loadMore:" + list.toString());
                            currentIndex++;
                            mView.loadMore(list);
                        } else {
                            LogUtil.e(TAG, e.getLocalizedMessage());
                            mView.showMessage(e.getLocalizedMessage());
                        }
                    }
                });
    }
}
