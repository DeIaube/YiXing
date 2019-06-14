package arouter.dawn.zju.edu.module_forum.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import arouter.dawn.zju.edu.module_forum.R;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 发布帖子页面标签的适配器
 */
public class ForumAddPostSelectTagAdapter extends RecyclerView.Adapter<ForumAddPostSelectTagAdapter.PostSelectTagHolder> {

    private Context mContext;
    private List<String> mTags;
    private String mCurrentTag;

    public ForumAddPostSelectTagAdapter(Context mContext, List<String> tags) {
        this.mContext = mContext;
        this.mTags = tags;
        if (!tags.isEmpty()) {
            this.mCurrentTag = tags.get(0);
        }
    }

    @NonNull
    @Override
    public PostSelectTagHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_forum_add_post_tag, viewGroup, false);
        return new PostSelectTagHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostSelectTagHolder postSelectTagHolder, final int i) {
        postSelectTagHolder.textView.setText(mTags.get(i));
        if (mCurrentTag != null && mCurrentTag.equals(mTags.get(i))) {
            postSelectTagHolder.textView.setTextColor(mContext.getResources().getColor(R.color.white));
            postSelectTagHolder.textView.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
        } else {
            postSelectTagHolder.textView.setTextColor(mContext.getResources().getColor(R.color.lightgrey));
            postSelectTagHolder.textView.setBackgroundColor(mContext.getResources().getColor(R.color.whitesmoke));
        }
        postSelectTagHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentTag = mTags.get(i);
                notifyDataSetChanged();
            }
        });
    }

    public String getTag() {
        return mCurrentTag;
    }

    @Override
    public int getItemCount() {
        return mTags.size();
    }

    class PostSelectTagHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public PostSelectTagHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}
