package arouter.dawn.zju.edu.module_nearby.ui;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_nearby.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_NEARBY_SEARCH)
public class SearchActivity extends BaseActivity {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void bindPresenter() {

    }

}
