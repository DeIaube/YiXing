package arouter.dawn.zju.edu.module_order.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.order.CashCoupon;
import arouter.dawn.zju.edu.module_order.R;

public class IntegralShopCashCouponListAdapter extends RecyclerView.Adapter<IntegralShopCashCouponListAdapter.IntegralShopCashCouponListHolder> {

    private List<CashCoupon> mCashCoupons;
    private Context mContext;

    public IntegralShopCashCouponListAdapter(List<CashCoupon> cashCoupons, Context context) {
        this.mCashCoupons = cashCoupons;
        this.mContext = context;
    }

    public void refresh(List<CashCoupon> cashCoupons) {
        this.mCashCoupons = cashCoupons;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IntegralShopCashCouponListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_integral_shop_cash_coupon, viewGroup, false);
        return new IntegralShopCashCouponListHolder(rootView);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull IntegralShopCashCouponListHolder holder, int i) {
        CashCoupon cashCoupon = mCashCoupons.get(i);
        holder.cashCouponTitleTv.setText(cashCoupon.getTitle());
        holder.cashCouponDiscountTv.setText(String.valueOf(cashCoupon.getDiscount()));
        holder.cashCouponDoorsillInformationTv.setText(String.format("满%d可用", (int)cashCoupon.getDoorsill()));
        holder.cashCouponIntegralTv.setText(String.format("%d分", cashCoupon.getIntegral()));
    }

    @Override
    public int getItemCount() {
        return mCashCoupons.size();
    }

    class IntegralShopCashCouponListHolder extends RecyclerView.ViewHolder {

        TextView cashCouponTitleTv;
        TextView cashCouponDiscountTv;
        TextView cashCouponDoorsillInformationTv;
        Button cashCouponBuyBtn;
        TextView cashCouponIntegralTv;

        public IntegralShopCashCouponListHolder(@NonNull View itemView) {
            super(itemView);
            cashCouponTitleTv = itemView.findViewById(R.id.cash_coupon_title);
            cashCouponDiscountTv = itemView.findViewById(R.id.cash_coupon_discount);
            cashCouponDoorsillInformationTv = itemView.findViewById(R.id.cash_coupon_doorsill_information);
            cashCouponBuyBtn = itemView.findViewById(R.id.cash_coupon_buy);
            cashCouponIntegralTv = itemView.findViewById(R.id.cash_coupon_integral);
        }
    }
}
