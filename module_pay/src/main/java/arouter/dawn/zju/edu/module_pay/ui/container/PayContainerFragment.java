package arouter.dawn.zju.edu.module_pay.ui.container;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import arouter.dawn.zju.edu.module_pay.R;
import arouter.dawn.zju.edu.module_pay.ui.home.PayHomeFragment;

public class PayContainerFragment extends BottomSheetDialogFragment implements View.OnKeyListener {

    private double price;
    private String title;
    private String content;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_container, container, false);

        //监听back必须设置的
        rootView.setFocusable(true);
        rootView.setFocusableInTouchMode(true);
        //然后在写这个监听器
        rootView.setOnKeyListener(this);

        getChildFragmentManager().beginTransaction().add(R.id.container, new PayHomeFragment()).commit();

        return rootView;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            //这边判断,如果是back的按键被点击了   就自己拦截实现掉
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                int count = getChildFragmentManager().getBackStackEntryCount();
                if (count < 0) {
                    return false;
                }
                if (count == 0) {
                    dismiss();
                } else {
                    getChildFragmentManager().popBackStack();
                }
                return true;//表示处理了
            }
        }
        return false;
    }

    public void show(FragmentManager manager, double price, String title, String content) {
        this.price = price;
        this.title = title;
        this.content = content;
        show(manager, this.getClass().getSimpleName());
    }
}
