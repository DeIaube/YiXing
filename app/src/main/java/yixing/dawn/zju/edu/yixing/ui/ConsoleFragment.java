package yixing.dawn.zju.edu.yixing.ui;


import android.view.View;
import android.widget.Toast;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_pay.callback.PayCallback;
import arouter.dawn.zju.edu.module_pay.pay.PayBuiled;
import arouter.dawn.zju.edu.module_pay.ui.container.PayContainerFragment;
import baselib.base.BaseFragment;
import yixing.dawn.zju.edu.yixing.R;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * debug专用页面
 */
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
        view.findViewById(R.id.ali_pay).setOnClickListener(this);
        view.findViewById(R.id.wallet_pay).setOnClickListener(this);
        view.findViewById(R.id.pay_fragment).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.exit_logon) {
            User.logOut();
        } else if (id == R.id.ali_pay) {
            new PayBuiled(getActivity())
                    .setTitle("测试商品")
                    .setContent("测试测试")
                    .setPrice(233)
                    .setPayCallback(new PayCallback() {
                        @Override
                        public void paySuccess() {
                            Toast.makeText(getContext(), "支付成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void payFailed(String msg) {
                            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .builedAliPay()
                    .pay(v);
        } else if (id == R.id.pay_fragment) {
            PayContainerFragment payBottomFragment = new PayContainerFragment();
            payBottomFragment.show(getFragmentManager(), 10.0, "title", "content", new PayCallback() {
                @Override
                public void paySuccess() {

                }

                @Override
                public void payFailed(String msg) {

                }
            });
        }else if (id == R.id.wallet_pay) {
            new PayBuiled(getActivity())
                    .setTitle("测试商品")
                    .setContent("测试测试")
                    .setPrice(233)
                    .setPayCallback(new PayCallback() {
                        @Override
                        public void paySuccess() {
                            Toast.makeText(getContext(), "支付成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void payFailed(String msg) {
                            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .builedWalletPay()
                    .pay();
        }
    }
}
