package arouter.dawn.zju.edu.module_order.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import arouter.dawn.zju.edu.module_order.R;
import arouter.dawn.zju.edu.module_order.config.Constants;
import baselib.bean.OrderBean;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderListHolder> {

    private Context mContext;
    private List<OrderBean> mOrders;

    public OrderListAdapter(Context context, List<OrderBean> orders) {
        this.mContext = context;
        this.mOrders = orders;
    }

    @Override
    public OrderListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).
                inflate(R.layout.item_order_list, parent, false);
        return new OrderListHolder(rootView);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(OrderListHolder holder, int position) {
        OrderBean order = mOrders.get(position);
        initViewByType(holder, order.getType());
        holder.createTimeTv.setText(order.getCreate_time());
        holder.payNumberTv.setText(String.format("￥ %d", order.getPay()));
        holder.realPayNumberTv.setText(String.format("￥ %d", order.getPay()));
        holder.goodsTitleTv.setText(order.getGoods_title());
        holder.typeTv.setText(order.getType());
        Picasso.with(mContext).load(order.getGoods_preview()).into(holder.goodsPreviewIv);
    }

    private void initViewByType(OrderListHolder holder, String type) {
        if (type.equals(Constants.ORDER_TYPE_PAYMENT)) {
            // 待付款 隐藏申请售后
            holder.customerServiceBtn.setVisibility(View.GONE);
        } else if (type.equals(Constants.ORDER_TYPE_CANCEL)) {
            // 已取消 隐藏 申请售后 取消订单 立即付款
            holder.buttonLayoutLv.setVisibility(View.GONE);
        } else if (type.equals(Constants.ORDER_TYPE_COMPLETE)) {
            // 已完成 隐藏 取消订单 立即付款
            holder.cancelOrderBtn.setVisibility(View.GONE);
            holder.payOrderBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }

    static class OrderListHolder extends RecyclerView.ViewHolder{

        TextView createTimeTv;
        TextView typeTv;
        ImageView goodsPreviewIv;
        TextView goodsTitleTv;
        TextView payNumberTv;
        TextView realPayNumberTv;
        Button cancelOrderBtn;
        Button payOrderBtn;
        Button customerServiceBtn;
        LinearLayout buttonLayoutLv;

        OrderListHolder(View itemView) {
            super(itemView);
            createTimeTv = itemView.findViewById(R.id.order_create_time);
            typeTv = itemView.findViewById(R.id.order_type);
            goodsPreviewIv = itemView.findViewById(R.id.goods_preview);
            goodsTitleTv = itemView.findViewById(R.id.goods_title);
            payNumberTv = itemView.findViewById(R.id.pay_number);
            realPayNumberTv = itemView.findViewById(R.id.real_pay_number);
            cancelOrderBtn = itemView.findViewById(R.id.cancel_order);
            payOrderBtn = itemView.findViewById(R.id.pay_order);
            customerServiceBtn = itemView.findViewById(R.id.customer_service);
            buttonLayoutLv = itemView.findViewById(R.id.button_layout);
        }
    }
}
