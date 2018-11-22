package arouter.dawn.zju.edu.module_forum.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.forum.ForumComment;
import arouter.dawn.zju.edu.module_forum.R;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 帖子回复的适配器
 */
public class ForumPostCommentAdapter extends RecyclerView.Adapter<ForumPostCommentAdapter.CommentHolder> {

    private Context mContext;
    private List<ForumComment> mCommentList;

    public ForumPostCommentAdapter(Context context, List<ForumComment> commentList) {
        this.mContext = context;
        this.mCommentList = commentList;
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_forum_post_comment, viewGroup, false);
        return new CommentHolder(rootView);
    }

    @SuppressLint({"SimpleDateFormat", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int i) {
        ForumComment comment = mCommentList.get(i);
        Picasso.with(mContext).load(comment.getOwner().getPortrait()).into(holder.authorPortraitIv);
        holder.authorPicknameTv.setText(comment.getOwner().getPickName());
        holder.commentInformationTv.setText(String.format("%d楼 · %s", i + 1,
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(comment.getCreatedAt())));
        holder.commentContentTv.setText(comment.getConent());
    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }

    public void refresh(List<ForumComment> commentList) {
        mCommentList = commentList;
        notifyDataSetChanged();
    }

    class CommentHolder extends RecyclerView.ViewHolder {

        ImageView authorPortraitIv;
        TextView authorPicknameTv;
        TextView commentContentTv;
        TextView commentInformationTv;

        public CommentHolder(@NonNull View itemView) {
            super(itemView);
            authorPortraitIv = itemView.findViewById(R.id.forum_post_comment_author_portrait);
            authorPicknameTv = itemView.findViewById(R.id.forum_post_comment_author_pickname);
            commentInformationTv = itemView.findViewById(R.id.forum_post_comment_information);
            commentContentTv = itemView.findViewById(R.id.forum_post_comment_content);
        }
    }
}
