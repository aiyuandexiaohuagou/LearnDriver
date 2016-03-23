package com.nanosic.criminalintent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class MainActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        UUID crimeId= (UUID)getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }
}
