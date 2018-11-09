package arouter.dawn.zju.edu.module_forum.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.module_forum.R;

public class ForumAlterTabAdapter extends RecyclerView.Adapter<ForumAlterTabAdapter.ForumAlterTabHolder> {

    private Context mContext;
    private List<String> mTitles;
    private List<String> mKeys;

    public ForumAlterTabAdapter(Context mContext) {
        this.mContext = mContext;
        initData();
    }

    private void initData() {
        mTitles = new ArrayList<>();
        mKeys = new ArrayList<>();

    }



    @NonNull
    @Override
    public ForumAlterTabHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(mContext).
                inflate(R.layout.item_forum_alter_tab, viewGroup, false);
        return new ForumAlterTabHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ForumAlterTabHolder forumAlterTabHolder, int i) {
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ForumAlterTabHolder extends RecyclerView.ViewHolder {

        SwitchCompat switchCompat;

        public ForumAlterTabHolder(@NonNull View itemView) {
            super(itemView);
            switchCompat = itemView.findViewById(R.id.switchWidget);
        }
    }
}
