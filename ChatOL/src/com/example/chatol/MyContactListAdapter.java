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

public class MyContactListAdapter extends BaseAdapter {
	private Context listContext;
	private List<String> list = new ArrayList<String>();
	
    public MyContactListAdapter(Context context, List<String> list) {
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
		Log.i("position", position+"");
		View v;
		ContactsItem item;
		
//		if(old == null) {
			//LayoutInflater inflater = v.getLayoutInflater();
			v = LayoutInflater.from(listContext).inflate(R.layout.contacts_item,null);
			
			TextView contactTextView = (TextView)v.findViewById(R.id.contactUserNameTextView);
		    Button btn = (Button)v.findViewById(R.id.contactUserButton);
		    ImageView image = (ImageView)v.findViewById(R.id.contactImage);
		    
		    item = new ContactsItem();
		    item.userName = contactTextView;
		    item.btn = btn;
		    item.user = InfoManager.finalUsers[position];
		    item.image = image;
		    Log.i("user",item.user.toString());
		    
		    item.userName.setText(item.user.memo1);
		    
		    selectProfile(item);//给列表每个item选择对应的头像
		   
		    v.setTag(item);
		    

//		}
//		else {
//			Log.i("userList", "9");
//			v = old;
//			item = (ContactsItem)v.getTag();
//		}

        item.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnContactItemClickListener.onTurnClick(position);
            }
        });
		
        return v;
	}
    
	private void selectProfile(ContactsItem item) {
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
    public interface onContactItemClickListener {
        void onTurnClick(int i);
    }
    
    private onContactItemClickListener mOnContactItemClickListener;

    public void setOnContactItemJumpClickListener(onContactItemClickListener mOnContactItemClickListener) {
        this.mOnContactItemClickListener = mOnContactItemClickListener;
    }
}
