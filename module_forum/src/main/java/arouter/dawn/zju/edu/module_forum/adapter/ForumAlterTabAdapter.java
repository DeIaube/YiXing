package arouter.dawn.zju.edu.module_forum.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.module_forum.R;
import arouter.dawn.zju.edu.module_forum.config.Constants;
import baselib.util.SPUtil;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 更改论坛首页Tab页面的适配器
 */
public class ForumAlterTabAdapter extends RecyclerView.Adapter<ForumAlterTabAdapter.ForumAlterTabHolder> {

    private Context mContext;
    private List<String> mTitles;
    private List<String> mKeys;
    private TabStatusListener mTabStatusListener;

    public void setTabStatusListener(TabStatusListener mTabStatusListener) {
        this.mTabStatusListener = mTabStatusListener;
    }

    public ForumAlterTabAdapter(Context mContext) {
        this.mContext = mContext;
        initData();
    }


    public interface TabStatusListener {
        void statusChange();
    }

    private void initData() {
        mTitles = new ArrayList<>();
        mKeys = new ArrayList<>();

        mTitles.add(Constants.TYPE_HUMANITY);
        mKeys.add(Constants.TYPE_HUMANITY_KEY);

        mTitles.add(Constants.TYPE_INTELLECTUALITY);
        mKeys.add(Constants.TYPE_INTELLECTUALITY_KEY);

        mTitles.add(Constants.TYPE_KEISURE);
        mKeys.add(Constants.TYPE_KEISURE_KEY);

        mTitles.add(Constants.TYPE_SPORTS);
        mKeys.add(Constants.TYPE_SPORTS_KEY);

        mTitles.add(Constants.TYPE_FINANCE);
        mKeys.add(Constants.TYPE_FINANCE_KEY);

        mTitles.add(Constants.TYPE_FASHION);
        mKeys.add(Constants.TYPE_FASHION_KEY);

        mTitles.add(Constants.TYPE_EMOTION);
        mKeys.add(Constants.TYPE_EMOTION_KEY);
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
        forumAlterTabHolder.switchCompat.setChecked(SPUtil.getBoolean(mKeys.get(i), true));
        forumAlterTabHolder.switchCompat.setText(mTitles.get(i));
        forumAlterTabHolder.position = i;
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    class ForumAlterTabHolder extends RecyclerView.ViewHolder {

        SwitchCompat switchCompat;
        int position;

        public ForumAlterTabHolder(@NonNull View itemView) {
            super(itemView);
            switchCompat = itemView.findViewById(R.id.switchWidget);
            switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    SPUtil.put(mKeys.get(position), isChecked);
                    if (mTabStatusListener != null) {
                        mTabStatusListener.statusChange();
                    }
                }
            });
        }
    }
}
