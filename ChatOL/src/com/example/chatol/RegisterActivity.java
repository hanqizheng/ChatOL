package com.example.chatol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

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

public class RegisterActivity extends Activity {
	
	EditText nickname;
	EditText username;
	EditText password;
	EditText passwordCheck;
	Button registerbtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		
		nickname = (EditText)findViewById(R.id.registerNickname);
		username = (EditText)findViewById(R.id.registerUsername);
		password = (EditText)findViewById(R.id.registerPassword);
		passwordCheck = (EditText)findViewById(R.id.registerPasswordCheck);
		registerbtn = (Button)findViewById(R.id.backButton);
		
		registerbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//trim()作用是去掉字符串中的空格
				
//				publishUser();
				String pw = password.getText().toString();
				String pwc = passwordCheck.getText().toString();
				
				Log.i("check", pw + "  " + pwc);
				if(pw.equals(pwc)) {
					publishUser();
					Intent intent = new Intent();
					intent.setClass(RegisterActivity.this, LoginActivity.class);
					startActivity(intent);
					finish();
				}
				else {
					Toast.makeText(RegisterActivity.this, "密码不一致，请重新输入！", Toast.LENGTH_LONG).show();
				}
			}
		});
	}				
	private void publishUser() {
			// TODO Auto-generated method stub
		String nn = nickname.getText().toString();
		String un = username.getText().toString();//trim()作用是去掉字符串中的空格
		String pw = password.getText().toString();
		
		AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>(){
			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				try{
					//10.1.112.12
				URL url = new URL("http://172.30.187.3:8080/project/Register.jsp?username="+params[0] + "&password=" + params[1] + "&nickname=" + params[2]);
				BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
				StringBuffer buf = new StringBuffer();
				String line;
				while((line = in.readLine()) != null) {
					Log.i("register",line);
					buf.append(line);
				}
				in.close();
				String json = buf.toString();
				Log.i("regist", json);
				return json;
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return null;
			}
			
			@Override
			protected void onPostExecute(String result) {
				Log.i("regist", result);
				Toast.makeText(RegisterActivity.this,result, Toast.LENGTH_SHORT).show();
			}
		};
		
		task.execute(un,pw,nn);
	}
	
}
