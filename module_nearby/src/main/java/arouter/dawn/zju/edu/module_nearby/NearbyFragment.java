package arouter.dawn.zju.edu.module_nearby;


import android.app.ActionBar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import baselib.base.BaseActivity;
import baselib.base.BaseFragment;


public class NearbyFragment extends BaseFragment {

    TextView searchTv;
    ImageView locationTv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nearby;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected void initView(View view) {
        searchTv = view.findViewById(R.id.near_search);
        locationTv = view.findViewById(R.id.near_location);
    }
}