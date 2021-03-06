package arouter.dawn.zju.edu.module_forum.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.CountCallback;
import com.avos.avoscloud.FindCallback;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arouter.dawn.zju.edu.lib_net.bean.forum.ForumComment;
import arouter.dawn.zju.edu.lib_net.bean.forum.ForumPost;
import arouter.dawn.zju.edu.lib_net.bean.forum.ForumPostLike;
import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_forum.R;
import baselib.constants.RouteConstants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 帖子列表的适配器
 */
public class ForumPostListAdapter extends RecyclerView.Adapter<ForumPostListAdapter.ForumListHolder> {

    private Context mContext;
    private List<ForumPost> mForumListItems;
    private OnForumListClickListener mOnForumListClickListener;
    private Map<ForumPost, ForumPostLike> mPostLikeMap;
    private Map<String, Integer> mPostLikeCountCache;
    private Map<String, Boolean> mPostLikeStatusCache;
    private Map<String, Integer> mPostCommentCountCache;

    public ForumPostListAdapter(Context mContext, List<ForumPost> mForumListItems) {
        this.mContext = mContext;
        this.mForumListItems = mForumListItems;
        this.mOnForumListClickListener = new DeafultOnForumListClickListener();
        this.mPostLikeMap = new HashMap<>();
        this.mPostLikeCountCache = new HashMap<>();
        this.mPostLikeStatusCache = new HashMap<>();
        this.mPostCommentCountCache = new HashMap<>();
    }

    public interface OnForumListClickListener {
        void likePost(ForumPost post, TextView likeCounter, ImageView likeBg);
        void clickPost(ForumPost post);
        void clickAuthor(User user);
    }

    public void refresh(List<ForumPost> forumPostList) {
        this.mForumListItems = forumPostList;
        notifyDataSetChanged();
    }

    public void loadMore(List<ForumPost> forumPostList) {
        if (forumPostList.isEmpty()) {
            return;
        }
        int startIndex = mForumListItems.size();
        this.mForumListItems.addAll(forumPostList);
        notifyItemRangeChanged(startIndex, forumPostList.size());
    }

    @NonNull
    @Override
    public ForumListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_forum_list, viewGroup, false);
        return new ForumListHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ForumListHolder holder, int i) {
        ForumPost post = mForumListItems.get(i);
        Picasso.with(mContext).load(post.getAuthor().getPortrait()).into(holder.portraitIv);
        holder.nameTv.setText(post.getAuthor().getPickName());
        holder.tagTv.setText(post.getTag());
        holder.titleTv.setText(post.getTitle());
        holder.contentTv.setText(post.getContent());
        showCommentCounter(holder.commentCountTv, post);
        showLikeInformation(holder.likeCountTv, holder.likeBgIv, post);
        holder.postition = i;
    }

    private void showLikeInformation(final TextView likeCountTv, final ImageView likeBg,
                                     final ForumPost post) {
        if (mPostLikeStatusCache.containsKey(post.getObjectId()) &&
                mPostLikeCountCache.containsKey(post.getObjectId())) {
            setLikeState(likeCountTv, likeBg,
                    mPostLikeStatusCache.get(post.getObjectId()),
                    mPostLikeCountCache.get(post.getObjectId()));
        }
        AVQuery<ForumPostLike> forumPostLikeAVQuery = ForumPostLike.getQuery(ForumPostLike.class);
        forumPostLikeAVQuery.whereEqualTo("post", post)
                .findInBackground(new FindCallback<ForumPostLike>() {
                    @Override
                    public void done(List<ForumPostLike> list, AVException e) {
                        if (e == null) {
                            for (ForumPostLike forumPostLike : list) {
                                if (forumPostLike.getOwner().equals(User.getCurrentUser(User.class))) {
                                    mPostLikeMap.put(post, forumPostLike);
                                }
                            }
                            mPostLikeCountCache.put(post.getObjectId(), list.size());
                            mPostLikeStatusCache.put(post.getObjectId(), mPostLikeMap.get(post) == null);
                            setLikeState(likeCountTv, likeBg, mPostLikeMap.get(post) == null, list.size());
                        }
                    }
                });
    }

    /**
     * 更新点赞页面
     * @param likeCountTv 点赞数目
     * @param likeBg 点赞背景
     * @param statue 是否已经点赞
     */
    private void setLikeState(TextView likeCountTv, ImageView likeBg, boolean statue, int counter) {
        setLikeState(likeBg, statue);
        likeCountTv.setText(String.valueOf(counter));
    }

    private void changeLikeState(TextView likeCountTv, ImageView likeBg, boolean statue) {
        setLikeState(likeBg, statue);
        if (statue) {
            likeCountTv.setText(String.valueOf(Integer.valueOf(likeCountTv.getText().toString()) - 1));
        } else {
            likeCountTv.setText(String.valueOf(Integer.valueOf(likeCountTv.getText().toString()) + 1));
        }
    }

    private void setLikeState(ImageView likeBg, boolean statue) {
        if (statue) {
            Picasso.with(mContext).load(R.drawable.forum_unlike).into(likeBg);
        } else {
            Picasso.with(mContext).load(R.drawable.forum_likeing).into(likeBg);
        }
    }

    private void showCommentCounter(final TextView counterTv, final ForumPost post) {
        if (mPostCommentCountCache.containsKey(post.getObjectId())) {
            counterTv.setText(String.valueOf(mPostCommentCountCache.get(post.getObjectId())));
        }
        AVQuery<ForumComment> commentAVQuery = ForumComment.getQuery(ForumComment.class);
        commentAVQuery.whereEqualTo("post", post)
                .countInBackground(new CountCallback() {
                    @Override
                    public void done(int i, AVException e) {
                        if (e == null) {
                            mPostCommentCountCache.put(post.getObjectId(), i);
                            counterTv.setText(String.valueOf(i));
                        }
                    }
                });
    }

    @Override
    public int getItemCount() {
        return mForumListItems.size();
    }

    class ForumListHolder extends RecyclerView.ViewHolder {

        int postition;

        ImageView portraitIv;
        TextView nameTv;
        TextView tagTv;
        TextView titleTv;
        TextView contentTv;
        TextView likeCountTv;
        ImageView likeBgIv;
        TextView commentCountTv;

        public ForumListHolder(@NonNull View itemView) {
            super(itemView);
            portraitIv = itemView.findViewById(R.id.forum_list_author_portrait);
            nameTv = itemView.findViewById(R.id.forum_list_author_name);
            tagTv = itemView.findViewById(R.id.forum_list_tag);
            titleTv = itemView.findViewById(R.id.forum_list_title);
            contentTv = itemView.findViewById(R.id.forum_list_content);
            likeCountTv = itemView.findViewById(R.id.like_count);
            likeBgIv = itemView.findViewById(R.id.like_bg);
            commentCountTv = itemView.findViewById(R.id.comment_count);
            portraitIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnForumListClickListener != null) {
                        mOnForumListClickListener.clickAuthor(mForumListItems.get(postition).getAuthor());
                    }
                }
            });
            nameTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnForumListClickListener != null) {
                        mOnForumListClickListener.clickAuthor(mForumListItems.get(postition).getAuthor());
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnForumListClickListener != null) {
                        mOnForumListClickListener.clickPost(mForumListItems.get(postition));
                    }
                }
            });
            itemView.findViewById(R.id.like_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnForumListClickListener != null) {
                        mOnForumListClickListener.likePost(mForumListItems.get(postition), likeCountTv, likeBgIv);
                    }
                }
            });
        }
    }

    class DeafultOnForumListClickListener implements OnForumListClickListener {

        /**
         * 点赞
         * @param post 点赞的post
         */
        @Override
        public void likePost(ForumPost post, TextView likeCounter, ImageView likeBg) {
            ForumPostLike forumPostLike = mPostLikeMap.get(post);
            if (forumPostLike == null) {
                forumPostLike = new ForumPostLike();
                forumPostLike.setOwner(User.getCurrentUser(User.class));
                forumPostLike.setPost(post);
                forumPostLike.saveInBackground();
                mPostLikeMap.put(post, forumPostLike);
            } else {
                mPostLikeMap.remove(post);
                forumPostLike.deleteInBackground();
            }
            changeLikeState(likeCounter, likeBg, mPostLikeMap.get(post) == null);
        }

        /**
         * 跳转入帖子详情
         * @param post 点击的post
         */
        @Override
        public void clickPost(ForumPost post) {
            ARouter.getInstance().build(RouteConstants.AROUTER_FORUM_FORUM_POST).
                    withParcelable(RouteConstants.FORUM_POST_POST, post).navigation();
        }

        @Override
        public void clickAuthor(User user) {
            ARouter.getInstance()
                    .build(RouteConstants.AROUTER_FORUM_USER_INFORMATION)
                    .withString(RouteConstants.FORUM_USER_INFORMATION_USER_ID, user.getObjectId())
                    .navigation();
        }
    }
}
