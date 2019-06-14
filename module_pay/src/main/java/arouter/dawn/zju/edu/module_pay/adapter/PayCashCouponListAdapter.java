package arouter.dawn.zju.edu.module_pay.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.order.CashCoupon;
import arouter.dawn.zju.edu.lib_net.bean.order.UserCashCoupon;
import arouter.dawn.zju.edu.module_pay.R;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 代金券列表适配器
 */
public class PayCashCouponListAdapter extends RecyclerView.Adapter<PayCashCouponListAdapter.PayCashCouponListHolder> {

    private List<UserCashCoupon> mCashCouponList;
    private Context mContext;
    private OnCashCouponClickListener mOnCashCouponClickListener;

    public PayCashCouponListAdapter(List<UserCashCoupon> cashCouponList, Context context) {
        this.mCashCouponList = cashCouponList;
        this.mContext = context;
    }

    public interface OnCashCouponClickListener {
        void selectCashCoupon(View v, UserCashCoupon userCashCoupon);
    }

    public void setOnCashCouponClickListener(OnCashCouponClickListener onCashCouponClickListener) {
        this.mOnCashCouponClickListener = onCashCouponClickListener;
    }

    public void refresh(List<UserCashCoupon> cashCouponList) {
        this.mCashCouponList = cashCouponList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PayCashCouponListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_pay_cash_coupon, viewGroup, false);
        return new PayCashCouponListHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull PayCashCouponListHolder holder, int i) {
        holder.position = i;
        CashCoupon cashCoupon = mCashCouponList.get(i).getCashCoupon();
        holder.discountNumberTv.setText(String.valueOf(cashCoupon.getDiscount()));
        holder.cashCouponTitleTv.setText(cashCoupon.getTitle());
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        holder.cashCouponTimeTv.setText(String.format("%s至%s",
                sdf.format(cashCoupon.getStartTime()),
                sdf.format(cashCoupon.getEndTime())));
        holder.cashCouponCouponTypeTv.setText(cashCoupon.getDoorsill() == 0 ? "现金券" : "满减券");
    }

    @Override
    public int getItemCount() {
        return mCashCouponList.size();
    }

    class PayCashCouponListHolder extends RecyclerView.ViewHolder {

        int position;

        TextView discountNumberTv;
        TextView cashCouponCouponTypeTv;
        TextView cashCouponTitleTv;
        TextView cashCouponTimeTv;


        public PayCashCouponListHolder(@NonNull View itemView) {
            super(itemView);

            discountNumberTv = itemView.findViewById(R.id.discount_number);
            cashCouponCouponTypeTv = itemView.findViewById(R.id.cash_coupon_coupon_type);
            cashCouponTitleTv = itemView.findViewById(R.id.cash_coupon_title);
            cashCouponTimeTv = itemView.findViewById(R.id.cash_coupon_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnCashCouponClickListener != null) {
                        mOnCashCouponClickListener.selectCashCoupon(v, mCashCouponList.get(position));
                    }
                }
            });
        }
    }
}
