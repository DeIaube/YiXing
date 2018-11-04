package arouter.dawn.zju.edu.module_forum;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import baselib.base.BaseFragment;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_FORUM_FORUM)
public class ForumFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_forum;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected void initView(View view) {

    }
}
