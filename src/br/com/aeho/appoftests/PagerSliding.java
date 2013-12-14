package br.com.aeho.appoftests;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.astuetz.viewpager.extensions.PagerSlidingTabStrip;

public class PagerSliding extends ActionBarActivity {

	static final Fragment[] fragmentos = { new Fragmento0(), new Fragmento1(),
			new Fragmento2() };

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.pagersliding);
		ViewPager pager = (ViewPager) findViewById(R.id.pagersliding_pager);
		pager.setAdapter(new PagerTestAdapter(getSupportFragmentManager()));

		PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.pagersliding_tabs);
		tabs.setViewPager(pager);
	}

	private class PagerTestAdapter extends FragmentStatePagerAdapter {

		public PagerTestAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int frag_position) {
			return fragmentos[frag_position];
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return "fragmento";
		}

	}

}
