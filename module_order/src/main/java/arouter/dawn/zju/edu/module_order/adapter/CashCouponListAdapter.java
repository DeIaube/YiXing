package arouter.dawn.zju.edu.module_order.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.order.UserCashCoupon;
import arouter.dawn.zju.edu.module_order.R;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 代金券列表适配器
 */
public class CashCouponListAdapter extends RecyclerView.Adapter<CashCouponListAdapter.CashCouponListHolder> {

    private Context mContext;
    private List<UserCashCoupon> mUserCashCouponList;
    private CashCouponListClickListener mCashCouponListClickListener;

    public interface CashCouponListClickListener {
        void CashCouponClick(View v);
    }

    public void setCashCouponListClickListener(CashCouponListClickListener cashCouponListClickListener) {
        this.mCashCouponListClickListener = cashCouponListClickListener;
    }

    public CashCouponListAdapter(Context context, List<UserCashCoupon> userCashCouponList) {
        this.mContext = context;
        this.mUserCashCouponList = userCashCouponList;
    }

    @NonNull
    @Override
    public CashCouponListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_cash_coupon_list, viewGroup, false);
        return new CashCouponListHolder(rootView);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull CashCouponListHolder holder, int i) {
        UserCashCoupon userCashCoupon = mUserCashCouponList.get(i);
        holder.cashCouponTitleTv.setText(userCashCoupon.getCashCoupon().getTitle());
        holder.discountNumberTv.setText(String.valueOf(userCashCoupon.getCashCoupon().getDiscount()));
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        holder.cashCouponTimeTv.setText(String.format("%s至%s",
                sdf.format(userCashCoupon.getCashCoupon().getStartTime()),
                sdf.format(userCashCoupon.getCashCoupon().getEndTime())));
        if (userCashCoupon.getCashCoupon().getDoorsill() != 0) {
            holder.cashCouponTypeTv.setText(String.format("满%d元使用", userCashCoupon.getCashCoupon().getDoorsill()));
            holder.cashCouponCouponTypeTv.setText("满减券");
        }
        if (userCashCoupon.getCashCoupon().getEndTime().before(new Date())) {
            // 过期了
            holder.itemView.setBackground(mContext.getDrawable(R.drawable.cash_coupon_item_unable_used_bg));
            holder.cashCouponCouponTypeTv.setVisibility(View.GONE);
        } else {
            if (userCashCoupon.getUsed()) {
                // 优惠券未使用
                holder.itemView.setBackground(mContext.getDrawable(R.drawable.cash_coupon_item_able_used_bg));
            } else {
                // 优惠券已使用
                holder.itemView.setBackground(mContext.getDrawable(R.drawable.cash_coupon_item_unable_used_bg));
                holder.cashCouponCouponTypeTv.setVisibility(View.GONE);
            }
        }
    }

    public void refresh(List<UserCashCoupon> cashCouponList) {
        this.mUserCashCouponList = cashCouponList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mUserCashCouponList.size();
    }

    class CashCouponListHolder extends RecyclerView.ViewHolder {

        TextView discountNumberTv;
        TextView cashCouponTypeTv;
        TextView cashCouponCouponTypeTv;
        TextView cashCouponTitleTv;
        TextView cashCouponTimeTv;

        public CashCouponListHolder(@NonNull View itemView) {
            super(itemView);
            discountNumberTv = itemView.findViewById(R.id.discount_number);
            cashCouponTypeTv = itemView.findViewById(R.id.cash_coupon_type);
            cashCouponCouponTypeTv = itemView.findViewById(R.id.cash_coupon_coupon_type);
            cashCouponTitleTv = itemView.findViewById(R.id.cash_coupon_title);
            cashCouponTimeTv = itemView.findViewById(R.id.cash_coupon_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCashCouponListClickListener != null) {
                        mCashCouponListClickListener.CashCouponClick(v);
                    }
                }
            });
        }
    }
}
