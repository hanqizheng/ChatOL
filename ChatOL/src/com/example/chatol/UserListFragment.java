package com.example.chatol;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.chatol.MyListAdapter.onItemClickListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

@SuppressLint("NewApi") public class UserListFragment extends Fragment{
	public ListView userList;
	public Button jumpButton;
	public List<String> list = new ArrayList<String>();
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.userlist_fragment, container,false);
		userList = (ListView) v.findViewById(R.id.userList);
		jumpButton = (Button)v.findViewById(R.id.userButton);
	
		//initChatLog();
		
		initChatLog();
		return v;
	}
	
	
	
//	private void init() {
//		AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
//			
//			@Override
//			protected Void doInBackground(Void... params) {
//				initChatLog();
//				
//				return null;
//			}
//			
//			@Override
//			protected void onPostExecute(Void result) {
//				
////				for(int i=0 ;i<InfoManager.lastChatlog.size();i++) {
////					Log.i("CON", InfoManager.lastChatlog.get(i).toString());
////				}
//				initListView();
//			}
//			
//		};
//		task.execute();
//	}



	private void initChatLog() {

		Log.i("initChatlog", "aaaaa");
		
		//给对方的id赋初值，因为接下来要遍历用户与其他每个好友的所有chatlog
		InfoManager.oppositeId = 1;


		AsyncTask<Integer, Void, Void> task = new AsyncTask<Integer, Void, Void>(){

			@Override
			protected Void doInBackground(Integer... arg0) {
				for(int i = InfoManager.oppositeId ; i <= InfoManager.users.length ; i++) {
					if(i == InfoManager.userId) {
						continue;
					}
					try {
						// 10.1.112.12
						URL url = new URL("http://172.30.187.3:8080/project/findChatLog.jsp?userid1=" + arg0[0] + "&userid2=" + i);
						BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
						StringBuffer buf = new StringBuffer();
						String line;
						while ((line = in.readLine()) != null) {
							Log.i("ABCD",line);
							buf.append(line);
						}
						in.close();
						String json = buf.toString();
					
						Gson gson = new GsonBuilder().setDateFormat("yyyyMMddHHmmss").create();
						ChatLog[] cl = gson.fromJson(json, ChatLog[].class);//从json字符串变成java对象

						for(int j=0 ; j<cl.length ;j++) {
							InfoManager.chatlogs.add(cl[j]);
						}
						
						
						InfoManager.lastChatlog.set(i-1, InfoManager.chatlogs.get(InfoManager.chatlogs.size()-1));
						//Log.i("CONTENT",(i-1) +  "   "  + InfoManager.lastChatlog.get(i-1)+"");

					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				
				}
				return null;
			}
			
			protected void onPostExecute(Void result) {
				
				for(int i=0 ;i<InfoManager.lastChatlog.size();i++) {
					Log.i("CON", InfoManager.lastChatlog.get(i)+"");
				}
				initListView();
			}
			
		};		
		Log.i("myId", InfoManager.userId+"");
		Log.i("otherId", InfoManager.oppositeId+"");
		task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, InfoManager.userId , InfoManager.oppositeId);
		

		//task.execute(InfoManager.userId , InfoManager.oppositeId);
	}
	
	
	private void initListView() {
		// TODO Auto-generated method stub
		//---------------------chat log list setting------------------------------//
		Log.i("initListView", "bbbbbbbbb");
		final MyListAdapter listAdapter = new MyListAdapter(this.getActivity(),list);
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
		
			Log.i("userList", "50");
			
			userList.setAdapter(listAdapter);
			userList.setAdapter(adapter);
			listAdapter.setOnItemJumpClickListener(new onItemClickListener() {
				
				@Override
				public void onJumpClick(int i) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(getActivity(),ChatActivity.class);
					
					int flag = i;
					intent.putExtra("flag", flag);
					startActivity(intent);
				}
			});
			//------------------------chat log list end-----------------------------//		
	}
}
