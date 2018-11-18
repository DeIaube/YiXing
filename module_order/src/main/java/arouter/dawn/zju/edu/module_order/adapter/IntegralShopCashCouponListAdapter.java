package arouter.dawn.zju.edu.module_order.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.order.CashCoupon;
import arouter.dawn.zju.edu.module_order.R;

public class IntegralShopCashCouponListAdapter extends RecyclerView.Adapter<IntegralShopCashCouponListAdapter.IntegralShopCashCouponListHolder> {

    private List<CashCoupon> mCashCoupons;
    private List<CashCoupon> mUserCashCoupons;
    private Context mContext;

    public IntegralShopCashCouponListAdapter(Context context, List<CashCoupon> cashCoupons,
                                             List<CashCoupon> userCashCoupons) {
        this.mContext = context;
        this.mCashCoupons = cashCoupons;
        this.mUserCashCoupons = userCashCoupons;
    }

    public void refresh(List<CashCoupon> cashCoupons, List<CashCoupon> userCashCoupons) {
        this.mCashCoupons = cashCoupons;
        this.mUserCashCoupons = userCashCoupons;
        notifyDataSetChanged();
        for (CashCoupon mCashCoupon : mCashCoupons) {
            Log.e("aaa", mCashCoupon.getTitle());
        }
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
        holder.cashCouponIntegralTv.setText(String.format("%d分", cashCoupon.getIntegral()));
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        holder.cashCouponTimeTv.setText(String.format("%s至%s",
                sdf.format(cashCoupon.getStartTime()), sdf.format(cashCoupon.getEndTime())));
        if (cashCoupon.getDoorsill() == 0) {
            holder.cashCouponDoorsillInformationTv.setText("无门槛");
        } else {
            holder.cashCouponDoorsillInformationTv.setText(String.format("满%d可用", (int)cashCoupon.getDoorsill()));
        }
        if (mUserCashCoupons.contains(cashCoupon)) {
            holder.cashCouponBuyBtn.setTextColor(mContext.getColor(R.color.colorPrimary));
            holder.cashCouponBuyBtn.setBackgroundResource(R.drawable.integral_shop_cash_coupon_unable_buy_btn_bg);
            holder.cashCouponBuyBtn.setText("去使用");
        } else {
            holder.cashCouponBuyBtn.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.cashCouponBuyBtn.setBackground(mContext.getDrawable(R.drawable.integral_shop_cash_coupon_able_buy_btn_bg));
            holder.cashCouponBuyBtn.setText("立即抢购");
        }
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
        TextView cashCouponTimeTv;

        public IntegralShopCashCouponListHolder(@NonNull View itemView) {
            super(itemView);
            cashCouponTitleTv = itemView.findViewById(R.id.cash_coupon_title);
            cashCouponDiscountTv = itemView.findViewById(R.id.cash_coupon_discount);
            cashCouponDoorsillInformationTv = itemView.findViewById(R.id.cash_coupon_doorsill_information);
            cashCouponBuyBtn = itemView.findViewById(R.id.cash_coupon_buy);
            cashCouponIntegralTv = itemView.findViewById(R.id.cash_coupon_integral);
            cashCouponTimeTv = itemView.findViewById(R.id.cash_coupon_time);
        }
    }
}
