package com.example.chatol;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ListItem {

	public TextView userName;
	public TextView chatLog;
	public Button btn;	
	public User user;
	public ImageView image;
	@Override
	public String toString() {
		return "ListItem [userName=" + userName + ", chatLog=" + chatLog + ", btn=" + btn + ", user=" + user
				+ ", image=" + image + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((btn == null) ? 0 : btn.hashCode());
		result = prime * result + ((chatLog == null) ? 0 : chatLog.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListItem other = (ListItem) obj;
		if (btn == null) {
			if (other.btn != null)
				return false;
		} else if (!btn.equals(other.btn))
			return false;
		if (chatLog == null) {
			if (other.chatLog != null)
				return false;
		} else if (!chatLog.equals(other.chatLog))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	
}
