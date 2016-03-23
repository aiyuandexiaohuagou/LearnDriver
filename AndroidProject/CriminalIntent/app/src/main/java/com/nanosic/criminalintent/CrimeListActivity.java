package com.nanosic.criminalintent;

import android.app.Fragment;

/**
 * Created by Administrator on 2016/3/23.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
