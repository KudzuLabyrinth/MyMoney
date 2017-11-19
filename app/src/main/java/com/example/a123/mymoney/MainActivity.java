package com.example.a123.mymoney;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a123.mymoney.fragment.AddExpenseFragment;
import com.example.a123.mymoney.fragment.AddIncomeFragment;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout mTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billing);
        viewPager=(ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new CustomerAdapter(getSupportFragmentManager(),getApplicationContext()));

        mTabLayout=findViewById(R.id.label);
        mTabLayout.setupWithViewPager(viewPager);

    }

    private class CustomerAdapter extends FragmentPagerAdapter {
        private String[] labels=getResources().getStringArray(R.array.add_expense_income_fragment);

        public CustomerAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new AddIncomeFragment();
                case 1:
                    return new AddExpenseFragment();
                default:
            }

            return null;
        }

        @Override
        public int getCount() {
            return labels.length;
        }



        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return labels[position];
        }
    }
}
