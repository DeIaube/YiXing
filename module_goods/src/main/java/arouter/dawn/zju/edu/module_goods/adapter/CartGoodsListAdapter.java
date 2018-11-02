package arouter.dawn.zju.edu.module_goods.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import baselib.bean.Goods;

public class CartGoodsListAdapter extends RecyclerView.Adapter<CartGoodsListAdapter.CartGoodsHolder> {

    Context context;
    List<Goods> goodsList;

    public CartGoodsListAdapter(Context context, List<Goods> goodsList) {
        this.context = context;
        this.goodsList = goodsList;
    }

    public void refresh(List<Goods> goodsList) {
        this.goodsList = goodsList;
        notifyDataSetChanged();
    }

    @Override
    public CartGoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CartGoodsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    class CartGoodsHolder extends RecyclerView.ViewHolder{

        public CartGoodsHolder(View itemView) {
            super(itemView);
        }
    }
}
