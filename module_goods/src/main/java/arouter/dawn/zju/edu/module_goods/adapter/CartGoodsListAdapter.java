package arouter.dawn.zju.edu.module_goods.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arouter.dawn.zju.edu.module_nearby.R;
import baselib.bean.Goods;

public class CartGoodsListAdapter extends RecyclerView.Adapter<CartGoodsListAdapter.CartGoodsHolder> {

    private Context mContext;
    private List<Goods> mGoodsList;
    // 每个商品选中状态
    private BitSet mGoodsSelector;
    // 每个商品对应的店铺选中状态
    private BitSet mShopSelector;
    // 购物车中商品状态改变的回调
    private CartStatusChangeListener cartStatusChangeListener;

    // 总金额
    private double mTotalPrice;
    // 每家店铺的总金额
    private Map<String, Double> mShopTotalPrice;

    public CartGoodsListAdapter(Context context, List<Goods> goodsList) {
        this.mContext = context;
        this.mGoodsList = goodsList;
        this.mGoodsSelector = new BitSet();
        this.mShopSelector = new BitSet();
        this.mShopTotalPrice = new HashMap<>();
    }

    public void setCartStatusChangeListener(CartStatusChangeListener cartStatusChangeListener) {
        this.cartStatusChangeListener = cartStatusChangeListener;
    }

    public void refresh(List<Goods> goodsList) {
        this.mGoodsList = goodsList;
        // 按照店铺名称排序
        Collections.sort(goodsList, new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                return o1.getShop().compareTo(o2.getShop());
            }
        });
        // 初始化 防止获取数据时出现空指针
        mShopTotalPrice.clear();
        for (Goods goods : goodsList) {
            mShopTotalPrice.put(goods.getShop(), 0.0);
        }
        notifyDataSetChanged();
    }

    public interface CartStatusChangeListener {
        void totalPriceChange(double price);
    }

    /**
     *
     * @return 购物车中被选中的商品列表
     */
    public List<Goods> getCheckedGoogsList() {
        List<Goods> result = new ArrayList<>();
        for (int i = 0; i < mGoodsList.size(); i++) {
            if (mGoodsSelector.get(i)) {
                result.add(mGoodsList.get(i));
            }
        }
        return result;
    }

    @Override
    public CartGoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_cart_goods, parent, false);
        return new CartGoodsHolder(rootView);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(final CartGoodsHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.setIsRecyclable(false);
        final Goods goods = mGoodsList.get(position);
        Picasso.with(mContext).load(goods.getPreview()).into(holder.goodsPreviewIv);
        holder.goodsTitleTv.setText(goods.getTitle());
        holder.goodsPriveTv.setText(String.format("￥%.2f", goods.getPrice()));
        holder.goodsSelectCb.setChecked(mGoodsSelector.get(position));
        holder.shopSelectCb.setChecked(mShopSelector.get(position));
        holder.goodsTotalPriveTv.setText(
                String.format("本仓总计(不含税): ￥%.2f", mShopTotalPrice.get(goods.getShop())));

        holder.goodsSelectCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                goodsStatusChange(position, isChecked);
            }
        });

        holder.shopSelectCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int count = 0;
                while (position + count < mGoodsList.size()) {
                    if (!mGoodsList.get(position + count).getShop().equals(goods.getShop())) {
                        break;
                    }
                    count++;
                }
                goodsStatusChange(position, count, isChecked);
            }
        });
        constraintLayout(holder, position);
        // 每次更新页面都要执行状态改变的回调
        if (cartStatusChangeListener != null) {
            cartStatusChangeListener.totalPriceChange(mTotalPrice);
        }
    }

    /**
     * 更新购物车商品状态
     * @param position 下标
     * @param status true:加入购物车 false:移除购物车
     */
    private void goodsStatusChange(int position, boolean status) {
        goodsStatusChange(position, 1, status);
    }

    /**
     * 批量更新购物车商品状态
     * @param position 下标
     * @param count 要修改的商品数量
     * @param status true:加入购物车 false:移除购物车
     */
    private void goodsStatusChange(int position, int count, boolean status) {
        for(int i = 0; i < count; i++) {
            if(mGoodsSelector.get(position + i) == status) {
                continue;
            }
            mGoodsSelector.set(position + i, status);
            mShopSelector.set(position + i, status);
            String shop = mGoodsList.get(position + i).getShop();
            if (status) {
                // 加入购物车
                mTotalPrice += mGoodsList.get(position + i).getPrice();
                mShopTotalPrice.put(shop,
                        mShopTotalPrice.get(shop) + mGoodsList.get(position + i).getPrice());
            } else {
                // 移除购物车
                mTotalPrice -= mGoodsList.get(position + i).getPrice();
                mShopTotalPrice.put(shop,
                        mShopTotalPrice.get(shop) - mGoodsList.get(position + i).getPrice());
            }
        }
        // 刷新页面
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }

    /**
     * 根据不同的状态使用不同的布局
     * 当前如果是此店的第一件商品则显示top_view
     * 当前如果是此店的最后一件商品则显示bottom_view
     * @param holder ViewHolder
     * @param position 下标
     */
    private void constraintLayout(CartGoodsHolder holder, int position) {
        if (position != 0 &&
                mGoodsList.get(position).getShop().equals(mGoodsList.get(position - 1).getShop())) {
            holder.topDivision.setVisibility(View.GONE);
        }
        if (position != 0 && mGoodsList.get(position).getShop().equals(mGoodsList.get(position - 1).getShop())) {
            holder.goods_top_layout.setVisibility(View.GONE);
        }
        if (position != mGoodsList.size() - 1 && mGoodsList.get(position).getShop().equals(mGoodsList.get(position + 1).getShop())) {
            holder.goods_bottom_layout.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mGoodsList.size();
    }

    public void totalGoodsStatusChange(boolean status) {
        goodsStatusChange(0, mGoodsList.size(), status);
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

        CartGoodsHolder(View itemView) {
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
