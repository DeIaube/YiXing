package arouter.dawn.zju.edu.module_mine.ui.setting;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import android.widget.Toast;


import arouter.dawn.zju.edu.module_mine.R;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 真正的设置页面
 */
public class SettingFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preference_setting);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceScreen ps = (PreferenceScreen) findPreference("clear_cache");
        ps.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(getContext(), "缓存已清除", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }
}
