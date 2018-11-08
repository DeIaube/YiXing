package arouter.dawn.zju.edu.module_forum.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class ForumListAdapter extends RecyclerView.Adapter<ForumListAdapter.ForumListHolder> {

    @NonNull
    @Override
    public ForumListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ForumListHolder forumListHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ForumListHolder extends RecyclerView.ViewHolder {

        public ForumListHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
