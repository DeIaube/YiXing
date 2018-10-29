package arouter.dawn.zju.edu.module_nearby;


import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import com.zaaach.citypicker.CityPickerActivity;

import baselib.base.BaseFragment;

import static android.app.Activity.RESULT_OK;


public class NearbyFragment extends BaseFragment {

    TextView searchTv;
    TextView loacationTv;

    private static final int REQUEST_CODE_PICK_CITY = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nearby;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected void initView(View view) {
        searchTv = view.findViewById(R.id.near_search);
        loacationTv = view.findViewById(R.id.near_location);
        loacationTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(), CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK){
            if (data != null){
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                showMessage("当前选择：" + city);
            }
        }

    }
}