package com.client.enigmas_quest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.client.enigmas_quest.adapter.NavigationDrawerAdapter;
import com.client.enigmas_quest.data.DrawerItem;
import com.client.enigmas_quest.fragments.MapPageFragment;
import com.client.enigmas_quest.fragments.ParametersPageFragment;
import com.client.enigmas_quest.fragments.StatsPageFragment;

public class Map_Activity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {


	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	private CharSequence mDrawerTitle;
	
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private ListView mDrawerList;
	private NavigationDrawerAdapter adapter;
	private List<DrawerItem> dataList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_);
		
		dataList = new ArrayList<DrawerItem>();
		dataList.add(new DrawerItem("Game"));
		dataList.add(new DrawerItem("Map", R.drawable.ic_action_gamepad));
        dataList.add(new DrawerItem("Stats", R.drawable.ic_action_about));
        dataList.add(new DrawerItem("Aprameter"));
        dataList.add(new DrawerItem("Parameters", R.drawable.ic_action_settings));

		mTitle = mDrawerTitle = getTitle();

		// Set up the drawer.
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);
		
		adapter = new NavigationDrawerAdapter(this, R.layout.custom_drawer_item,
                dataList);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		mDrawerList.setAdapter(adapter);
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		 
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
		            R.drawable.ic_drawer, R.string.navigation_drawer_open,
		            R.string.navigation_drawer_close) {
		      public void onDrawerClosed(View view) {
		            getActionBar().setTitle(mTitle);
		            invalidateOptionsMenu(); // creates call to
		                                                      // onPrepareOptionsMenu()
		      }
		 
		      public void onDrawerOpened(View drawerView) {
		            getActionBar().setTitle(mDrawerTitle);
		            invalidateOptionsMenu(); // creates call to
		                                                      // onPrepareOptionsMenu()
		      }
		};
		 
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		 
		if (savedInstanceState == null) {
		      onSelectItem(1);
		}
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
	      super.onPostCreate(savedInstanceState);
	      // Sync the toggle state after onRestoreInstanceState has occurred.
	      mDrawerToggle.syncState();
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1)).commit();
	}

	public void onSelectItem(int number) {
		Fragment fragment = null;
        Bundle args = new Bundle();
        
		switch (number) {
		case 1:
			fragment = new MapPageFragment();
            args.putString(MapPageFragment.ITEM_NAME, "yo");
			break;
		case 2:
			fragment = new StatsPageFragment();
            args.putString(StatsPageFragment.PLAYER_NAME, "Nicolas Martin");
            args.putInt(StatsPageFragment.SCORE, 54);
            args.putInt(StatsPageFragment.NB_ENIGMES, 18);
			break;
		case 4:
			fragment = new ParametersPageFragment();
			break;
		}
		
		fragment.setArguments(args);
        FragmentManager frgManager = getSupportFragmentManager();
        frgManager.beginTransaction().replace(R.id.container, fragment)
                    .commit();
        
        mDrawerList.setItemChecked(number, true);
        setTitle(dataList.get(number).getItemName());
        mDrawerLayout.closeDrawer(mDrawerList);

	}
	
	@Override
	public void setTitle(CharSequence title) {
	      mTitle = title;
	      getActionBar().setTitle(mTitle);
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
	      // ActionBarDrawerToggle will take care of this.
	      if (mDrawerToggle.onOptionsItemSelected(item)) {
	            return true;
	      }
	 
	      return false;
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	      super.onConfigurationChanged(newConfig);
	      // Pass any configuration change to the drawer toggles
	      mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			if (dataList.get(position).getTitle() == null) {
				onSelectItem(position);
			}
		}

	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_map_one, container,
					false);
			TextView textView = (TextView) rootView
					.findViewById(R.id.section_label_one);
			textView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((Map_Activity) activity).onSelectItem(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}

}