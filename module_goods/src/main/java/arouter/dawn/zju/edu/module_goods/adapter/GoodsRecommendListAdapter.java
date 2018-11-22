package arouter.dawn.zju.edu.module_goods.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.goods.Goods;
import arouter.dawn.zju.edu.module_nearby.R;

/**
 * @Auther: Dawn
 * @Date: 2018/11/23 00:13
 * @Description:
 */
public class GoodsRecommendListAdapter extends RecyclerView.Adapter<GoodsRecommendListAdapter.GoodsRecommendListHolder> {

    private List<Goods> mGoodsList;
    private Context mContext;

    public GoodsRecommendListAdapter(List<Goods> goodsList, Context context) {
        this.mGoodsList = goodsList;
        this.mContext = context;
    }

    public void refresh(List<Goods> goodsList) {
        this.mGoodsList = goodsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GoodsRecommendListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_goods_recommend, viewGroup, false);
        return new GoodsRecommendListHolder(rootView);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull GoodsRecommendListHolder holder, int i) {
        Goods goods = mGoodsList.get(i);
        holder.titleTv.setText(goods.getTitle());
        holder.priceTv.setText(String.format("￥%.0f", goods.getPrice()));
        Picasso.with(mContext).load(goods.getPreview()).into(holder.preIv);
    }

    @Override
    public int getItemCount() {
        return mGoodsList.size();
    }

    class GoodsRecommendListHolder extends RecyclerView.ViewHolder {

        TextView titleTv;
        TextView priceTv;
        Button buyBtn;
        ImageView preIv;

        public GoodsRecommendListHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.recommend_title);
            priceTv = itemView.findViewById(R.id.recommend_price);
            buyBtn = itemView.findViewById(R.id.recommend_buy);
            preIv = itemView.findViewById(R.id.recommend_pre);
        }
    }
}
