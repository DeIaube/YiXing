package arouter.dawn.zju.edu.module_forum.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import arouter.dawn.zju.edu.module_forum.R;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 展示图片的适配器
 */
public class ForumShowImageAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> mImageList;

    public ForumShowImageAdapter(Context mContext, List<String> mImageList) {
        this.mContext = mContext;
        this.mImageList = mImageList;
    }

    @Override
    public int getCount() {
        return mImageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_show_image, null);
        ImageView imageView = rootView.findViewById(R.id.image);
        File imageFile = new File(mImageList.get(position));
        if (imageFile.exists()) {
            Picasso.with(mContext).load(imageFile).into(imageView);
        } else {
            Picasso.with(mContext).load(mImageList.get(position)).into(imageView);
        }
        container.addView(rootView);
        return rootView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
