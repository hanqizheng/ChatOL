package com.example.chatol;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListAdapter extends BaseAdapter {
	private Context listContext;
	private List<String> list = new ArrayList<String>();
	
    public MyListAdapter(Context context, List<String> list) {
        listContext = context;
        this.list = list;
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View old, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Log.i("userList", "5");
		View v;
		ListItem item;
		
//		if(old == null) {
			//LayoutInflater inflater = v.getLayoutInflater();
			Log.i("userList", "10");
			v = LayoutInflater.from(listContext).inflate(R.layout.list_item, null);
			Log.i("userList", "11");
			TextView userNameTextView = (TextView)v.findViewById(R.id.userNameTextView);
			TextView chatLogTextView = (TextView)v.findViewById(R.id.chatLogTextView);
		    Button btn = (Button)v.findViewById(R.id.userButton);
		    ImageView image = (ImageView)v.findViewById(R.id.chatImage);
		    
		    item = new ListItem();
		    item.userName = userNameTextView;
		    item.chatLog = chatLogTextView;
		    item.btn = btn;
		    item.image = image;
		    item.user = InfoManager.finalUsers[position];
		    item.userName.setText(item.user.memo1);
		    

		    item.chatLog.setText(InfoManager.lastChatlog.get(item.user.id-1).content+"");
		    
		    
		    selectProfile(item);
		    
		    v.setTag(item);
//		}
//		else {
//			Log.i("userList", "9");
//			v = old;
//			item = (ListItem)v.getTag();
//		}

        item.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onJumpClick(position);
            }
        });
        return v;
	}
    
	 private void selectProfile(ListItem item) {
		// TODO Auto-generated method stub
		 
			//这里用了一个最low的办法，实际上并没有解决这个问题，真正解决这个问题应该是在数据库
			//用户表多加一列用来存取用户头像的相关数据
			//但是由于我更改数据库以后出现了莫名的错误，所以我暂时用这个方法代替。
		    switch (item.user.id) {
			case 1:
				item.image.setBackgroundResource(R.drawable.icon1);
				break;
			case 2:
				item.image.setBackgroundResource(R.drawable.icon2);
				break;
			case 3:
				item.image.setBackgroundResource(R.drawable.icon3);
				break;
			case 4:
				item.image.setBackgroundResource(R.drawable.icon4);
				break;
			case 5:
				item.image.setBackgroundResource(R.drawable.icon5);
				break;
			case 6:
				item.image.setBackgroundResource(R.drawable.icon6);
				break;
			case 7:
				item.image.setBackgroundResource(R.drawable.icon7);
				break;
			case 8:
				item.image.setBackgroundResource(R.drawable.icon8);
				break;
			case 9:
				item.image.setBackgroundResource(R.drawable.icon9);
				break;
			case 10:
				item.image.setBackgroundResource(R.drawable.icon10);
				break;
			case 11:
				item.image.setBackgroundResource(R.drawable.icon11);
				break;
			case 12:
				item.image.setBackgroundResource(R.drawable.icon12);
				break;
			case 13:
				item.image.setBackgroundResource(R.drawable.icon13);
				break;
			case 14:
				item.image.setBackgroundResource(R.drawable.icon14);
				break;
			case 15:
				item.image.setBackgroundResource(R.drawable.icon15);
				break;
			default:
				item.image.setBackgroundResource(R.drawable.icon);
				break;
			}
	}

	/**
     * 删除按钮的监听接口
     */
    public interface onItemClickListener {
        void onJumpClick(int i);
    }
    
    private onItemClickListener mOnItemClickListener;

    public void setOnItemJumpClickListener(onItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
