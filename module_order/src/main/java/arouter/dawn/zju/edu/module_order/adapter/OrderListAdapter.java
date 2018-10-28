package arouter.dawn.zju.edu.module_order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import arouter.dawn.zju.edu.module_order.R;
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

    @Override
    public void onBindViewHolder(OrderListHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }

    static class OrderListHolder extends RecyclerView.ViewHolder{

        OrderListHolder(View itemView) {
            super(itemView);
        }
    }
}
