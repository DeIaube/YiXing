package arouter.dawn.zju.edu.module_pay.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.order.CashCoupon;
import arouter.dawn.zju.edu.module_pay.R;

public class PayCashCouponListAdapter extends RecyclerView.Adapter<PayCashCouponListAdapter.PayCashCouponListHolder> {

    private List<CashCoupon> mCashCouponList;
    private Context mContext;
    private OnCashCouponClickListener mOnCashCouponClickListener;

    public PayCashCouponListAdapter(List<CashCoupon> cashCouponList, Context context) {
        this.mCashCouponList = cashCouponList;
        this.mContext = context;
    }

    public interface OnCashCouponClickListener {
        void selectCashCoupon(View v, CashCoupon cashCoupon);
    }

    public void setOnCashCouponClickListener(OnCashCouponClickListener onCashCouponClickListener) {
        this.mOnCashCouponClickListener = onCashCouponClickListener;
    }

    public void refresh(List<CashCoupon> cashCouponList) {
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
    }

    @Override
    public int getItemCount() {
        return mCashCouponList.size();
    }

    class PayCashCouponListHolder extends RecyclerView.ViewHolder {

        int position;

        public PayCashCouponListHolder(@NonNull View itemView) {
            super(itemView);
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
