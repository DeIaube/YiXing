package arouter.dawn.zju.edu.module_goods.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class PicassoUrlImageLeader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Picasso.with(context).load((String) path).into(imageView);
    }

}
