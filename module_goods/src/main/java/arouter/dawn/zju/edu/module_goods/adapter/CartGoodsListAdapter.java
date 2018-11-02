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

import java.util.List;

import arouter.dawn.zju.edu.module_nearby.R;
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
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_cart_goods, parent, false);
        return new CartGoodsHolder(rootView);
    }

    @Override
    public void onBindViewHolder(CartGoodsHolder holder, int position) {
//        Goods goods = goodsList.get(position);
//        Picasso.with(context).load(goods.getPreview()).into(holder.previewIv);
//        holder.titleTv.setText(goods.getTitle());
//        holder.priveTv.setText(String.format("￥%d", goods.getPrice()));
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    class CartGoodsHolder extends RecyclerView.ViewHolder{

        AppCompatCheckBox selectCb;
        ImageView previewIv;
        TextView titleTv;
        TextView priveTv;

        public CartGoodsHolder(View itemView) {
            super(itemView);
//            selectCb = itemView.findViewById(R.id.select);
//            previewIv = itemView.findViewById(R.id.preview);
//            titleTv = itemView.findViewById(R.id.title);
//            priveTv = itemView.findViewById(R.id.price);
        }
    }
}
