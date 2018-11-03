package arouter.dawn.zju.edu.module_goods.ui.search;

import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.module_nearby.R;
import arouter.dawn.zju.edu.module_goods.adapter.GoodsListAdapter;
import baselib.base.BaseActivity;
import arouter.dawn.zju.edu.lib_net.bean.Goods;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_GOODS_SEARCH)
public class SearchActivity extends BaseActivity<SearchContract.Presenter> implements SearchContract.View, GoodsListAdapter.OnGoodsClickListener {

    RecyclerView searchResultRv;
    GoodsListAdapter adapter;

    @Override
    protected void initView() {
        searchResultRv = findViewById(R.id.search_result_list);

        searchResultRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GoodsListAdapter(new ArrayList<Goods>(), this);
        adapter.setOnGoodsClickListener(this);
        searchResultRv.setAdapter(adapter);
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_toolbar, menu);
        MenuItem item = menu.findItem(R.id.toolbar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        //进去打开搜索栏
        searchView.setIconified(false);
        //设置输入文本的EditText
        SearchView.SearchAutoComplete et = searchView.findViewById(R.id.search_src_text);
        //设置搜索栏的默认提示，作用和setQueryHint相同
        et.setHint("输入商品名或首字母");
        //设置提示文本的颜色
        et.setHintTextColor(Color.WHITE);
        //设置输入文本的颜色
        et.setTextColor(Color.WHITE);
        //设置提交按钮是否可见
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mPresenter.search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new SearchPresenter();
    }

    @Override
    public void refresh(List<Goods> goodsList) {
        adapter.refresh(goodsList);
    }

    @Override
    public void onGoodsClick(View v, Goods goods) {
        // todo 商品点击回调
    }
}
