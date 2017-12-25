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
		
		
		//��ʼ��ȫ��ʹ�õ� lastChatlog
		
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
					BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));//��仰�����൱�ڽ���������
					StringBuffer buf = new StringBuffer();
					String line;
					while((line = in.readLine()) != null) {
						Log.i("9",line);
						buf.append(line);
					}
					in.close();
					String json = buf.toString();
					Gson gson = new GsonBuilder().setDateFormat("yyyyMMddHHmmss").create();
					User[] users = gson.fromJson(json, User[].class);//��json�ַ������java����

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
					
					ArrayList<User> u = new ArrayList<User>();//���u��������ʱ��ų����û��Լ�  �����˵�����User����
					String un = username.getText().toString();
					String pw = password.getText().toString();

					for(i=0;i<result.length;i++) {
//						Log.i("check",result[i].getUsername() + "    " + result[i].getPassword());
						
						//����û�������
						if(un.equals(result[i].getUsername()) && pw.equals(result[i].getPassword())) {
							//��ȡ���û��Լ���id
							InfoManager.userId = result[i].id;
							Log.i("Usersid", InfoManager.users.length+"");

							//������ת
							Intent intent1 = new Intent();
							intent1.setClass(LoginActivity.this, MainActivity.class);
							startActivity(intent1);
							finish();
							
							//�ѳ����û�����������û���ӵ�  u��
							for(int k=0; k<InfoManager.users.length;k++) {
								if(InfoManager.userId != InfoManager.users[k].id) {
									u.add(InfoManager.users[k]);
								}
								
							}
							
							//tu�������ǰ�u�����ݸ��ƹ����������ֵ��finalUsers
							User[] tu = new User[u.size()];
							
							//��u�е����ݸ� tu
							for(int j=0;j<u.size();j++) {
								tu[j] = u.get(j);
							}
							
							//���ս�tu�е����� ��finaluser
							InfoManager.finalUsers = tu;
							
							Log.i("UsersLength", InfoManager.users.length+"");
							Log.i("FUsersLength", InfoManager.finalUsers.length+"");
							
							//�ȸ�lastchatlog��ʼ����С������ٸı�������
							for(int k=0;k < InfoManager.finalUsers.length+1; k++) {
								InfoManager.lastChatlog.add(null);
								Log.i("initLC", InfoManager.lastChatlog.get(i)+"");
							}
							
							break;
						}
					}
					if(i == result.length) {
						Toast.makeText(LoginActivity.this, "�û������벻��ȷ�����������룡", Toast.LENGTH_LONG).show();
					}
				}
			}
		};
		task.execute();
	}
	
	


}
