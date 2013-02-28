package com.markevansjr.quoteme;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.markevansjr.quoteme.lib.FileStuff;
import com.markevansjr.quoteme.lib.MainListener;
import com.markevansjr.quoteme.lib.QuoteList;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements MainListener {
	
	QuoteList _quoteList = null;
	ArrayList<String> _data = new ArrayList<String>();
	ArrayList<String> _quotes = new ArrayList<String>();
	ArrayList<String> _authors = new ArrayList<String>();
	TextView _tv;
	ArrayList<Map<String, String>> _data2;
	static String _theQuote;
	static String _theAuthor;
	public static String _passed;
	public static String _sharedQuote = null;
	public static String _passedObjStr;
	public static String _passedQuote;
	public static String _passedAuthor;
	public static String _finalQuote;
	public static String _finalAuthor;
	HashMap<String, String> _recent = new HashMap<String, String>();
	ArrayList<String> _recentTitle = new ArrayList<String>();
	String currentQuote = null;
	public static String _checkButton;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Initialize Parse
		Parse.initialize(this, "AzORciWSbRjYRJ44OTDjmAufXcn7H87qXcz2wrKQ", "TBfaHCiVKpTQ5PvNgCj2zg8SHO8viYjTDuOCPVab");
		
		// Set Save Condition
		FileStuff.storeStringFile(getBaseContext(), "buttonSave", "YES", false);
		
		// Check for Connectivity
		ConnectivityManager connec = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connec != null && (connec.getNetworkInfo(1).isAvailable() == true) ||
				(connec.getNetworkInfo(0).isAvailable() == true)){
			
			// Get Random Quote
			getRandomQuote();
		} else {
			Toast toast = Toast.makeText(getApplicationContext(), "NO CONNECTION", Toast.LENGTH_SHORT);
			toast.show();
		}
		
		// Set ActionBar
		ActionBar bar = getActionBar();
	    
		// ActionBar Settings
	    bar.setDisplayShowCustomEnabled(true);
	    bar.setDisplayShowTitleEnabled(false);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM, ActionBar.DISPLAY_SHOW_CUSTOM);
        bar.setCustomView(R.layout.custom);
        
        // Custom Icon Setting
        View homeIcon = findViewById(android.R.id.home);
        ((View) homeIcon.getParent()).setVisibility(View.GONE);
        
        // Set Navigation Tab
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        // Set Text on each Tab
	    ActionBar.Tab tabHome = bar.newTab().setText("Home");
	    ActionBar.Tab tabSearch = bar.newTab().setText("Search");
	    ActionBar.Tab tabSaved = bar.newTab().setText("Saved");
	    ActionBar.Tab tabAdd = bar.newTab().setText("Add");
	    
	    // Define each Fragment
	    Fragment fragmentA = new HomeFragmentTab();
	    Fragment fragmentB = new SearchFragmentTab();
	    Fragment fragmentC = new SavedFragmentTab();
	    Fragment fragmentD = new AddFragmentTab();

	    // Attach Tab Listeners to Fragments
	    tabHome.setTabListener(new MyTabsListener(fragmentA));
	    tabSearch.setTabListener(new MyTabsListener(fragmentB));
	    tabSaved.setTabListener(new MyTabsListener(fragmentC));
	    tabAdd.setTabListener(new MyTabsListener(fragmentD));

	    // Add Tabs to ActionBar
	    bar.addTab(tabHome);
	    bar.addTab(tabSearch);
	    bar.addTab(tabSaved);
	    bar.addTab(tabAdd);
	    
	    try {
	        ViewConfiguration config = ViewConfiguration.get(this);
	        Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
	        if(menuKeyField != null) {
	            menuKeyField.setAccessible(true);
	            menuKeyField.setBoolean(config, false);
	        }
	    } catch (Exception ex) {
	        Log.i("TAG EXCEPTION", ex.toString());
	    }
	}
	
	public void getRandomQuote(){
		ParseQuery query = new ParseQuery("ListOfQuotes");
		Log.i("TAG GET PARSE", "SHOW ALL");
		query.findInBackground(new FindCallback() {
			public void done(List<ParseObject> objects, ParseException e) {
				if (e == null) {
					
					// Generate Random number from amount of Quotes in the array
					int random = 0 + (int)(Math.random() * ((objects.toArray().length - 1)));
					Log.i("TAG RANDOM COUNT", String.valueOf(random));
					ParseObject s = objects.get(random);
					String thequote = s.getString("quote");
					String theauthor = s.getString("author");
					FileStuff.storeStringFile(getApplicationContext(), "savedQuote", thequote+"\r\n\n"+theauthor, false);
					FileStuff.storeStringFile(getApplicationContext(), "QOD", thequote+"\r\n\n"+theauthor, false);
				}
			}
		});	
    }
	
	protected class MyTabsListener implements ActionBar.TabListener {

	    private Fragment fragment;

	    public MyTabsListener(Fragment fragment) {
	        this.fragment = fragment;
	    }

	    public void onTabReselected(Tab tab, FragmentTransaction ft) {
	    }

	    public void onTabSelected(Tab tab, FragmentTransaction ft) {
	        ft.add(R.id.fragment_container, fragment, null);
	    }

	    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	        // some people needed this line as well to make it work: 
	        ft.remove(fragment);
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu); 
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		ActionBar bar = getActionBar();
        
	    ActionBar.Tab tabHome = bar.getTabAt(0);
	    ActionBar.Tab tabSearch = bar.getTabAt(1);
	    ActionBar.Tab tabSaved = bar.getTabAt(2);
	    ActionBar.Tab tabAdd = bar.getTabAt(3);
	    
	    switch(item.getItemId()) {
	    case R.id.home_item:
	        //click on hometab item
	    	Log.i("TAG", "HOME");
	    	bar.selectTab(tabHome);
	        break;
	    case R.id.search_item:
	        //click on searchtab item
	    	Log.i("TAG", "SEARCH");
	    	bar.selectTab(tabSearch);
	        break;
	    case R.id.saved_item:
	        //click on savedtab item
	    	Log.i("TAG", "SAVED");
	    	bar.selectTab(tabSaved);
	        break;
	    case R.id.add_item:
	        //click on add item
	    	Log.i("TAG", "ADD");
	    	bar.selectTab(tabAdd);
	        break;
	    case R.id.share_item:
	        //click on share item
	    	ConnectivityManager connec = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connec != null && (connec.getNetworkInfo(1).isAvailable() == true) ||
					(connec.getNetworkInfo(0).isAvailable() == true)){
				
				// Quote and Author from Quote View
				_sharedQuote = HomeFragmentTab._tv.getText().toString();
				
				// Share Intent for Social Media
				Intent sharingIntent = new Intent(Intent.ACTION_SEND);
				sharingIntent.setType("text/plain");
				sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, _sharedQuote);
				sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "QuoteMe - Quote");
				startActivity(Intent.createChooser(sharingIntent, "Share using.."));
				Log.i("TAG", "SHARE");
			} else {
				Toast toast = Toast.makeText(getApplicationContext(), "NO CONNECTION", Toast.LENGTH_SHORT);
				toast.show();
			}
	        break;
	    }
	    return true;
	}

	@Override
	public void pass(String pass, String buttonSave, String id, int number) {
		ActionBar bar = getActionBar();
	    ActionBar.Tab tabHome = bar.getTabAt(0);
	    _checkButton = buttonSave;
		FileStuff.storeStringFile(getBaseContext(), "savedQuote", pass, false);
		FileStuff.storeStringFile(getBaseContext(), "buttonSave", buttonSave, false);
		FileStuff.storeStringFile(getBaseContext(), "theId", id, false);
		FileStuff.storeStringFile(getBaseContext(), "checkNull", String.valueOf(number), false);
		HomeFragmentTab._tv.setText(pass);
		bar.selectTab(tabHome);
	}

	@Override
	public void passForSearch(String objStr, String quote, String author) {
		_finalQuote = quote;
		_finalAuthor = author;
	}
	
	@Override
	public void passForSaved(String objStr, String quote, String author, String id) {
		_finalQuote = quote;
		_finalAuthor = author;
	}

	@Override
	public void checkButton(String state) {
		Log.i("TAG BUTTON STATE", state);
	}

}
