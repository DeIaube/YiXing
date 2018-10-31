package arouter.dawn.zju.edu.module_nearby.ui.nearby;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zaaach.citypicker.CityPickerActivity;

import arouter.dawn.zju.edu.module_nearby.R;
import arouter.dawn.zju.edu.module_nearby.config.Constants;
import baselib.base.BaseFragment;
import baselib.util.SPUtil;

import static android.app.Activity.RESULT_OK;


public class NearbyFragment extends BaseFragment<NearbyContract.Presenter> implements NearbyContract.View {

    TextView searchTv;
    TextView loacationTv;
    ViewPager viewPager;
    TabLayout tabLayout;
    SwipeRefreshLayout refreshSrl;

    private String mLocation;

    private static final int REQUEST_CODE_PICK_CITY = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nearby;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new NearbyPresenter();
    }

    @Override
    protected void initView(View view) {
        searchTv = view.findViewById(R.id.near_search);
        loacationTv = view.findViewById(R.id.near_location);
        viewPager = view.findViewById(R.id.neary_view_pager);
        tabLayout = view.findViewById(R.id.neary_tab_layout);
        refreshSrl = view.findViewById(R.id.refresh_layout);

        mLocation = SPUtil.getString(Constants.LAST_LOCATION, Constants.DEFAULT_LOCATION);

        refreshSrl.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        refreshSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refresh();
            }
        });

        loacationTv.setText(mLocation);

        loacationTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(), CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);
            }
        });

        searchTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(baselib.config.Constants.AROUTER_NEARBY_SEARCH).navigation();
            }
        });

        mPresenter.bindViewPager(getChildFragmentManager(), viewPager, tabLayout);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK){
            if (data != null){
                mLocation = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                loacationTv.setText(mLocation);
                SPUtil.put(Constants.LAST_LOCATION, mLocation);
            }
        }
    }

    @Override
    public void showSwipeRefreshLayout() {
        refreshSrl.setRefreshing(true);
    }

    @Override
    public void hideSwipeRefreshLayout() {
        refreshSrl.setRefreshing(false);
    }
}