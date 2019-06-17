package yixing.dawn.zju.edu.yixing.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.alibaba.android.arouter.launcher.ARouter;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.DailyRecommend;
import arouter.dawn.zju.edu.lib_net.bean.goods.Goods;
import baselib.base2.BaseViewModel;
import baselib.constants.RouteConstants;

public class HomeViewModel extends BaseViewModel<HomeFragment, HomeRepository> {

    MutableLiveData<List<Goods>> goodsListData = new MutableLiveData<>();
    MutableLiveData<List<String>> preViewListData = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application, HomeFragment view, HomeRepository repository) {
        super(application, view, repository);
    }

    public void setData() {
        AVQuery<DailyRecommend> dailyRecommendAVQuery = DailyRecommend.getQuery(DailyRecommend.class);
        dailyRecommendAVQuery
                .limit(5)
                .include("goods")
                .findInBackground(new FindCallback<DailyRecommend>() {
                    @Override
                    public void done(List<DailyRecommend> list, AVException e) {
                        if (e == null) {
                            List<Goods> goodsList = new ArrayList<>();
                            List<String> preViewList = new ArrayList<>();
                            for (DailyRecommend dailyRecommend : list) {
                                goodsList.add(dailyRecommend.getGoods());
                                preViewList.add(dailyRecommend.getPreview());
                            }
                            goodsListData.setValue(goodsList);
                            preViewListData.setValue(preViewList);
                        }
                    }
                });
    }

    public void search() {
        ARouter.getInstance().build(RouteConstants.AROUTER_GOODS_SEARCH).navigation();
    }

    public void notice() {
        ARouter.getInstance().build(RouteConstants.AROUTER_SETTING_NOTICE).navigation();
    }

    public void tabMenu() {
        ARouter.getInstance().build(RouteConstants.AROUTER_GOODS_NEARBY_ACTIVITY).navigation();
    }

    public void scan() {
        // 扫描二维码
    }

}
