package com.example.chatol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserInfoActivity extends Activity {
  
	TextView nickname;
	TextView username;
	Button chatButton;
	ImageView profile;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_info);
		
		nickname = (TextView)findViewById(R.id.userinfoNicknaeme);
		username = (TextView)findViewById(R.id.userinfoUsername);
		chatButton = (Button)findViewById(R.id.userinfoButton);
		profile = (ImageView)findViewById(R.id.userinfoProfile);
		
		Intent intent = getIntent();
		
		final int userNum = intent.getIntExtra("flag", 0);
		
		InfoManager.oppositeId = InfoManager.finalUsers[userNum].id;//获取到对方的id
		
		nickname.setText(InfoManager.finalUsers[userNum].memo1);
		username.setText("COL号:" + InfoManager.finalUsers[userNum].username);
		
		
		//这里用了一个最low的办法，实际上并没有解决这个问题，真正解决这个问题应该是在数据库
		//用户表多加一列用来存取用户头像的相关数据
		//但是由于我更改数据库以后出现了莫名的错误，所以我暂时用这个方法代替。
		 switch (InfoManager.finalUsers[userNum].id) {
			case 1:
				profile.setBackgroundResource(R.drawable.icon1);
				break;
			case 2:
				profile.setBackgroundResource(R.drawable.icon2);
				break;
			case 3:
				profile.setBackgroundResource(R.drawable.icon3);
				break;
			case 4:
				profile.setBackgroundResource(R.drawable.icon4);
				break;
			case 5:
				profile.setBackgroundResource(R.drawable.icon5);
				break;
			case 6:
				profile.setBackgroundResource(R.drawable.icon6);
				break;
			case 7:
				profile.setBackgroundResource(R.drawable.icon7);
				break;
			case 8:
				profile.setBackgroundResource(R.drawable.icon8);
				break;
			case 9:
				profile.setBackgroundResource(R.drawable.icon9);
				break;
			case 10:
				profile.setBackgroundResource(R.drawable.icon10);
				break;
			case 11:
				profile.setBackgroundResource(R.drawable.icon11);
				break;
			case 12:
				profile.setBackgroundResource(R.drawable.icon12);
				break;
			case 13:
				profile.setBackgroundResource(R.drawable.icon13);
				break;
			case 14:
				profile.setBackgroundResource(R.drawable.icon14);
				break;
			case 15:
				profile.setBackgroundResource(R.drawable.icon15);
				break;
			default:
				profile.setBackgroundResource(R.drawable.icon);
				break;
			}
		
		
		chatButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(UserInfoActivity.this,ChatActivity.class);

				startActivity(intent);
			}
		});
	}
}
