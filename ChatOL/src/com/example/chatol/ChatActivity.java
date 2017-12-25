package com.example.chatol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
//import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ChatActivity extends Activity {

	Button sendBtn;
	EditText chatET;
	ListView chatLV;
	
	BaseAdapter chatAdapter;

	ArrayList<ChatLog> chatLogList = new ArrayList<ChatLog>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.chat_activity);

		Intent intent = getIntent();
		final int userNum = intent.getIntExtra("flag", 0);
		InfoManager.oppositeId = InfoManager.finalUsers[userNum].id;//获取到对方的id
		
		Log.i("OPPOID", InfoManager.oppositeId+"");
		
		sendBtn = (Button) findViewById(R.id.sendButton);
		chatET = (EditText) findViewById(R.id.chatEditText);
		chatLV = (ListView) findViewById(R.id.chatListView);
		
				
		sendBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String content = chatET.getText().toString();
				Log.i("J",content);
				sendMessage(content);
			}

		});

		InfoManager.chatlogs.clear();
		
		initLoopDownloadTask();		
		initListView();
	}

	private void initLoopDownloadTask() {

		Log.i("chatActivity", "12");
		AsyncTask<Integer, String, Void> task = new AsyncTask<Integer, String, Void>(){
			@Override
			protected Void doInBackground(Integer... arg0) {
				try {
					//10.1.112.12
					while(true) {
						Log.i("MyId", InfoManager.userId+"");
						Log.i("OpId", InfoManager.oppositeId+"");
						//10.1.112.12
						URL url = new URL("http://172.30.187.3:8080/project/findChatLog.jsp?userid1=" + arg0[0] + "&userid2=" + arg0[1]);
						BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));//这句话就是相当于建立了连接
						StringBuffer buf = new StringBuffer();
						String line;
						while((line = in.readLine()) != null) {
							//Log.i("getChatlog",line);
							buf.append(line);
						}
						in.close();
						String json = buf.toString();
						
						Gson gson = new GsonBuilder().setDateFormat("yyyyMMddHHmmss").create();
						ChatLog[] chatlog = gson.fromJson(json, ChatLog[].class);
				
						
						Log.i("loopChatLog", "wai");
						
						//如果没有聊过天，就从头添加
						if(InfoManager.chatlogs.size() == 0) {
							Log.i("getChatlog",0+"");
							for(int i=0;i<chatlog.length;i++) {
								chatLogList.add(chatlog[i]);
								InfoManager.chatlogs.add(chatlog[i]);
							}
						}
						else {//如果聊过天就从最后一句的后面添加
							int startNum = InfoManager.chatlogs.size() + 1;
							Log.i("getChatlog",chatlog[startNum-2]+"");
							for(int i = startNum;i<chatlog.length;i++) {
								chatLogList.add(chatlog[i]);
								InfoManager.chatlogs.add(chatlog[i]);
							}
						}
						
						//InfoManager.lastChatlog[InfoManager.oppositeId-1] = InfoManager.chatlogs.get(InfoManager.chatlogs.size()-1);
						InfoManager.lastChatlog.set(InfoManager.oppositeId-1, InfoManager.chatlogs.get(InfoManager.chatlogs.size()-1));
						
						publishProgress("success");
						Thread.sleep(3000);
					}

					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					publishProgress("nothing");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					publishProgress("nothing");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					publishProgress("nothing");
				}
				return null;

			}

			@Override
			protected void onProgressUpdate(String... values) {
				if("success".equals(values[0])){
					chatAdapter.notifyDataSetChanged();
				}
			}
		};
		task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,InfoManager.userId,InfoManager.oppositeId);
		//task.execute(InfoManager.userId,InfoManager.oppositeId);
	}

	private void initListView() {
		Log.i("initLISTVIEW",chatLogList.size()+"");
		chatAdapter = new BaseAdapter() {

			@Override
			public View getView(int position, View old, ViewGroup arg2) {
				// TODO Auto-generated method stub

				Log.i("initLISTVIEW", "11");
				ChatLog log = chatLogList.get(position);
				View v;
				if (log.getUserid1() == InfoManager.userId) {
					// me
					v = getLayoutInflater().inflate(R.layout.me_say, null);
					ImageView myProfile = (ImageView)v.findViewById(R.id.meSayProfile);
					selectProfile(myProfile,InfoManager.userId);//去获取自己的头像
				} else {
					// other
					v = getLayoutInflater().inflate(R.layout.other_say, null);
					ImageView myProfile = (ImageView)v.findViewById(R.id.otherSayProfile);
					selectProfile(myProfile,InfoManager.oppositeId);//获取聊天好友的头像
				}
				TextView tv = (TextView) v.findViewById(R.id.logTV);
				
				Log.i("USER_ID",InfoManager.userId+"");
				Log.i("FRINEND",InfoManager.oppositeId+"");				
				
				tv.setText(log.getContent());

				return v;
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
				return chatLogList.size();
			}
		};
		chatLV.setAdapter(chatAdapter);
	}

	private void selectProfile(ImageView myProfile, int userId) {
		// TODO Auto-generated method stub
		
		//这里用了一个最low的办法，实际上并没有解决这个问题，真正解决这个问题应该是在数据库
		//用户表多加一列用来存取用户头像的相关数据
		//但是由于我更改数据库以后出现了莫名的错误，所以我暂时用这个方法代替。
		switch (userId) {
		case 1:
			myProfile.setBackgroundResource(R.drawable.icon1);
			break;
		case 2:
			myProfile.setBackgroundResource(R.drawable.icon2);
			break;
		case 3:
			myProfile.setBackgroundResource(R.drawable.icon3);
			break;
		case 4:
			myProfile.setBackgroundResource(R.drawable.icon4);
			break;
		case 5:
			myProfile.setBackgroundResource(R.drawable.icon5);
			break;
		case 6:
			myProfile.setBackgroundResource(R.drawable.icon6);
			break;
		case 7:
			myProfile.setBackgroundResource(R.drawable.icon7);
			break;
		case 8:
			myProfile.setBackgroundResource(R.drawable.icon8);
			break;
		case 9:
			myProfile.setBackgroundResource(R.drawable.icon9);
			break;
		case 10:
			myProfile.setBackgroundResource(R.drawable.icon10);
			break;
		case 11:
			myProfile.setBackgroundResource(R.drawable.icon11);
			break;
		case 12:
			myProfile.setBackgroundResource(R.drawable.icon12);
			break;
		case 13:
			myProfile.setBackgroundResource(R.drawable.icon13);
			break;
		case 14:
			myProfile.setBackgroundResource(R.drawable.icon14);
			break;
		case 15:
			myProfile.setBackgroundResource(R.drawable.icon15);
			break;
		default:
			myProfile.setBackgroundResource(R.drawable.icon);
			break;
		}
	}
	
	private void sendMessage(String content) {
		// TODO Auto-generated method stub
		Log.i("sendMessage", "5");

		AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... params) {

				try {
					// 10.1.112.12
					URL url = new URL("http://172.30.187.3:8080/project/publishChat.jsp?userid1=" + params[0]
							+ "&userid2=" + params[1] + "&content=" + params[2] + "&memo1=" + params[3]);
					Log.i("PARAMS",params[3]);
					BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
					StringBuffer buf = new StringBuffer();
					String line;
					while ((line = in.readLine()) != null) {
						buf.append(line);
					}
					in.close();
					String json = buf.toString();

					return json;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(String result) {
				chatAdapter.notifyDataSetChanged();
			}
		};
		
		int memo1;
		if(InfoManager.chatlogs.size() == 0) {
			memo1 = 1;//没说过话，所以最后一句话的id是1
		}
		else {
			ChatLog lastchatLog = InfoManager.chatlogs.get(InfoManager.chatlogs.size()-1);
			memo1 = Integer.parseInt(lastchatLog.memo1) + 1;//最后一句话的id+1
		}

		task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, InfoManager.userId + "", InfoManager.oppositeId + "",content,memo1+"");
	}

}
