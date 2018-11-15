package arouter.dawn.zju.edu.module_forum.ui.collection;


import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.ForumCollection;
import arouter.dawn.zju.edu.lib_net.bean.ForumPost;
import arouter.dawn.zju.edu.lib_net.bean.User;
import baselib.base.BasePresenter;
import baselib.util.LogUtil;

public class ForumCollectionPresenter extends BasePresenter<ForumCollectionContract.View> implements ForumCollectionContract.Presenter {

    private String TAG = "ForumCollectionPresenter";

    @Override
    public void refreshCollectionPostList() {
        AVQuery<ForumCollection> forumCollectionAVQuery = ForumCollection.getQuery(ForumCollection.class);
        forumCollectionAVQuery.include("post")
                .include("post.author")
                .whereEqualTo("owner", User.getCurrentUser(User.class))
                .findInBackground(new FindCallback<ForumCollection>() {
                    @Override
                    public void done(List<ForumCollection> list, AVException e) {
                        if (e == null) {
                            LogUtil.i(TAG, "refreshCollectionPostList:" + list.toString());
                            List<ForumPost> postList = new ArrayList<>();
                            for (ForumCollection forumCollection : list) {
                                postList.add(forumCollection.getPost());
                            }
                            mView.refreshCollectionPostList(postList);
                        } else {
                            LogUtil.e(TAG, e.getLocalizedMessage());
                            mView.showMessage(e.getLocalizedMessage());
                        }
                    }
                });
    }
}
