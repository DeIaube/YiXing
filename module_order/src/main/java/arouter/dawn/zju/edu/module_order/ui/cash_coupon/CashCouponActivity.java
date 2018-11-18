package arouter.dawn.zju.edu.module_order.ui.cash_coupon;


import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_order.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_ORDER_CASH_COUPON)
public class CashCouponActivity extends BaseActivity<CashCouponContract.Presenter> implements CashCouponContract.View {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cash_coupon;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new CashCouponPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cash_coupon, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cash_coupon_menu_infamation) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.tips)
                    .setMessage(R.string.cash_coupon_infamation)
                    .setNegativeButton(R.string.confirm, null)
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }
}
