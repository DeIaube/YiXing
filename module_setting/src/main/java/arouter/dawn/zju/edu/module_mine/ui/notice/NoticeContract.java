package arouter.dawn.zju.edu.module_mine.ui.notice;


import java.util.List;

import arouter.dawn.zju.edu.lib_db.bean.Notice;
import baselib.base.BaseContract;

public interface NoticeContract {

    interface View extends BaseContract.BaseView {

        void refreshNoticeList(List<Notice> noticeList);

    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void refreshNoticeList();

    }

}
