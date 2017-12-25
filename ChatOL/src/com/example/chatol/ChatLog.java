package com.example.chatol;

import java.util.Date;

public class ChatLog {
	int id;
	int userid1;
	int userid2;
	String content;
	Date publishTime;
	String memo1;
	public ChatLog(int id, int userid1, int userid2, String content, Date publishTime, String memo1) {
		super();
		this.id = id;
		this.userid1 = userid1;
		this.userid2 = userid2;
		this.content = content;
		this.publishTime = publishTime;
		this.memo1 = memo1;
	}
	@Override
	public String toString() {
		return "ChatLog [id=" + id + ", userid1=" + userid1 + ", userid2=" + userid2 + ", content=" + content
				+ ", publishTime=" + publishTime + ", memo1=" + memo1 + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + id;
		result = prime * result + ((memo1 == null) ? 0 : memo1.hashCode());
		result = prime * result + ((publishTime == null) ? 0 : publishTime.hashCode());
		result = prime * result + userid1;
		result = prime * result + userid2;
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
		ChatLog other = (ChatLog) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id != other.id)
			return false;
		if (memo1 == null) {
			if (other.memo1 != null)
				return false;
		} else if (!memo1.equals(other.memo1))
			return false;
		if (publishTime == null) {
			if (other.publishTime != null)
				return false;
		} else if (!publishTime.equals(other.publishTime))
			return false;
		if (userid1 != other.userid1)
			return false;
		if (userid2 != other.userid2)
			return false;
		return true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid1() {
		return userid1;
	}
	public void setUserid1(int userid1) {
		this.userid1 = userid1;
	}
	public int getUserid2() {
		return userid2;
	}
	public void setUserid2(int userid2) {
		this.userid2 = userid2;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public String getMemo1() {
		return memo1;
	}
	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}

	
}
