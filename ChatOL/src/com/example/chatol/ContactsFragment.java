package com.example.chatol;

import java.util.ArrayList;
import java.util.List;

import com.example.chatol.MyContactListAdapter.onContactItemClickListener;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

@SuppressLint("NewApi") public class ContactsFragment extends Fragment{
	public ListView contactsList;
	public LayoutInflater inflater;
	
	public Button userInfoButton;
	public List<String> list = new ArrayList<String>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		View v = inflater.inflate(R.layout.contactslist_fragment, container,false);
		
		contactsList = (ListView) v.findViewById(R.id.contactsList);
		userInfoButton = (Button) v.findViewById(R.id.contactUserButton); 
		
		
		initListView();
		return v;
	}

	
	private void initListView() {
		// TODO Auto-generated method stub
		//---------------------chat log list setting------------------------------//
		Log.i("userList", "4");
		final MyContactListAdapter listAdapter = new MyContactListAdapter(this.getActivity(),list);
		ListAdapter adapter = new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				return listAdapter.getView(position, convertView, parent);
			}
			
			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return InfoManager.finalUsers.length;
			}
			
		};

			contactsList.setAdapter(listAdapter);
			contactsList.setAdapter(adapter);
			

			
			listAdapter.setOnContactItemJumpClickListener(new onContactItemClickListener() {
				
				@Override
				public void onTurnClick(int i) {
					// TODO Auto-generated method stub
					Log.i("item", i+"");
					Intent intent = new Intent(getActivity(),UserInfoActivity.class);
				
					int flag = i;
					intent.putExtra("flag", flag);
					startActivity(intent);
				}
			});
		
			//------------------------chat log list end-----------------------------//		
	}
}
