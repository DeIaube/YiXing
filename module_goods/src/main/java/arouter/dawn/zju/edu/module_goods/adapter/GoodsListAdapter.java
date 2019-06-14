package arouter.dawn.zju.edu.module_goods.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import arouter.dawn.zju.edu.module_nearby.R;
import arouter.dawn.zju.edu.lib_net.bean.goods.Goods;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 商品列表适配器
 */
public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.GoodsListHolder> {

    private List<Goods> mGoodsList;
    private Context mContext;
    private OnGoodsClickListener onGoodsClickListener;

    public interface OnGoodsClickListener {
        void onGoodsClick(View v, Goods goods);
    }

    public GoodsListAdapter(List<Goods> goodsList, Context context) {
        this.mGoodsList = goodsList;
        this.mContext = context;
    }

    public void refresh(List<Goods> goodsList) {
        this.mGoodsList = goodsList;
        if (goodsList.isEmpty()) {
            notifyDataSetChanged();
            return;
        }
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
        final Goods goods = mGoodsList.get(position);
        holder.titleTv.setText(goods.getTitle());
        holder.locationTv.setText(String.format("%s %s", goods.getCity(), goods.getRegion()));
        holder.priceTv.setText(String.format("￥%.0f", goods.getPrice()));
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
