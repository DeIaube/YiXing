package yixing.dawn.zju.edu.yixing.ui;


import android.view.View;
import android.widget.Button;

import arouter.dawn.zju.edu.lib_net.bean.User;
import baselib.base.BaseFragment;
import yixing.dawn.zju.edu.yixing.R;

public class ConsoleFragment extends BaseFragment implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
       return R.layout.fragment_console;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.exit_logon).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.exit_logon) {
            User.logOut();
        }
    }
}
