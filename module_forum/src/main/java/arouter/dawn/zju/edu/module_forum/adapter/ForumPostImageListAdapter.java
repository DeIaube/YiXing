package arouter.dawn.zju.edu.module_forum.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.module_forum.R;
import baselib.config.Constants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 帖子中图片列表的适配器
 */
public class ForumPostImageListAdapter extends RecyclerView.Adapter<ForumPostImageListAdapter.PostImageListHolder> {

    private Context mContext;
    private ArrayList<String> mImageList;

    public ForumPostImageListAdapter(Context context, List<String> urls) {
        this.mContext = context;
        this.mImageList = new ArrayList<>(urls);
    }

    @NonNull
    @Override
    public PostImageListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_forum_post_list_image, viewGroup, false);
        return new PostImageListHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostImageListHolder holder, int i) {
        holder.position = i;
        Picasso.with(mContext).load(mImageList.get(i)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    class PostImageListHolder extends RecyclerView.ViewHolder {

        int position;
        ImageView imageView;

        public PostImageListHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList(Constants.FORUM_SHOW_IMAGE_LIST, (mImageList));
                    ARouter.getInstance().build(Constants.AROUTER_FORUM_SHOW_IMAGE).
                            withBundle(Constants.FORUM_SHOW_IMAGE_BUNDLE, bundle).
                            withInt(Constants.FORUM_SHOW_IMAGE_POSTION, position).navigation();
                }
            });
        }
    }
}
