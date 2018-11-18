package arouter.dawn.zju.edu.module_order.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.order.UserCashCoupon;
import arouter.dawn.zju.edu.module_order.R;

public class CashCouponListAdapter extends RecyclerView.Adapter<CashCouponListAdapter.CashCouponListHolder> {

    private Context mContext;
    private List<UserCashCoupon> mUserCashCouponList;

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

    @Override
    public void onBindViewHolder(@NonNull CashCouponListHolder cashCouponListHolder, int i) {

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

        public CashCouponListHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
