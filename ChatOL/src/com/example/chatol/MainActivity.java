package com.example.chatol;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button chatOLButton;
	Button contactsButton;
	Button discoverButton;
	Button meButton;
	
	UserListFragment userListFragment = new UserListFragment();
	ContactsFragment contactsFragment = new ContactsFragment();
	DiscoverFragment discoverFragment = new DiscoverFragment();
	MeFragment meFragment = new MeFragment();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("onCreat", "before");
		initButton();
		Log.i("onCreat", "after");
		
		getFragmentManager().beginTransaction().replace(R.id.container, userListFragment).commit();
		Log.i("onCreat", "last");
	}


	private void initButton() {
		Log.i("initButton", "1");
		chatOLButton = (Button) findViewById(R.id.chatBtn);
		discoverButton = (Button) findViewById(R.id.discoverBtn);
		contactsButton = (Button) findViewById(R.id.contactsBtn);
		meButton = (Button) findViewById(R.id.meBtn);
		
		chatOLButton.setOnClickListener(new OnClickListener() {
			@Override
				public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("initButton", "2");
				getFragmentManager().beginTransaction().replace(R.id.container,userListFragment).commit();
				}
		});
		
		discoverButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("initButton", "3");
				// TODO Auto-generated method stub
				getFragmentManager().beginTransaction().replace(R.id.container, discoverFragment).commit();
			}
		});
		
		contactsButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("initButton", "4");
				getFragmentManager().beginTransaction().replace(R.id.container, contactsFragment).commit();
			}
		});
		
		meButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("initButton", "5");
				getFragmentManager().beginTransaction().replace(R.id.container,meFragment).commit();
			}
		});
		
	}
}
