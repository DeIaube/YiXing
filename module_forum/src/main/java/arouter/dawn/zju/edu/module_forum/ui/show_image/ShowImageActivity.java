package arouter.dawn.zju.edu.module_forum.ui.show_image;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.List;

import arouter.dawn.zju.edu.module_forum.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_FORUM_SHOW_IMAGE)
public class ShowImageActivity extends BaseActivity {

    @Autowired(name = Constants.FORUM_SHOW_IMAGE_POSTION)
    private int mCurrentPosition;
    @Autowired(name = Constants.FORUM_SHOW_IMAGE_LIST)
    private List<String> mImageList;

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_image;
    }

    @Override
    protected void bindPresenter() {

    }

}
