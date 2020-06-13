package com.example.aquafit.ui.settings;

import android.os.*;
import android.view.*;
import android.support.v7.app.*;
import android.support.v4.view.*;
import android.support.design.widget.*;
import com.example.aquafit.R;
import java.util.*;
import android.support.v4.app.*;
import android.support.v7.widget.*;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity 
{
	
	private Toolbar toolbar;

	private ViewPager viewPager;

	private TabLayout tabLayout;

	private ArrayList<Fragment> fragments;

	private SettingsFragment viewSettings;


	private ActivityFragment viewActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
    super.onCreate(savedInstanceState);
    	initLayout();
		
    }
	public void initLayout(){
		setContentView(R.layout.settings_main);
		
        viewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        fragments =new ArrayList<Fragment>();

        fragments.add(viewSettings = new SettingsFragment());
        fragments.add(viewActivity = new ActivityFragment());

        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), getApplicationContext(), fragments);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_stars_white_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_date_range_white_24dp);
        
	}
}
