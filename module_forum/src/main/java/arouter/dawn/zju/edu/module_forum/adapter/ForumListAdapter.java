package arouter.dawn.zju.edu.module_forum.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.ForumPost;
import arouter.dawn.zju.edu.module_forum.R;

public class ForumListAdapter extends RecyclerView.Adapter<ForumListAdapter.ForumListHolder> {

    private Context mContext;
    private List<ForumPost> mForumListItems;

    public ForumListAdapter(Context mContext, List<ForumPost> mForumListItems) {
        this.mContext = mContext;
        this.mForumListItems = mForumListItems;
    }

    @NonNull
    @Override
    public ForumListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_forum_list, viewGroup, false);
        return new ForumListHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ForumListHolder forumListHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ForumListHolder extends RecyclerView.ViewHolder {

        public ForumListHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
