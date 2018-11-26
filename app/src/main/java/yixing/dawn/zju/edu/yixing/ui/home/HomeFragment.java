package yixing.dawn.zju.edu.yixing.ui.home;


import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.youth.banner.Banner;

import baselib.base.BaseFragment;
import baselib.config.Constants;
import yixing.dawn.zju.edu.yixing.R;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class HomeFragment extends BaseFragment<HomeContract.Presenter> implements HomeContract.View, View.OnClickListener {

    Banner banner;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new HomePresenter();
    }

    @Override
    protected void initView(View view) {
        banner = view.findViewById(R.id.home_banner);

        view.findViewById(R.id.home_scan).setOnClickListener(this);
        view.findViewById(R.id.home_search).setOnClickListener(this);
        view.findViewById(R.id.home_notice).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.home_scan) {
            // todo 扫描二维码
        } else if (id == R.id.home_search) {
            ARouter.getInstance().build(Constants.AROUTER_GOODS_SEARCH).navigation();
        } else if (id == R.id.home_notice) {
            ARouter.getInstance().build(Constants.AROUTER_SETTING_NOTICE).navigation();
        }
    }
}
