package com.art.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity  extends AppCompatActivity implements
CrimeFragment.Callbacks{

    private static final String EXTRA_CRIME_ID = "com.art.crimeintent.crime_id";

    private ViewPager mViewPager;
    private List<Crime> mCrimes;
    private Button mButtonPrev;
    private Button mButtonNext;

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        mViewPager = (ViewPager) findViewById(R.id.crime_view_pager);

        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {

                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        mButtonPrev = (Button) findViewById(R.id.button_prev_crime);
        mButtonNext = (Button) findViewById(R.id.button_next_crime);

        for (int i = 0; i < mCrimes.size(); i++) {

            if (mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                if(i == mCrimes.size() -1)
                    mButtonNext.setEnabled(false);
                if(i == 0)
                    mButtonPrev.setEnabled(false);
                break;
            }
        }

        mButtonPrev.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mButtonNext.setEnabled(true);
                int item = mViewPager.getCurrentItem();
                Log.i("item: ", " " + item);

                if (item != 0)
                    mViewPager.setCurrentItem(item - 1);
                item = item -1;
                if (item == 0)
                    mButtonPrev.setEnabled(false);
            }
        });

        mButtonNext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                mButtonPrev.setEnabled(true);
                int item = mViewPager.getCurrentItem();
                Log.i("item: ", " " + item);

                if(item != mCrimes.size() -1)
                    mViewPager.setCurrentItem(item + 1);
                item++;

                if (item == mCrimes.size() - 1)
                    mButtonNext.setEnabled(false);
            }
        });
    }

    @Override
    public void onCrimeUpdated(Crime crime) {

    }
}
