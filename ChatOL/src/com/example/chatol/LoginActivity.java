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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	EditText username;
	EditText password;
	Button loginbtn;
	Button registerbtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		
		
		//初始化全局使用的 lastChatlog
		
		//Log.i("LastChatLogLength", InfoManager.lastChatlog.size()+"");
		
		
		username = (EditText)findViewById(R.id.Username);
		password = (EditText)findViewById(R.id.Password);
		loginbtn = (Button)findViewById(R.id.loginButton);
		registerbtn = (Button)findViewById(R.id.registerButton);
		
		loginbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				checkAccount();
			}
		});
		
		registerbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent2 = new Intent();
				intent2.setClass(LoginActivity.this, RegisterActivity.class);
				startActivity(intent2);
			}
		});
		
	}		
	
	
	private void checkAccount() {
		// TODO Auto-generated method stub
		AsyncTask<Void, Void, User[]> task = new AsyncTask<Void, Void, User[]>(){
			@Override
			protected User[] doInBackground(Void... params) {
				// TODO Auto-generated method stub
				//Log.i("login", "854247");
				try {
					//10.1.112.12
					URL url = new URL("http://172.30.187.3:8080/project/findUser.jsp");
					BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));//这句话就是相当于建立了连接
					StringBuffer buf = new StringBuffer();
					String line;
					while((line = in.readLine()) != null) {
						Log.i("9",line);
						buf.append(line);
					}
					in.close();
					String json = buf.toString();
					Gson gson = new GsonBuilder().setDateFormat("yyyyMMddHHmmss").create();
					User[] users = gson.fromJson(json, User[].class);//从json字符串变成java对象

					InfoManager.users = users;
					
					return users;
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			@Override
			protected void onPostExecute(User[] result) {
				if(result!=null) {
					int i;
					
					ArrayList<User> u = new ArrayList<User>();//这个u是用来临时存放除了用户自己  其他人的所有User对象
					String un = username.getText().toString();
					String pw = password.getText().toString();

					for(i=0;i<result.length;i++) {
//						Log.i("check",result[i].getUsername() + "    " + result[i].getPassword());
						
						//检查用户名密码
						if(un.equals(result[i].getUsername()) && pw.equals(result[i].getPassword())) {
							//获取到用户自己的id
							InfoManager.userId = result[i].id;
							Log.i("Usersid", InfoManager.users.length+"");

							//界面跳转
							Intent intent1 = new Intent();
							intent1.setClass(LoginActivity.this, MainActivity.class);
							startActivity(intent1);
							finish();
							
							//把除了用户本身的其他用户添加到  u中
							for(int k=0; k<InfoManager.users.length;k++) {
								if(InfoManager.userId != InfoManager.users[k].id) {
									u.add(InfoManager.users[k]);
								}
								
							}
							
							//tu的作用是把u的内容复制过来并且最后赋值给finalUsers
							User[] tu = new User[u.size()];
							
							//把u中的内容给 tu
							for(int j=0;j<u.size();j++) {
								tu[j] = u.get(j);
							}
							
							//最终将tu中的内容 给finaluser
							InfoManager.finalUsers = tu;
							
							Log.i("UsersLength", InfoManager.users.length+"");
							Log.i("FUsersLength", InfoManager.finalUsers.length+"");
							
							//先给lastchatlog初始化大小，随后再改变其内容
							for(int k=0;k < InfoManager.finalUsers.length+1; k++) {
								InfoManager.lastChatlog.add(null);
								Log.i("initLC", InfoManager.lastChatlog.get(i)+"");
							}
							
							break;
						}
					}
					if(i == result.length) {
						Toast.makeText(LoginActivity.this, "用户名密码不正确，请重新输入！", Toast.LENGTH_LONG).show();
					}
				}
			}
		};
		task.execute();
	}
	
	


}
