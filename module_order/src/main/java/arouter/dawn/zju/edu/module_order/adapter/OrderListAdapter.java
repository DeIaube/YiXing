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

import java.text.SimpleDateFormat;
import java.util.List;

import arouter.dawn.zju.edu.module_order.R;
import arouter.dawn.zju.edu.module_order.config.Constants;
import arouter.dawn.zju.edu.lib_net.bean.order.Order;
import baselib.App;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 订单列表适配器
 */
public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderListHolder> {

    private Context mContext;
    private List<Order> mOrders;
    private OnOrderListClickListener mOrderListClickListener;

    public interface OnOrderListClickListener {
        void cancelOrderClickListener(final View view, final Order orderBean);
        void payOrderClickListener(final View view, final Order orderBean);
        void orderEvaluateClickListener(final View view, final Order orderBean);
        void orderDetailClickListener(final View view, final Order orderBean);
        void orderRefundClickListener(final View view, final Order orderBean);
    }

    public void setmOrderListClickListener(OnOrderListClickListener mOrderListClickListener) {
        this.mOrderListClickListener = mOrderListClickListener;
    }

    public OrderListAdapter(Context context, List<Order> orders) {
        this.mContext = context;
        this.mOrders = orders;
    }

    @Override
    public OrderListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).
                inflate(R.layout.item_order_list, parent, false);
        return new OrderListHolder(rootView);
    }

    @SuppressLint({"DefaultLocale", "SimpleDateFormat"})
    @Override
    public void onBindViewHolder(OrderListHolder holder, int position) {
        holder.setIsRecyclable(false);
        Order order = mOrders.get(position);
        initViewByType(holder, order.getType());
        holder.createTimeTv.setText(new SimpleDateFormat("yyyy-MM-dd").format(order.getCreatedAt()));
        holder.payNumberTv.setText(String.format("￥ %.2f", order.getGoods().getPrice()));
        holder.realPayNumberTv.setText(String.format("￥ %.2f", order.getGoods().getPrice()));
        holder.goodsTitleTv.setText(order.getGoods().getTitle());
        holder.typeTv.setText(getTypeString(order.getType()));
        Picasso.with(mContext).load(order.getGoods().getPreview()).into(holder.goodsPreviewIv);
        initListener(holder, position);
    }

    private String getTypeString(int type) {
        if (type == Constants.ORDER_TYPE_PAYMENT) {
            return App.getContext().getString(R.string.order_home_list_type_payment);
        } else if (type == Constants.ORDER_TYPE_COMPLETE_REQUE_EVALUATE) {
            return App.getContext().getString(R.string.order_home_list_type_reque_evaluate);
        } else if (type == Constants.ORDER_TYPE_COMPLETE_EVALUATED) {
            return App.getContext().getString(R.string.order_home_list_type_evaluated);
        } else {
            return App.getContext().getString(R.string.order_home_list_type_all);
        }
    }

    private void initListener(OrderListHolder holder, final int position) {
        if (mOrderListClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOrderListClickListener.orderDetailClickListener(v, mOrders.get(position));
                }
            });
            holder.cancelOrderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOrderListClickListener.cancelOrderClickListener(v, mOrders.get(position));
                }
            });
            holder.payOrderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOrderListClickListener.payOrderClickListener(v, mOrders.get(position));
                }
            });
            holder.orderEvaluateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOrderListClickListener.orderEvaluateClickListener(v, mOrders.get(position));
                }
            });
            holder.orderRefundBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOrderListClickListener.orderRefundClickListener(v, mOrders.get(position));
                }
            });
        }
    }

    private void initViewByType(OrderListHolder holder, int type) {
        if (type == Constants.ORDER_TYPE_PAYMENT) {
            // 待付款 隐藏评价与申请退款
            holder.orderEvaluateBtn.setVisibility(View.GONE);
            holder.orderRefundBtn.setVisibility(View.GONE);
        } else if (type == Constants.ORDER_TYPE_COMPLETE_REQUE_EVALUATE){
            // 已完成 隐藏 取消订单 立即付款 申请售后
            holder.cancelOrderBtn.setVisibility(View.GONE);
            holder.payOrderBtn.setVisibility(View.GONE);
        } else if (type == Constants.ORDER_TYPE_COMPLETE_EVALUATED) {
            // 已评价 隐藏 取消订单 立即付款 申请售后
            holder.cancelOrderBtn.setVisibility(View.GONE);
            holder.payOrderBtn.setVisibility(View.GONE);
            holder.orderEvaluateBtn.setVisibility(View.GONE);
        }
    }

    public void refresh(List<Order> mOrders) {
        this.mOrders = mOrders;
        notifyDataSetChanged();
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
        Button orderRefundBtn;
        Button orderEvaluateBtn;
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
            orderRefundBtn = itemView.findViewById(R.id.order_refund);
            orderEvaluateBtn = itemView.findViewById(R.id.order_evaluate);
            buttonLayoutLv = itemView.findViewById(R.id.button_layout);
        }
    }
}
