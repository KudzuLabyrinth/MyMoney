package com.example.a123.mymoney;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.a123.mymoney.fragment.AddExpenseFragment;
import com.example.a123.mymoney.fragment.AddIncomeFragment;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.billing);

        init();

        sqLiteHelper = new SQLiteHelper(
                MainActivity.this,
                "MyMoneyDB.sqlite",
                null,
                1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS FINANCIAL " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " cost VARCHAR," +
                " date VARCHAR," +
                " category VARCHAR," +
                " subCategory VARCHAR," +
                " comment VARCHAR)");


        viewPager.setAdapter(new CustomerAdapter(getSupportFragmentManager(), getApplicationContext()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
    }

    private class CustomerAdapter extends FragmentPagerAdapter {

        private String[] labels = getResources().getStringArray(R.array.add_expense_income_fragment);

        public CustomerAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new AddExpenseFragment();
                case 1:
                    return new AddIncomeFragment();
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

    private void init() {
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
    }


}
