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
import com.squareup.picasso.Picasso;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_forum.R;
import baselib.constants.RouteConstants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/27 14:18
 * @Description:
 * 我的关注用户列表适配器
 */
public class ForumFollowListAdapter extends RecyclerView.Adapter<ForumFollowListAdapter.ForumFollowListHolder> {

    private Context context;
    private List<User> userList;
    private FollowListClickListener mFollowListClickListener;

    public void setFollowListClickListener(FollowListClickListener followListClickListener) {
        this.mFollowListClickListener = followListClickListener;
    }

    public ForumFollowListAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
        this.mFollowListClickListener = new DefaultFollowListClickListener();
    }

    public void refresh(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public interface FollowListClickListener {
        void followClick(View v, User user);
    }

    @NonNull
    @Override
    public ForumFollowListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_forum_follow_list, viewGroup, false);
        return new ForumFollowListHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ForumFollowListHolder holder, int i) {
        holder.position = i;
        User user = userList.get(i);
        holder.picknameTv.setText(user.getPickName());
        Picasso.with(context).load(user.getPortrait()).into(holder.portraitIv);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class ForumFollowListHolder extends RecyclerView.ViewHolder {

        int position;
        TextView picknameTv;
        ImageView portraitIv;

        public ForumFollowListHolder(@NonNull View itemView) {
            super(itemView);
            picknameTv = itemView.findViewById(R.id.follow_pickname);
            portraitIv = itemView.findViewById(R.id.follow_portrait);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mFollowListClickListener != null) {
                        mFollowListClickListener.followClick(v, userList.get(position));
                    }
                }
            });
        }
    }

    class DefaultFollowListClickListener implements FollowListClickListener {

        @Override
        public void followClick(View v, User user) {
            ARouter.getInstance()
                    .build(RouteConstants.AROUTER_FORUM_USER_INFORMATION)
                    .withString(RouteConstants.FORUM_USER_INFORMATION_USER_ID, user.getObjectId())
                    .navigation();
        }
    }
}
