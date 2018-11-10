package arouter.dawn.zju.edu.module_forum.ui.list;


import org.greenrobot.eventbus.EventBus;

import arouter.dawn.zju.edu.module_forum.config.EventBusCode;
import arouter.dawn.zju.edu.module_forum.ui.home.ForumHomeFragment;
import baselib.base.BasePresenter;
import baselib.bus.BusEvent;

public class ForumListPresenter extends BasePresenter<ForumListContract.View> implements ForumListContract.Presenter {

    String TAG = "ForumListPresenter";

    @Override
    public void sendScrollUpEvent() {
        BusEvent event = new BusEvent();
        event.setTarget(ForumHomeFragment.TAG);
        event.setCode(EventBusCode.FORUM_LIST_SCROLL_UP);
        EventBus.getDefault().post(event);
    }

    @Override
    public void sendScrollDownEvent() {
        BusEvent event = new BusEvent();
        event.setTarget(ForumHomeFragment.TAG);
        event.setCode(EventBusCode.FORUM_LIST_SCROLL_DOWN);
        EventBus.getDefault().post(event);
    }
}
