package yixing.dawn.zju.edu.yixing.ui.home;


import android.view.View;

import baselib.base.BaseFragment;
import yixing.dawn.zju.edu.yixing.R;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class HomeFragment extends BaseFragment<HomeContract.Presenter> implements HomeContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected void initView(View view) {

    }
}
