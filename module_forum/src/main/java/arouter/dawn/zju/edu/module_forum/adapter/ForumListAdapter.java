package arouter.dawn.zju.edu.module_forum.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.CountCallback;
import com.avos.avoscloud.GetCallback;
import com.squareup.picasso.Picasso;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.ForumComment;
import arouter.dawn.zju.edu.lib_net.bean.ForumPost;
import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_forum.R;

public class ForumListAdapter extends RecyclerView.Adapter<ForumListAdapter.ForumListHolder> {

    private Context mContext;
    private List<ForumPost> mForumListItems;
    private onForumListClickListener mOnForumListClickListener;

    public void setOnForumListClickListener(ForumListAdapter.onForumListClickListener onForumListClickListener) {
        this.mOnForumListClickListener = onForumListClickListener;
    }

    public ForumListAdapter(Context mContext, List<ForumPost> mForumListItems) {
        this.mContext = mContext;
        this.mForumListItems = mForumListItems;
    }

    public interface onForumListClickListener {
        void likePost(ForumPost post);
        void clickPost(ForumPost post);
    }

    public void refresh(List<ForumPost> forumPostList) {
        this.mForumListItems = forumPostList;
        notifyDataSetChanged();
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
        holder.likeCountTv.setText(String.valueOf(post.getLikesUser().size()));
        showCommentCounter(holder.commentCountTv, post);
        holder.postition = i;
    }

    private void showCommentCounter(final TextView counterTv, ForumPost post) {
        AVQuery<ForumComment> commentAVQuery = ForumComment.getQuery(ForumComment.class);
        commentAVQuery.whereEqualTo("post", post)
                .countInBackground(new CountCallback() {
                    @Override
                    public void done(int i, AVException e) {
                        if (e == null) {
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
        TextView commentCountTv;

        public ForumListHolder(@NonNull View itemView) {
            super(itemView);
            portraitIv = itemView.findViewById(R.id.forum_list_author_portrait);
            nameTv = itemView.findViewById(R.id.forum_list_author_name);
            tagTv = itemView.findViewById(R.id.forum_list_tag);
            titleTv = itemView.findViewById(R.id.forum_list_title);
            contentTv = itemView.findViewById(R.id.forum_list_content);
            likeCountTv = itemView.findViewById(R.id.like_count);
            commentCountTv = itemView.findViewById(R.id.comment_count);
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
                        mOnForumListClickListener.likePost(mForumListItems.get(postition));
                    }
                }
            });
        }
    }
}
