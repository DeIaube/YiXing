package arouter.dawn.zju.edu.module_nearby.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.GoodsBean;
import arouter.dawn.zju.edu.module_nearby.R;
import baselib.base.BaseFragment;

public class GoodsListFragment extends BaseFragment {

    public void refresh(List<GoodsBean> goodsList) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goods_list;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected void initView(View view) {

    }
}
