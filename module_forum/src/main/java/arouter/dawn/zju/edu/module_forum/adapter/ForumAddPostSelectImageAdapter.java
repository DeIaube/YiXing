package arouter.dawn.zju.edu.module_forum.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import arouter.dawn.zju.edu.module_forum.R;

public class ForumAddPostSelectImageAdapter extends RecyclerView.Adapter<ForumAddPostSelectImageAdapter.PostSelectImageHolder> {

    private List<String> mImages;
    private Context mContext;
    private SelectImageLisener mSelectImageLisener;

    public ForumAddPostSelectImageAdapter(List<String> mImages, Context mContext) {
        this.mImages = mImages;
        this.mContext = mContext;
    }

    public interface SelectImageLisener {
        void showImage(String url);
        void addImage();
        void deleteImage(int position);
    }

    public void setSelectImageLisener(SelectImageLisener selectImageLisener) {
        this.mSelectImageLisener = selectImageLisener;
    }

    public void update(List<String> images) {
        this.mImages = images;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostSelectImageHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_forum_add_post_image, viewGroup, false);
        return new PostSelectImageHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostSelectImageHolder holder, @SuppressLint("RecyclerView") final int i) {
        holder.closeIv.setVisibility(View.GONE);
        if (i == mImages.size()) {
            holder.closeIv.setVisibility(View.GONE);
            Picasso.with(mContext).load(R.drawable.forum_add_post_add_image).into(holder.imageView);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSelectImageLisener != null) {
                        mSelectImageLisener.addImage();
                    }
                }
            });
        } else {
            Picasso.with(mContext).load(new File(mImages.get(i))).into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSelectImageLisener != null) {
                        mSelectImageLisener.showImage(mImages.get(i));
                    }
                }
            });
            holder.closeIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSelectImageLisener != null) {
                        mSelectImageLisener.deleteImage(i);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mImages.size() + 1;
    }

    class PostSelectImageHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        ImageView closeIv;

        public PostSelectImageHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            closeIv = itemView.findViewById(R.id.image_close);
        }
    }
}
