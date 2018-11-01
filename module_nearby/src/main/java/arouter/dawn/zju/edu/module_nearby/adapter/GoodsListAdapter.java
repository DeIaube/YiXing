package arouter.dawn.zju.edu.module_nearby.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import arouter.dawn.zju.edu.module_nearby.R;
import baselib.bean.AVGoods;

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.GoodsListHolder> {

    List<AVGoods> mGoodsList;
    Context mContext;
    OnGoodsClickListener onGoodsClickListener;

    public interface OnGoodsClickListener {
        void onGoodsClick(View v, AVGoods goods);
    }

    public GoodsListAdapter(List<AVGoods> goodsList, Context context) {
        this.mGoodsList = goodsList;
        this.mContext = context;
    }

    public void refresh(List<AVGoods> goodsList) {
        this.mGoodsList = goodsList;
        notifyItemRangeChanged(0, goodsList.size());
    }

    public void setOnGoodsClickListener(OnGoodsClickListener onGoodsClickListener) {
        this.onGoodsClickListener = onGoodsClickListener;
    }

    @Override
    public GoodsListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_goods, parent, false);
        return new GoodsListHolder(rootView);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(GoodsListHolder holder, int position) {
        final AVGoods goods = mGoodsList.get(position);
        holder.titleTv.setText(goods.getTitle());
        holder.locationTv.setText(String.format("%s %s", goods.getCity(), goods.getRegion()));
        holder.priceTv.setText(String.format("￥%d", goods.getPrice()));
        Picasso.with(mContext).load(goods.getPreview()).into(holder.preIv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onGoodsClickListener != null) {
                    onGoodsClickListener.onGoodsClick(v, goods);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGoodsList.size();
    }

    class GoodsListHolder extends RecyclerView.ViewHolder {

        ImageView preIv;
        TextView titleTv;
        TextView locationTv;
        TextView priceTv;
        TextView praiseTv;

        public GoodsListHolder(View itemView) {
            super(itemView);
            preIv = itemView.findViewById(R.id.goods_pre);
            titleTv = itemView.findViewById(R.id.goods_title);
            locationTv = itemView.findViewById(R.id.goods_location);
            priceTv = itemView.findViewById(R.id.goods_price);
            praiseTv = itemView.findViewById(R.id.goods_praise);

        }
    }
}
