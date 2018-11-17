package arouter.dawn.zju.edu.module_goods.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

import arouter.dawn.zju.edu.module_nearby.R;

public class PicassoUrlImageLeader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Picasso.with(context).load((String) path).into(imageView);
    }

}
