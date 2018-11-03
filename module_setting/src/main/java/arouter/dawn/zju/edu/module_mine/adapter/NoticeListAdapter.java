package arouter.dawn.zju.edu.module_mine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import arouter.dawn.zju.edu.module_mine.R;

public class NoticeListAdapter extends RecyclerView.Adapter<NoticeListAdapter.NoticeListHolder> {

    private Context mContext;

    public NoticeListAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public NoticeListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_notice, viewGroup, false);
        return new NoticeListHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeListHolder noticeListHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class NoticeListHolder extends RecyclerView.ViewHolder {

        NoticeListHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
