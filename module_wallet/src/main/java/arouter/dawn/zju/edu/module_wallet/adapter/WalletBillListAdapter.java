package arouter.dawn.zju.edu.module_wallet.adapter;

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

import arouter.dawn.zju.edu.lib_net.bean.wallet.Bill;
import arouter.dawn.zju.edu.module_wallet.R;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 账单列表适配器
 */
public class WalletBillListAdapter extends RecyclerView.Adapter<WalletBillListAdapter.WalletBillListholder> {

    private Context mContext;
    private List<Bill> mBillList;
    private WalletBillListClickListener mWalletBillListClickListener;

    public void setWalletBillListClickListener(WalletBillListClickListener walletBillListClickListener) {
        this.mWalletBillListClickListener = walletBillListClickListener;
    }

    public interface WalletBillListClickListener {
        void billClick(Bill bill);
    }

    public WalletBillListAdapter(Context context, List<Bill> billList) {
        this.mContext = context;
        this.mBillList = billList;
    }

    public void refresh(List<Bill> billList) {
        this.mBillList = billList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WalletBillListholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_wallet_bill_list, viewGroup, false);
        return new WalletBillListholder(rootView);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull WalletBillListholder holder, int i) {
        holder.position = i;
        Bill bill = mBillList.get(i);
        holder.billDealTv.setText(bill.getDeal());
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        holder.billTimeTv.setText(sdf.format(bill.getUpdatedAt()));
        if (bill.getType()) {
            holder.billAmountTv.setText(String.format("+%.2f", bill.getAmount()));
            holder.billAmountTv.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        } else {
            holder.billAmountTv.setText(String.format("-%.2f", bill.getAmount()));
            holder.billAmountTv.setTextColor(mContext.getResources().getColor(R.color.black));
        }
    }

    @Override
    public int getItemCount() {
        return mBillList.size();
    }

    class WalletBillListholder extends RecyclerView.ViewHolder {

        int position;
        TextView billDealTv;
        TextView billTimeTv;
        TextView billAmountTv;

        public WalletBillListholder(@NonNull View itemView) {
            super(itemView);
            billDealTv = itemView.findViewById(R.id.bill_deal);
            billTimeTv = itemView.findViewById(R.id.bill_time);
            billAmountTv = itemView.findViewById(R.id.bill_amount);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mWalletBillListClickListener != null) {
                        mWalletBillListClickListener.billClick(mBillList.get(position));
                    }
                }
            });
        }
    }
}
