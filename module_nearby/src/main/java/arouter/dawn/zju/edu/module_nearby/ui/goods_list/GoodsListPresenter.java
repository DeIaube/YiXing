package arouter.dawn.zju.edu.module_nearby.ui.goods_list;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import arouter.dawn.zju.edu.lib_net.Api;
import arouter.dawn.zju.edu.lib_net.ApiRequest;
import arouter.dawn.zju.edu.lib_net.bean.GoodsBean;
import arouter.dawn.zju.edu.lib_net.bean.network.SearchGoodsRespense;
import arouter.dawn.zju.edu.module_nearby.adapter.NearbyPagerAdapter;
import arouter.dawn.zju.edu.module_nearby.config.Constants;
import arouter.dawn.zju.edu.module_nearby.ui.nearby.NearbyContract;
import baselib.base.BasePresenter;
import baselib.util.LogUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GoodsListPresenter extends BasePresenter<GoodsListContract.View> implements GoodsListContract.Presenter {

}
