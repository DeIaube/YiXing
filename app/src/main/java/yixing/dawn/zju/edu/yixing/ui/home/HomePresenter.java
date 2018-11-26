package yixing.dawn.zju.edu.yixing.ui.home;


import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.DailyRecommend;
import arouter.dawn.zju.edu.lib_net.bean.goods.Goods;
import baselib.base.BasePresenter;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 主页面中首页
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    private static final String TAG = "HomePresenter";

    @Override
    public void refreshHomeView() {
        AVQuery<DailyRecommend> dailyRecommendAVQuery = DailyRecommend.getQuery(DailyRecommend.class);
        dailyRecommendAVQuery
                .limit(5)
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
                    mView.refreshHomeView(goodsList, preViewList);
                }
            }
        });
    }
}
