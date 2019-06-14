package arouter.dawn.zju.edu.module_forum.ui.show_image;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import java.io.File;
import java.util.ArrayList;

import arouter.dawn.zju.edu.module_forum.R;
import arouter.dawn.zju.edu.module_forum.adapter.ForumShowImageAdapter;
import baselib.base.BaseActivity;
import baselib.constants.RouteConstants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 展示图片页面
 */
@Route(path = RouteConstants.AROUTER_FORUM_SHOW_IMAGE)
public class ForumShowImageActivity extends BaseActivity<ForumShowImageContract.Presenter>
        implements View.OnClickListener, ViewPager.OnPageChangeListener, ForumShowImageContract.View {

    @Autowired(name = RouteConstants.FORUM_SHOW_IMAGE_POSTION)
    int currentPosition;
    @Autowired(name = RouteConstants.FORUM_SHOW_IMAGE_BUNDLE)
    Bundle bundle;

    ArrayList<String> imageList;

    TextView positionTv;
    ViewPager imageViewPager;
    ImageView imageDetailIv;
    ForumShowImageAdapter adapter;

    @SuppressLint("DefaultLocale")
    @Override
    protected void initView() {
        positionTv = findViewById(R.id.show_image_position);
        imageViewPager = findViewById(R.id.show_image_view_pager);
        imageDetailIv = findViewById(R.id.show_image_detail);
        imageList = bundle.getStringArrayList(RouteConstants.FORUM_SHOW_IMAGE_LIST);

        findViewById(R.id.show_image_detail).setOnClickListener(this);

        adapter = new ForumShowImageAdapter(this, imageList);
        imageViewPager.setAdapter(adapter);

        imageViewPager.setOnPageChangeListener(this);
        imageViewPager.setOffscreenPageLimit(imageList.size());
        imageViewPager.setCurrentItem(currentPosition);

        positionTv.setText(String.format("%d/%d", currentPosition + 1, imageList.size()));
        
        // 如果当前图片数组在本地存在 显示详情按钮
        if (new File(imageList.get(0)).exists()) {
            imageDetailIv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_image;
    }

    @Override
    protected void bindPresenter() {

    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.show_image_detail) {
            File file = new File(imageList.get(currentPosition));
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;//这个参数设置为true才有效，
            BitmapFactory.decodeFile(imageList.get(currentPosition), options);//这里的bitmap是个空
            int outHeight =options.outHeight;
            int outWidth = options.outWidth;

            new AlertDialog.Builder(this)
                    .setMessage(String.format("大小:%d KB\n尺寸:%d x %d", (int)(file.length() / 1024), outWidth, outHeight))
                    .setPositiveButton(R.string.confirm, null)
                    .show();
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onPageSelected(int i) {
        positionTv.setText(String.format("%d/%d", i + 1, imageList.size()));
        currentPosition = i;
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
