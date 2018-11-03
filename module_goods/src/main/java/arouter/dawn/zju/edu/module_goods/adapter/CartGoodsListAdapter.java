package arouter.dawn.zju.edu.module_goods.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import arouter.dawn.zju.edu.module_nearby.R;
import baselib.bean.Goods;

public class CartGoodsListAdapter extends RecyclerView.Adapter<CartGoodsListAdapter.CartGoodsHolder> {

    Context context;
    List<Goods> goodsList;
    BitSet bitSet;

    public CartGoodsListAdapter(Context context, List<Goods> goodsList) {
        this.context = context;
        this.goodsList = goodsList;
        this.bitSet = new BitSet();
    }

    public void refresh(List<Goods> goodsList) {
        this.goodsList = goodsList;
        Collections.sort(goodsList, new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                return o1.getShop().compareTo(o2.getShop());
            }
        });
        notifyDataSetChanged();
    }

    @Override
    public CartGoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_cart_goods, parent, false);
        return new CartGoodsHolder(rootView);
    }

    @Override
    public void onBindViewHolder(CartGoodsHolder holder, int position) {
        Goods goods = goodsList.get(position);
        Picasso.with(context).load(goods.getPreview()).into(holder.goodsPreviewIv);
        holder.goodsTitleTv.setText(goods.getTitle());
        holder.goodsPriveTv.setText(String.format("￥%d", goods.getPrice()));
        constraintLayout(holder, position);
    }

    private void constraintLayout(CartGoodsHolder holder, int position) {
        if (position != 0 &&
                goodsList.get(position).getShop().equals(goodsList.get(position - 1).getShop())) {
            holder.topDivision.setVisibility(View.GONE);
        }
        if (position != 0 && goodsList.get(position).getShop().equals(goodsList.get(position - 1).getShop())) {
            holder.goods_top_layout.setVisibility(View.GONE);
        }
        if (position != goodsList.size() - 1 && goodsList.get(position).getShop().equals(goodsList.get(position + 1).getShop())) {
            holder.goods_bottom_layout.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    class CartGoodsHolder extends RecyclerView.ViewHolder{

        AppCompatCheckBox shopSelectCb;
        TextView shopTitleTv;
        AppCompatCheckBox goodsSelectCb;
        ImageView goodsPreviewIv;
        TextView goodsTitleTv;
        TextView goodsPriveTv;
        TextView goodsTotalPriveTv;
        View goods_top_layout;
        View goods_bottom_layout;
        View topDivision;

        public CartGoodsHolder(View itemView) {
            super(itemView);
            shopSelectCb = itemView.findViewById(R.id.cart_goods_shop_select);
            shopTitleTv = itemView.findViewById(R.id.cart_goods_shop_title);
            goodsSelectCb = itemView.findViewById(R.id.cart_goods_select);
            goodsPreviewIv = itemView.findViewById(R.id.cart_goods_preview);
            goodsTitleTv = itemView.findViewById(R.id.cart_goods_title);
            goodsPriveTv = itemView.findViewById(R.id.cart_goods_price);
            goodsTotalPriveTv = itemView.findViewById(R.id.cart_total_price_title);
            goods_top_layout = itemView.findViewById(R.id.cart_goods_top_layout);
            goods_bottom_layout = itemView.findViewById(R.id.cart_goods_bottom_layout);
            topDivision = itemView.findViewById(R.id.top_division);
        }
    }
}
