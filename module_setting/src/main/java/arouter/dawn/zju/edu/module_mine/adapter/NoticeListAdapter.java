package arouter.dawn.zju.edu.module_mine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import arouter.dawn.zju.edu.lib_db.bean.Notice;
import arouter.dawn.zju.edu.module_mine.R;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 通知适配器
 */
public class NoticeListAdapter extends RecyclerView.Adapter<NoticeListAdapter.NoticeListHolder> {

    private Context mContext;
    private List<Notice> mNoticeList;

    public NoticeListAdapter(Context mContext, List<Notice> noticeList) {
        this.mContext = mContext;
        this.mNoticeList = noticeList;
    }

    public void refresh(List<Notice> noticeList) {
        this.mNoticeList = noticeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoticeListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_notice, viewGroup, false);
        return new NoticeListHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeListHolder holder, int position) {
        Notice notice = mNoticeList.get(position);
        if (notice.isValid()) {
            holder.title.setText(notice.getTitle());
            holder.content.setText(notice.getContent());
            holder.time.setText(notice.getTime());
        }
    }

    @Override
    public int getItemCount() {
        return mNoticeList.size();
    }

    class NoticeListHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;
        TextView time;

        NoticeListHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notice_title);
            content = itemView.findViewById(R.id.notice_content);
            time = itemView.findViewById(R.id.notice_time);
        }
    }
}
